package id.co.veritrans.mdk.v1.sample.manager;

import id.co.veritrans.mdk.v1.sample.db.repo.ProductRepo;

import javax.servlet.http.HttpSession;

/**
 * Created by gde on 5/20/15.
 */
public class SessionManager {

    private final HttpSession httpSession;
    private final ProductRepo productRepo;
    private CartManager cartManager;

    protected SessionManager(final HttpSession httpSession, final ProductRepo productRepo) {
        this.httpSession = httpSession;
        this.productRepo = productRepo;
    }

    public CartManager cartManager() {
        if (cartManager == null) {
            cartManager = new CartManager(productRepo, httpSession);
        }
        return cartManager;
    }
}
