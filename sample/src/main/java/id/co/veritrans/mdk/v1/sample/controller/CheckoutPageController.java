package id.co.veritrans.mdk.v1.sample.controller;

import id.co.veritrans.mdk.v1.sample.db.repo.ProductRepo;
import id.co.veritrans.mdk.v1.sample.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by gde on 5/18/15.
 */
@Controller
public class CheckoutPageController {

    @Autowired
    private ProductRepo productRepo;

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public ModelAndView index(final HttpSession httpSession) {
        final Map<Long, Long> cartItems = SessionUtil.getAttribute(httpSession, "cart_items", new LinkedHashMap<Long, Long>());
        final int itemsInCartCount = cartItems.size();

        final Map<String, Object> modelValue = new LinkedHashMap<String, Object>();
        modelValue.put("cartItems", cartItems);
        modelValue.put("itemsInCartCount", itemsInCartCount);

        return new ModelAndView("checkout", modelValue);
    }
}
