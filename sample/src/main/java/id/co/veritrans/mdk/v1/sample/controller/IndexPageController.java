package id.co.veritrans.mdk.v1.sample.controller;

import id.co.veritrans.mdk.v1.sample.db.model.Product;
import id.co.veritrans.mdk.v1.sample.db.repo.ProductRepo;
import id.co.veritrans.mdk.v1.sample.manager.SessionManager;
import id.co.veritrans.mdk.v1.sample.manager.SessionManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gde on 5/14/15.
 */
@Controller
@RequestMapping(value = "/")
public class IndexPageController {

    @Autowired
    private SessionManagerFactory sessionManagerFactory;
    @Autowired
    private ProductRepo productRepo;

    @RequestMapping(method = RequestMethod.GET)
    public String root() {
        return "redirect:/index";
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView index(final HttpSession httpSession) {
        final SessionManager sessionManager = sessionManagerFactory.get(httpSession);
        final List<Product> products = productRepo.findAll();

        final Map<String, Object> modelValue = new LinkedHashMap<String, Object>();
        modelValue.put("products", products);
        modelValue.put("cartSize", sessionManager.cartManager().getCartSize());

        return new ModelAndView("index", modelValue);
    }
}
