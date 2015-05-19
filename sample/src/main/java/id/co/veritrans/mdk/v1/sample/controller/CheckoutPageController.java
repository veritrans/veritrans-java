package id.co.veritrans.mdk.v1.sample.controller;

import id.co.veritrans.mdk.v1.gateway.VtDirect;
import id.co.veritrans.mdk.v1.sample.db.model.Product;
import id.co.veritrans.mdk.v1.sample.db.repo.ProductRepo;
import id.co.veritrans.mdk.v1.sample.manager.VtPaymentManager;
import id.co.veritrans.mdk.v1.sample.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by gde on 5/18/15.
 */
@Controller
@RequestMapping("/checkout")
public class CheckoutPageController {

    @Autowired
    private VtPaymentManager vtPaymentManager;
    @Autowired
    private ProductRepo productRepo;
    private VtDirect vtDirect;

    @PostConstruct
    public void setup() {
        vtDirect = vtPaymentManager.getVtGatewayFactory().vtDirect();
    }

    @RequestMapping(value = "choose_payment", method = RequestMethod.GET)
    public ModelAndView checkoutChoosePaymentGet(final HttpSession httpSession) {
        final Map<Long, Long> cartItems = SessionUtil.getAttribute(httpSession, "cart_items", new LinkedHashMap<Long, Long>());
        final int itemsInCartCount = cartItems.size();

        final List<CartItem> cartItemList = new LinkedList<CartItem>();
        for (final Map.Entry<Long, Long> entry : cartItems.entrySet()) {
            final Long productId = entry.getKey();
            final Long count = entry.getValue();

            cartItemList.add(new CartItem(productRepo.getOne(productId), count));
        }

        long totalPrice = 0;
        for (final CartItem cartItem : cartItemList) {
            totalPrice += cartItem.getTotalPrice().longValue();
        }

        final Map<String, Object> modelValue = new LinkedHashMap<String, Object>();
        modelValue.put("cartItems", cartItemList);
        modelValue.put("itemsInCartCount", itemsInCartCount);
        modelValue.put("totalPrice", totalPrice);

        return new ModelAndView("checkout/choose_payment", modelValue);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView checkoutGet(final HttpSession httpSession) {
        final Map<Long, Long> cartItems = SessionUtil.getAttribute(httpSession, "cart_items", new LinkedHashMap<Long, Long>());
        final int itemsInCartCount = cartItems.size();

        final List<CartItem> cartItemList = new LinkedList<CartItem>();
        for (final Map.Entry<Long, Long> entry : cartItems.entrySet()) {
            final Long productId = entry.getKey();
            final Long count = entry.getValue();

            cartItemList.add(new CartItem(productRepo.getOne(productId), count));
        }

        long totalPrice = 0;
        for (final CartItem cartItem : cartItemList) {
            totalPrice += cartItem.getTotalPrice().longValue();
        }

        final Map<String, Object> modelValue = new LinkedHashMap<String, Object>();
        modelValue.put("cartItems", cartItemList);
        modelValue.put("itemsInCartCount", itemsInCartCount);
        modelValue.put("totalPrice", totalPrice);

        return new ModelAndView("checkout", modelValue);
    }

    public static class CheckoutForm {

        private String paymentMethod;

        public String getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(final String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        @Override
        public String toString() {
            return "CheckoutForm{" +
                    "paymentMethod='" + paymentMethod + '\'' +
                    '}';
        }
    }

    public static class CartItem {

        private Product product;
        private Long count;
        private BigDecimal totalPrice;

        public CartItem(final Product product, final Long count) {
            this.product = product;
            this.count = count;
            this.totalPrice = new BigDecimal(count.longValue() * product.getPriceIdr().longValue());
        }

        public Product getProduct() {
            return product;
        }

        public void setProduct(final Product product) {
            this.product = product;
        }

        public Long getCount() {
            return count;
        }

        public void setCount(final Long count) {
            this.count = count;
        }

        public BigDecimal getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(final BigDecimal totalPrice) {
            this.totalPrice = totalPrice;
        }
    }
}
