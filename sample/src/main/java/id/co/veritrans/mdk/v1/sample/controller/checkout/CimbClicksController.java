package id.co.veritrans.mdk.v1.sample.controller.checkout;

import com.fasterxml.jackson.core.JsonProcessingException;
import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.CimbClicksRequest;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.CimbClicks;
import id.co.veritrans.mdk.v1.sample.controller.model.CheckoutForm;
import id.co.veritrans.mdk.v1.sample.db.model.Transaction;
import id.co.veritrans.mdk.v1.sample.manager.CartManager;
import id.co.veritrans.mdk.v1.sample.manager.SessionManager;
import id.co.veritrans.mdk.v1.sample.util.SessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

/**
 * Created by andes on 5/22/15.
 */
@Controller
@RequestMapping("/checkout/cimb_clicks")
public class CimbClicksController extends AbstractVtDirectController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView get(final HttpSession httpSession) {
        final SessionManager sessionManager = sessionManagerFactory.get(httpSession);
        final Map<String, Object> viewModel = buildCartViewModel(sessionManager);

        final int[] years = new int[10];
        final int currentYear = GregorianCalendar.getInstance().get(Calendar.YEAR);
        for (int i = 0; i < years.length; i++) {
            years[i] = currentYear + i;
        }
        viewModel.put("years", years);

        return new ModelAndView("checkout/cimb_clicks", viewModel);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView post(final HttpSession httpSession, final RedirectAttributes redirectAttributes) throws JsonProcessingException {
        final SessionManager sessionManager = sessionManagerFactory.get(httpSession);
        final CartManager cartManager = sessionManager.cartManager();

        final CheckoutForm checkoutForm = SessionUtil.getAttribute(httpSession, "checkoutForm", null);
        if (checkoutForm == null) {
            return new ModelAndView("redirect:/checkout/choose_payment");
        }

        final CimbClicksRequest request = createCimbClicksRequest(checkoutForm, cartManager);
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

    private CimbClicksRequest createCimbClicksRequest(CheckoutForm checkoutForm, CartManager cartManager) {
        final CimbClicksRequest ret = new CimbClicksRequest();
        setVtRequestParam(ret, checkoutForm, cartManager);

        ret.setCimbClicks(new CimbClicks());
        ret.getCimbClicks().setDescription("Test transaction description");
        return ret;
    }
}
