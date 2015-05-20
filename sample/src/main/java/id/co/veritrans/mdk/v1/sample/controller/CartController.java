package id.co.veritrans.mdk.v1.sample.controller;

import id.co.veritrans.mdk.v1.sample.db.model.Product;
import id.co.veritrans.mdk.v1.sample.db.repo.ProductRepo;
import id.co.veritrans.mdk.v1.sample.manager.SessionManager;
import id.co.veritrans.mdk.v1.sample.manager.SessionManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by gde on 5/18/15.
 */
@Controller
public class CartController {

    @Autowired
    private SessionManagerFactory sessionManagerFactory;
    @Autowired
    private ProductRepo productRepo;

    @RequestMapping(value = "/cart_add", method = RequestMethod.GET)
    private String cartAdd(final HttpSession httpSession, final @RequestParam(value = "productId") Long productId) {
        final SessionManager sessionManager = sessionManagerFactory.get(httpSession);
        final Product product = productRepo.findOne(productId);

        if (product != null) {
            sessionManager.cartManager().add(product);
        }

        return "redirect:/index";
    }
}
