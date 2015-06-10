package id.co.veritrans.mdk.v1.sample.controller.checkout;

import id.co.veritrans.mdk.v1.gateway.VtDirect;
import id.co.veritrans.mdk.v1.gateway.model.AbstractVtRequest;
import id.co.veritrans.mdk.v1.sample.controller.model.CheckoutForm;
import id.co.veritrans.mdk.v1.sample.controller.model.ViewCartItem;
import id.co.veritrans.mdk.v1.sample.manager.CartManager;
import id.co.veritrans.mdk.v1.sample.manager.SessionManager;
import id.co.veritrans.mdk.v1.sample.manager.model.CartItem;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by gde on 5/22/15.
 */
public abstract class AbstractVtDirectController extends AbstractCheckoutPaymentMethodController {

    protected VtDirect vtDirect;

    @PostConstruct
    public void setup() {
        vtDirect = vtPaymentManager.getVtGatewayFactory().vtDirect();
    }

    protected Map<String, Object> buildCartViewModel(final SessionManager sessionManager) {
        final CartManager cartManager = sessionManager.cartManager();
        final Map<String, Object> ret = new LinkedHashMap<String, Object>();

        final List<ViewCartItem> cartItemList = new LinkedList<ViewCartItem>();
        for (final CartItem cartItem : sessionManager.cartManager().getCartItems()) {
            cartItemList.add(new ViewCartItem(cartItem.getProduct(), cartItem.getAmount()));
        }

        ret.put("cartItems", cartItemList);
        ret.put("cartSize", cartManager.getCartSize());
        ret.put("totalPrice", cartManager.calcTotalPrice());

        return ret;
    }

    protected void setVtRequestParam(final AbstractVtRequest vtRequest, final CheckoutForm checkoutForm, final CartManager cartManager) {
        vtRequest.setCustomerDetails(toCustomerDetails(checkoutForm));

        vtRequest.setTransactionDetails(toTransactionDetails(cartManager));
        vtRequest.setItemDetails(toTransactionItems(cartManager));
    }
}
