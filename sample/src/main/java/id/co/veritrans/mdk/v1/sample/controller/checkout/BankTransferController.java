package id.co.veritrans.mdk.v1.sample.controller.checkout;

import com.fasterxml.jackson.core.JsonProcessingException;
import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.BankTransferRequest;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.BankTransfer;
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
@RequestMapping("/checkout/bank_transfer")
public class BankTransferController extends AbstractVtDirectController {

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

        return new ModelAndView("checkout/bank_transfer", viewModel);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/finish")
    public ModelAndView finish(final HttpSession httpSession, String permataVaNumber, Long totalPrice) {
        final SessionManager sessionManager = sessionManagerFactory.get(httpSession);
        final Map<String, Object> viewModel = buildCartViewModel(sessionManager);
        viewModel.put("permataVaNumber", permataVaNumber.replaceAll(".{4}", "$0 "));
        viewModel.put("totalPrice", totalPrice);

        return new ModelAndView("checkout/bank_transfer_finish", viewModel);
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

        final BankTransferRequest request = createBankTransferRequest(checkoutForm, cartManager);
        final Transaction transaction = saveTransaction(request, cartManager, request.getPaymentMethod());

        try {
            final VtResponse vtResponse = vtDirect.charge(request);
            transaction.setPaymentTransactionId(vtResponse.getTransactionId());
            transaction.setPaymentFdsStatus(vtResponse.getFraudStatus() == null ? null : vtResponse.getFraudStatus().name());
            transaction.setPaymentStatus(vtResponse.getTransactionStatus() == null ? null : vtResponse.getTransactionStatus().name());

            if (vtResponse.getStatusCode().equals("201")) {
                Long totalPrice = cartManager.calcTotalPrice();
                cartManager.clear();
                httpSession.removeAttribute("checkoutForm");

                redirectAttributes.addAttribute("transactionId", transaction.getId());
                return finish(httpSession, vtResponse.getPermataVaNumber(), totalPrice);
            } else {
                return new ModelAndView("redirect:/checkout");
            }
        } catch (RestClientException e) {
            transaction.setPaymentStatus("ERROR");
        }
        return new ModelAndView("redirect:/index");
    }

    private BankTransferRequest createBankTransferRequest(CheckoutForm checkoutForm, CartManager cartManager) {
        final BankTransferRequest ret = new BankTransferRequest();
        setVtRequestParam(ret, checkoutForm, cartManager);

        ret.setBankTransfer(new BankTransfer());
        ret.getBankTransfer().setBank(BankTransfer.Bank.PERMATA);
        return ret;
    }
}
