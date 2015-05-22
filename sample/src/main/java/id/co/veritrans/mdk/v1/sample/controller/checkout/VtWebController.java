package id.co.veritrans.mdk.v1.sample.controller.checkout;

import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.VtWeb;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;
import id.co.veritrans.mdk.v1.gateway.model.vtweb.VtWebChargeRequest;
import id.co.veritrans.mdk.v1.gateway.model.vtweb.VtWebParam;
import id.co.veritrans.mdk.v1.sample.controller.AbstractCheckoutPaymentMethodController;
import id.co.veritrans.mdk.v1.sample.controller.model.CheckoutForm;
import id.co.veritrans.mdk.v1.sample.db.model.Transaction;
import id.co.veritrans.mdk.v1.sample.manager.CartManager;
import id.co.veritrans.mdk.v1.sample.manager.SessionManager;
import id.co.veritrans.mdk.v1.sample.manager.SessionManagerFactory;
import id.co.veritrans.mdk.v1.sample.manager.VtPaymentManager;
import id.co.veritrans.mdk.v1.sample.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

/**
 * Created by gde on 5/21/15.
 */
@Controller
@RequestMapping("/checkout/vtweb")
public class VtWebController extends AbstractCheckoutPaymentMethodController {

    @Autowired
    private SessionManagerFactory sessionManagerFactory;
    @Autowired
    private VtPaymentManager vtPaymentManager;
    private VtWeb vtWeb;

    @PostConstruct
    public void setup() {
        vtWeb = vtPaymentManager.getVtGatewayFactory().vtWeb();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String checkoutVtWebGet(final HttpSession httpSession) throws RestClientException {
        final SessionManager sessionManager = sessionManagerFactory.get(httpSession);
        final CheckoutForm checkoutForm = SessionUtil.getAttribute(httpSession, "checkoutForm", null);

        final VtWebChargeRequest vtWebChargeRequest = createVtWebChargeRequest(checkoutForm, sessionManager.cartManager());
        final Transaction transaction = saveTransaction(vtWebChargeRequest, sessionManager.cartManager(), "vtweb");

        final VtWeb vtWeb = vtPaymentManager.getVtGatewayFactory().vtWeb();
        final VtResponse vtResponse = vtWeb.charge(vtWebChargeRequest);

        if (vtResponse.getStatusCode().equals("201")) {
            return "redirect:" + vtResponse.getRedirectUrl();
        }
        return "redirect:/checkout";
    }

    protected VtWebChargeRequest createVtWebChargeRequest(final CheckoutForm checkoutForm, final CartManager cartManager) {
        final VtWebChargeRequest ret = new VtWebChargeRequest();
        ret.setVtWeb(new VtWebParam());
        ret.getVtWeb().setCreditCardUse3dSecure(true);

        ret.setCustomerDetails(toCustomerDetails(checkoutForm));

        ret.setTransactionDetails(toTransactionDetails(cartManager));
        ret.setItemDetails(toTransactionItems(cartManager));
        return ret;
    }
}
