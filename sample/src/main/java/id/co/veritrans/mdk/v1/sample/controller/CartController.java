package id.co.veritrans.mdk.v1.sample.controller;

import id.co.veritrans.mdk.v1.sample.util.SessionUtil;
import id.co.veritrans.mdk.v1.sample.db.model.Product;
import id.co.veritrans.mdk.v1.sample.db.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by gde on 5/18/15.
 */
@Controller
public class CartController {

    @Autowired
    private ProductRepo productRepo;

    @RequestMapping(value = "/cart_add", method = RequestMethod.GET)
    private String cartAdd(final HttpSession httpSession, final @RequestParam(value = "product_id") Long productId) {
        final Map<Long, Long> cartItems = SessionUtil.getAttribute(httpSession, "cart_items", new LinkedHashMap<Long, Long>());
        final Product product = productRepo.findOne(productId);

        if (product != null) {
            if (cartItems.containsKey(productId)) {
                cartItems.put(productId, cartItems.get(productId).longValue() + 1);
            } else {
                cartItems.put(productId, 1l);
            }
        }

        return "redirect:/index";
    }
}
