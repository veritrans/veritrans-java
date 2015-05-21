package id.co.veritrans.mdk.v1.sample.manager;

import id.co.veritrans.mdk.v1.sample.db.model.Product;
import id.co.veritrans.mdk.v1.sample.db.repo.ProductRepo;
import id.co.veritrans.mdk.v1.sample.manager.model.CartItem;
import id.co.veritrans.mdk.v1.sample.util.SessionUtil;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by gde on 5/20/15.
 */
public class CartManager {

    private static final String HTTP_SESSION_KEY = CartManager.class.getName() + ".cartItemMap";
    private final ProductRepo productRepo;
    private final HttpSession httpSession;
    private final Map<Long, SessionCartItem> sessionCartItemMap;
    private final Map<Long, CartItem> cartItemMap;

    public CartManager(final ProductRepo productRepo, final HttpSession httpSession) {
        this.productRepo = productRepo;
        this.httpSession = httpSession;
        this.sessionCartItemMap = loadSessionCartItemMap();
        this.cartItemMap = loadCartItemMap();
    }

    /**
     * We only store the productId in the session instead of the whole Product POJO, this is intended to comply with
     * JPA detached entity state once the request in finished.
     * @return
     */
    private Map<Long, SessionCartItem> loadSessionCartItemMap() {
        return SessionUtil.getAttribute(httpSession, HTTP_SESSION_KEY, new LinkedHashMap<Long, SessionCartItem>());
    }

    private Map<Long, CartItem> loadCartItemMap() {
        final Map<Long, CartItem> ret = new LinkedHashMap<Long, CartItem>(sessionCartItemMap.size());
        for (final Map.Entry<Long, SessionCartItem> entry : sessionCartItemMap.entrySet()) {
            ret.put(entry.getKey(), new CartItem(productRepo.findOne(entry.getKey()), entry.getValue().getAmount()));
        }
        return ret;
    }

    public CartItem add(final Product product) {
        final CartItem cartItem = cartItemMap.get(product.getId());
        final CartItem newCartItem;

        if (cartItem == null) {
            newCartItem = new CartItem(product, 1);
        } else {
            newCartItem = new CartItem(product, cartItem.getAmount() + 1);
        }

        doAdd(newCartItem);
        return newCartItem;
    }

    private void doAdd(final CartItem cartItem) {
        cartItemMap.put(cartItem.getProduct().getId(), cartItem);
        sessionCartItemMap.put(cartItem.getProduct().getId(), new SessionCartItem(cartItem.getAmount()));
    }

    public int getCartSize() {
        return cartItemMap.size();
    }

    public long calcTotalPrice() {
        long ret = 0;
        for (final Map.Entry<Long, CartItem> entry : cartItemMap.entrySet()) {
            ret += entry.getValue().calcTotalPrice();
        }
        return ret;
    }

    public Collection<CartItem> getCartItems() {
        return cartItemMap.values();
    }

    public CartManager clear() {
        sessionCartItemMap.clear();
        cartItemMap.clear();
        return this;
    }

    private static class SessionCartItem {

        private final int amount;

        public SessionCartItem(final int amount) {
            this.amount = amount;
        }

        public int getAmount() {
            return amount;
        }
    }
}
