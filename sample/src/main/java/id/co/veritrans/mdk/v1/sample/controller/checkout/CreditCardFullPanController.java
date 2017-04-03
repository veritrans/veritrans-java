package id.co.veritrans.mdk.v1.sample.controller.checkout;

import com.fasterxml.jackson.core.JsonProcessingException;
import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.CreditCardFullPan;
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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

/**
 * Created by gde on 5/21/15.
 */
@Controller
@RequestMapping("/checkout/credit_card_full_pan")
public class CreditCardFullPanController extends AbstractVtDirectController {

    private static final boolean D3_SECURE = true;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView checkoutCreditCardGet(final HttpSession httpSession) {
        final SessionManager sessionManager = sessionManagerFactory.get(httpSession);
        final Map<String, Object> viewModel = buildCartViewModel(sessionManager);

        final int[] years = new int[10];
        final int currentYear = GregorianCalendar.getInstance().get(Calendar.YEAR);
        for (int i = 0; i < years.length; i++) {
            years[i] = currentYear + i;
        }
        viewModel.put("years", years);

        return new ModelAndView("checkout/credit_card_full_pan", viewModel);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView checkoutCreditCardPost(
            final HttpSession httpSession,
            @RequestParam("card_number") final String cardNumber,
            @RequestParam("card_cvv") final String cardCvv,
            @RequestParam("card_expired_month") final String cardExpiredMonth,
            @RequestParam("card_expired_year") final String cardExpiredYear,
            final RedirectAttributes redirectAttributes) throws JsonProcessingException {

        final SessionManager sessionManager = sessionManagerFactory.get(httpSession);
        final CartManager cartManager = sessionManager.cartManager();

        final CheckoutForm checkoutForm = SessionUtil.getAttribute(httpSession, "checkoutForm", null);
        if (checkoutForm == null) {
            return new ModelAndView("redirect:/checkout/choose_payment");
        }

        final CreditCardFullPanRequest request = createCreditCardFullPanRequest(new CreditCardFullPan(cardNumber.replaceAll("\\s+",""), cardCvv, cardExpiredMonth, cardExpiredYear, D3_SECURE), checkoutForm, cartManager);
        final Transaction transaction = saveTransaction(request, cartManager, request.getPaymentMethod());

        try {
            final VtResponse vtResponse = vtDirect.charge(request);
            transaction.setPaymentTransactionId(vtResponse.getTransactionId());
            transaction.setPaymentFdsStatus(vtResponse.getFraudStatus() == null ? null : vtResponse.getFraudStatus().name());
            transaction.setPaymentStatus(vtResponse.getTransactionStatus() == null ? null : vtResponse.getTransactionStatus().name());

            if (vtResponse.getStatusCode().equals("201")) {
                cartManager.clear();
                httpSession.removeAttribute("checkoutForm");

                redirectAttributes.addAttribute("transactionId", transaction.getId());
                return new ModelAndView("redirect:" + vtResponse.getRedirectUrl());
            } else {
                return new ModelAndView("redirect:/checkout");
            }
        } catch (RestClientException e) {
            transaction.setPaymentStatus("ERROR");
        }
        return new ModelAndView("redirect:/index");
    }

    protected CreditCardFullPanRequest createCreditCardFullPanRequest(final CreditCardFullPan creditCardFullPan, final CheckoutForm checkoutForm, final CartManager cartManager) {
        final CreditCardFullPanRequest ret = new CreditCardFullPanRequest();
        setVtRequestParam(ret, checkoutForm, cartManager);

        ret.setCreditCardFullPan(creditCardFullPan);
        return ret;
    }
}
