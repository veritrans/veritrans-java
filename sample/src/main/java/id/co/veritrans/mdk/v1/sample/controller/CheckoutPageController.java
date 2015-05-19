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

    @RequestMapping(value = "choose_payment", method = RequestMethod.POST)
    public ModelAndView checkoutChoosePaymentPost(final HttpSession httpSession, final CheckoutForm checkoutForm) {
        if (checkoutForm.getPaymentMethod().equals("creditCard")) {
            return new ModelAndView("redirect:/checkout/credit_card");
        }
        return new ModelAndView("redirect:/checkout");
    }

    @RequestMapping(value = "credit_card", method = RequestMethod.GET)
    public ModelAndView checkoutCreditCardGet(final HttpSession httpSession) {
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

        return new ModelAndView("checkout/credit_card", modelValue);
    }

    @RequestMapping(value = "credit_card", method = RequestMethod.POST)
    public ModelAndView checkoutCreditCardPost(final HttpSession httpSession) {
        final Map<Long, Long> cartItems = SessionUtil.getAttribute(httpSession, "cart_items", new LinkedHashMap<Long, Long>());
        cartItems.clear();
        return new ModelAndView("redirect:/index");
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
        private String email;
        private String billingFirstName;
        private String billingLastName;
        private String billingAddress1;
        private String billingAddress2;
        private String billingCity;
        private String billingPostalCode;
        private String billingPhone;
        private String shippingFirstName;
        private String shippingLastName;
        private String shippingAddress1;
        private String shippingAddress2;
        private String shippingCity;
        private String shippingPostalCode;
        private String shippingPhone;

        public String getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(final String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(final String email) {
            this.email = email;
        }

        public String getBillingFirstName() {
            return billingFirstName;
        }

        public void setBillingFirstName(final String billingFirstName) {
            this.billingFirstName = billingFirstName;
        }

        public String getBillingLastName() {
            return billingLastName;
        }

        public void setBillingLastName(final String billingLastName) {
            this.billingLastName = billingLastName;
        }

        public String getBillingAddress1() {
            return billingAddress1;
        }

        public void setBillingAddress1(final String billingAddress1) {
            this.billingAddress1 = billingAddress1;
        }

        public String getBillingAddress2() {
            return billingAddress2;
        }

        public void setBillingAddress2(final String billingAddress2) {
            this.billingAddress2 = billingAddress2;
        }

        public String getBillingCity() {
            return billingCity;
        }

        public void setBillingCity(final String billingCity) {
            this.billingCity = billingCity;
        }

        public String getBillingPostalCode() {
            return billingPostalCode;
        }

        public void setBillingPostalCode(final String billingPostalCode) {
            this.billingPostalCode = billingPostalCode;
        }

        public String getBillingPhone() {
            return billingPhone;
        }

        public void setBillingPhone(final String billingPhone) {
            this.billingPhone = billingPhone;
        }

        public String getShippingFirstName() {
            return shippingFirstName;
        }

        public void setShippingFirstName(final String shippingFirstName) {
            this.shippingFirstName = shippingFirstName;
        }

        public String getShippingLastName() {
            return shippingLastName;
        }

        public void setShippingLastName(final String shippingLastName) {
            this.shippingLastName = shippingLastName;
        }

        public String getShippingAddress1() {
            return shippingAddress1;
        }

        public void setShippingAddress1(final String shippingAddress1) {
            this.shippingAddress1 = shippingAddress1;
        }

        public String getShippingAddress2() {
            return shippingAddress2;
        }

        public void setShippingAddress2(final String shippingAddress2) {
            this.shippingAddress2 = shippingAddress2;
        }

        public String getShippingCity() {
            return shippingCity;
        }

        public void setShippingCity(final String shippingCity) {
            this.shippingCity = shippingCity;
        }

        public String getShippingPostalCode() {
            return shippingPostalCode;
        }

        public void setShippingPostalCode(final String shippingPostalCode) {
            this.shippingPostalCode = shippingPostalCode;
        }

        public String getShippingPhone() {
            return shippingPhone;
        }

        public void setShippingPhone(final String shippingPhone) {
            this.shippingPhone = shippingPhone;
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
