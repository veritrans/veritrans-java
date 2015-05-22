package id.co.veritrans.mdk.v1.sample.controller.checkout;

import com.fasterxml.jackson.core.JsonProcessingException;
import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.MandiriClickpayRequest;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.MandiriClickpay;
import id.co.veritrans.mdk.v1.sample.controller.model.CheckoutForm;
import id.co.veritrans.mdk.v1.sample.db.model.Transaction;
import id.co.veritrans.mdk.v1.sample.manager.CartManager;
import id.co.veritrans.mdk.v1.sample.manager.SessionManager;
import id.co.veritrans.mdk.v1.sample.util.SessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Random;

/**
 * Created by gde on 5/22/15.
 */
@Controller
@RequestMapping("/checkout/mandiri_clickpay")
public class MandiriClickpayController extends AbstractVtDirectController {

    private static String generateChallengeCode() {
        final Random rand = new Random();
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView checkoutCreditCardGet(final HttpSession httpSession) {
        final SessionManager sessionManager = sessionManagerFactory.get(httpSession);
        final Map<String, Object> viewModel = buildCartViewModel(sessionManager);

        final String challengeCode = generateChallengeCode();
        httpSession.setAttribute(
                MandiriClickpayController.class.getName() +".challengeCode",
                challengeCode
        );
        viewModel.put("input2", sessionManager.cartManager().calcTotalPrice());
        viewModel.put("input3", challengeCode);

        return new ModelAndView("checkout/mandiri_clickpay", viewModel);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView checkoutCreditCardPost(
            final HttpSession httpSession,
            @RequestParam("cardNumber") final String cardNumber,
            @RequestParam("mandiriToken") final String mandiriToken,
            final RedirectAttributes redirectAttributes) throws JsonProcessingException {

        final SessionManager sessionManager = sessionManagerFactory.get(httpSession);
        final CartManager cartManager = sessionManager.cartManager();

        final CheckoutForm checkoutForm = SessionUtil.getAttribute(httpSession, "checkoutForm", null);
        if (checkoutForm == null) {
            return new ModelAndView("redirect:/checkout/choose_payment");
        }

        final String challengeCode = SessionUtil.getAttribute(httpSession, MandiriClickpayController.class.getName() +".challengeCode", null);
        if (challengeCode == null) {
            return new ModelAndView("redirect:/checkout/choose_payment");
        }
        httpSession.removeAttribute(MandiriClickpayController.class.getName() +".challengeCode");

        final String input2 = String.valueOf(sessionManager.cartManager().calcTotalPrice());
        final String input3 = challengeCode;

        final MandiriClickpayRequest request = createMandiriClickpayRequest(
                cardNumber,
                input2,
                input3,
                mandiriToken,
                checkoutForm,
                cartManager);

        final Transaction transaction = saveTransaction(request, cartManager, request.getPaymentMethod());
        try {
            final VtResponse vtResponse = vtDirect.charge(request);
            transaction.setPaymentTransactionId(vtResponse.getTransactionId());
            transaction.setPaymentFdsStatus(vtResponse.getFraudStatus() == null ? null : vtResponse.getFraudStatus().name());
            transaction.setPaymentStatus(vtResponse.getTransactionStatus() == null ? null : vtResponse.getTransactionStatus().name());

            if (vtResponse.getStatusCode().equals("200")) {
                cartManager.clear();
                httpSession.removeAttribute("checkoutForm");

                redirectAttributes.addAttribute("transactionId", transaction.getId());
                return new ModelAndView("redirect:/checkout/success");
            } else {
                return new ModelAndView("redirect:/checkout");
            }
        } catch (RestClientException e) {
            transaction.setPaymentStatus("ERROR");
        }
        return new ModelAndView("redirect:/index");
    }

    protected MandiriClickpayRequest createMandiriClickpayRequest(
            final String cardNumber,
            final String input2,
            final String input3,
            final String mandiriToken,
            final CheckoutForm checkoutForm,
            final CartManager cartManager) {

        final MandiriClickpayRequest ret = new MandiriClickpayRequest();
        setVtRequestParam(ret, checkoutForm, cartManager);

        ret.setMandiriClickpay(new MandiriClickpay());
        ret.getMandiriClickpay().setCardNumber(cardNumber);
        ret.getMandiriClickpay().setInput1(cardNumber.substring(cardNumber.length() - 10));
        ret.getMandiriClickpay().setInput2(input2);
        ret.getMandiriClickpay().setInput3(input3);
        ret.getMandiriClickpay().setToken(mandiriToken);

        return ret;
    }
}
