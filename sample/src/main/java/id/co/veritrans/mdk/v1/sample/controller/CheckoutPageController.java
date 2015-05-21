package id.co.veritrans.mdk.v1.sample.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.VtDirect;
import id.co.veritrans.mdk.v1.gateway.model.*;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.CreditCardRequest;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.CreditCard;
import id.co.veritrans.mdk.v1.sample.db.model.Product;
import id.co.veritrans.mdk.v1.sample.db.model.Transaction;
import id.co.veritrans.mdk.v1.sample.db.repo.ProductRepo;
import id.co.veritrans.mdk.v1.sample.db.repo.TransactionItemRepo;
import id.co.veritrans.mdk.v1.sample.db.repo.TransactionRepo;
import id.co.veritrans.mdk.v1.sample.manager.CartManager;
import id.co.veritrans.mdk.v1.sample.manager.SessionManager;
import id.co.veritrans.mdk.v1.sample.manager.SessionManagerFactory;
import id.co.veritrans.mdk.v1.sample.manager.VtPaymentManager;
import id.co.veritrans.mdk.v1.sample.manager.model.CartItem;
import id.co.veritrans.mdk.v1.sample.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by gde on 5/18/15.
 */
@Controller
@RequestMapping("/checkout")
public class CheckoutPageController {

    @Autowired
    private SessionManagerFactory sessionManagerFactory;
    @Autowired
    private VtPaymentManager vtPaymentManager;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private TransactionItemRepo transactionItemRepo;
    private VtDirect vtDirect;

    @PostConstruct
    public void setup() {
        vtDirect = vtPaymentManager.getVtGatewayFactory().vtDirect();
    }

    private Map<String, Object> buildCartViewModel(final SessionManager sessionManager) {
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

    @RequestMapping(value = "choose_payment", method = RequestMethod.GET)
    public ModelAndView checkoutChoosePaymentGet(final HttpSession httpSession) {
        final SessionManager sessionManager = sessionManagerFactory.get(httpSession);
        return new ModelAndView("checkout/choose_payment", buildCartViewModel(sessionManager));
    }

    @RequestMapping(value = "choose_payment", method = RequestMethod.POST)
    public ModelAndView checkoutChoosePaymentPost(final HttpSession httpSession, final CheckoutForm checkoutForm) {
        httpSession.setAttribute("checkoutForm", checkoutForm);
        if (checkoutForm.getPaymentMethod().equals("creditCard")) {
            return new ModelAndView("redirect:/checkout/credit_card");
        }
        return new ModelAndView("redirect:/checkout");
    }

    @RequestMapping(value = "credit_card", method = RequestMethod.GET)
    public ModelAndView checkoutCreditCardGet(final HttpSession httpSession) {
        final SessionManager sessionManager = sessionManagerFactory.get(httpSession);
        final Map<String, Object> viewModel = buildCartViewModel(sessionManager);

        final int[] years = new int[10];
        final int currentYear = GregorianCalendar.getInstance().get(Calendar.YEAR);
        for (int i = 0; i < years.length; i++) {
            years[i] = currentYear + i;
        }
        viewModel.put("years", years);

        return new ModelAndView("checkout/credit_card", viewModel);
    }

    @Transactional
    @RequestMapping(value = "credit_card", method = RequestMethod.POST)
    public ModelAndView checkoutCreditCardPost(final HttpSession httpSession, @RequestParam("vt_token") final String vtToken, final RedirectAttributes redirectAttributes) throws JsonProcessingException {
        final SessionManager sessionManager = sessionManagerFactory.get(httpSession);
        final CartManager cartManager = sessionManager.cartManager();

        final CheckoutForm checkoutForm = SessionUtil.getAttribute(httpSession, "checkoutForm", null);
        if (checkoutForm == null) {
            return new ModelAndView("redirect:/checkout/choose_payment");
        }

        final CreditCardRequest creditCardRequest = createCreditCardRequest(vtToken, checkoutForm, cartManager);
        final Transaction transaction = saveTransaction(creditCardRequest, cartManager);

        try {
            final VtResponse vtResponse = vtDirect.charge(creditCardRequest);
            transaction.setPaymentTransactionId(vtResponse.getTransactionId());
            transaction.setPaymentFdsStatus(vtResponse.getFraudStatus() == null ? null : vtResponse.getFraudStatus().name());
            transaction.setPaymentStatus(vtResponse.getTransactionStatus() == null ? null : vtResponse.getTransactionStatus().name());

            if (vtResponse.getStatusCode().equals("200")) {
                cartManager.clear();
                httpSession.removeAttribute("checkoutForm");

                redirectAttributes.addAttribute("transactionId", transaction.getId());
                return new ModelAndView("redirect:/checkout/success");
            } else {
                return new ModelAndView("redirect:/checkout");
            }
        } catch (RestClientException e) {
            transaction.setPaymentStatus("ERROR");
        }
        return new ModelAndView("redirect:/index");
    }

    @Transactional(readOnly = true)
    @RequestMapping(value = "success", method = RequestMethod.GET)
    public ModelAndView checkoutPaymentSuccessGet(final HttpSession httpSession, @RequestParam("transactionId") final Long transactionId) {
        final Map<Long, Integer> sessionCartItems = SessionUtil.getAttribute(httpSession, "cartItems", new LinkedHashMap<Long, Integer>());
        final Transaction transaction = transactionRepo.findOne(transactionId);
        final List<id.co.veritrans.mdk.v1.sample.db.model.TransactionItem> transactionItems = transactionItemRepo.findByTransaction(transaction);

        final List<ViewCartItem> cartItems = new ArrayList<ViewCartItem>(transactionItems.size());
        for (final id.co.veritrans.mdk.v1.sample.db.model.TransactionItem transactionItem : transactionItems) {
            cartItems.add(new ViewCartItem(transactionItem.getProduct(), transactionItem.getAmount()));
        }
        final int cartSize = sessionCartItems.size();

        long totalPrice = 0;
        for (final ViewCartItem cartItem : cartItems) {
            totalPrice += cartItem.getTotalPrice();
        }

        final Map<String, Object> viewModel = new LinkedHashMap<String, Object>();
        viewModel.put("cartItems", cartItems);
        viewModel.put("cartSize", cartSize);
        viewModel.put("totalPrice", totalPrice);
        viewModel.put("transaction", transaction);

        return new ModelAndView("checkout/success", viewModel);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView checkoutGet(final HttpSession httpSession) {
        final SessionManager sessionManager = sessionManagerFactory.get(httpSession);
        return new ModelAndView("checkout", buildCartViewModel(sessionManager));
    }

    private Transaction saveTransaction(final CreditCardRequest creditCardRequest, final CartManager cartManager) {
        final Transaction ret = new Transaction();
        ret.setBillingAddress(creditCardRequest.getCustomerDetails().getBillingAddress().getAddress());
        ret.setBillingCity(creditCardRequest.getCustomerDetails().getBillingAddress().getCity());
        ret.setBillingCountryCode(creditCardRequest.getCustomerDetails().getBillingAddress().getCountryCode());
        ret.setBillingFirstName(creditCardRequest.getCustomerDetails().getBillingAddress().getFirstName());
        ret.setBillingLastName(creditCardRequest.getCustomerDetails().getBillingAddress().getLastName());
        ret.setBillingPhone(creditCardRequest.getCustomerDetails().getBillingAddress().getPhone());
        ret.setBillingPostalCode(creditCardRequest.getCustomerDetails().getBillingAddress().getPostalCode());
        
        ret.setCustomerEmail(creditCardRequest.getCustomerDetails().getEmail());
        ret.setCustomerFirstName(creditCardRequest.getCustomerDetails().getFirstName());
        ret.setCustomerLastName(creditCardRequest.getCustomerDetails().getLastName());
        ret.setCustomerPhone(creditCardRequest.getCustomerDetails().getPhone());

        ret.setShippingAddress(creditCardRequest.getCustomerDetails().getShippingAddress().getAddress());
        ret.setShippingCity(creditCardRequest.getCustomerDetails().getShippingAddress().getCity());
        ret.setShippingCountryCode(creditCardRequest.getCustomerDetails().getShippingAddress().getCountryCode());
        ret.setShippingFirstName(creditCardRequest.getCustomerDetails().getShippingAddress().getFirstName());
        ret.setShippingLastName(creditCardRequest.getCustomerDetails().getShippingAddress().getLastName());
        ret.setShippingPhone(creditCardRequest.getCustomerDetails().getShippingAddress().getPhone());
        ret.setShippingPostalCode(creditCardRequest.getCustomerDetails().getShippingAddress().getPostalCode());

        ret.setPaymentFdsStatus(null);
        ret.setPaymentMethod(PaymentMethod.CREDIT_CARD.name());
        ret.setPaymentOrderId(creditCardRequest.getTransactionDetails().getOrderId());
        ret.setPaymentStatus(null);
        ret.setPaymentTransactionId(null);

        ret.setTotalPriceIdr(creditCardRequest.getTransactionDetails().getGrossAmount());

        final Transaction managedTransaction = transactionRepo.save(ret);
        for (final CartItem cartItem : cartManager.getCartItems()) {
            final id.co.veritrans.mdk.v1.sample.db.model.TransactionItem transactionItem = new id.co.veritrans.mdk.v1.sample.db.model.TransactionItem();
            transactionItem.setProduct(cartItem.getProduct());
            transactionItem.setTransaction(managedTransaction);
            transactionItem.setAmount(cartItem.getAmount());
            transactionItem.setPriceEachIdr(cartItem.getProduct().getPriceIdr());
            transactionItem.setTotalPriceIdr(cartItem.calcTotalPrice());
            transactionItemRepo.save(transactionItem);
        }
        return managedTransaction;
    }

    private CreditCardRequest createCreditCardRequest(final String vtToken, final CheckoutForm checkoutForm, final CartManager cartManager) {
        final CreditCardRequest ret = new CreditCardRequest();
        ret.setCreditCard(new CreditCard());
        ret.getCreditCard().setCardToken(vtToken);

        ret.setCustomerDetails(toCustomerDetails(checkoutForm));

        ret.setTransactionDetails(new TransactionDetails());
        ret.setItemDetails(new ArrayList<TransactionItem>(cartManager.getCartSize()));

        ret.getTransactionDetails().setGrossAmount(cartManager.calcTotalPrice());
        ret.getTransactionDetails().setOrderId(UUID.randomUUID().toString());

        for (final CartItem cartItem : cartManager.getCartItems()) {
            final Product product = cartItem.getProduct();
            ret.getItemDetails().add(new TransactionItem(
                    product.getId().toString(),
                    product.getShortName(),
                    product.getPriceIdr(),
                    cartItem.getAmount()
            ));
        }
        return ret;
    }

    private CustomerDetails toCustomerDetails(CheckoutForm checkoutForm) {
        final CustomerDetails ret = new CustomerDetails();
        ret.setFirstName(checkoutForm.getBillingFirstName());
        ret.setLastName(checkoutForm.getBillingLastName());
        ret.setEmail(checkoutForm.getEmail());
        ret.setPhone(checkoutForm.getBillingPhone());

        ret.setBillingAddress(new Address());
        ret.getBillingAddress().setAddress(checkoutForm.getBillingAddress1() +"\n" +checkoutForm.getBillingAddress2());
        ret.getBillingAddress().setCity(checkoutForm.getBillingCity());
        ret.getBillingAddress().setCountryCode("IDN");
        ret.getBillingAddress().setFirstName(checkoutForm.getBillingFirstName());
        ret.getBillingAddress().setLastName(checkoutForm.getBillingLastName());
        ret.getBillingAddress().setPhone(checkoutForm.getBillingPhone());
        ret.getBillingAddress().setPostalCode(checkoutForm.getBillingPostalCode());

        ret.setShippingAddress(new Address());
        ret.getShippingAddress().setAddress(checkoutForm.getShippingAddress1() +"\n" +checkoutForm.getShippingAddress2());
        ret.getShippingAddress().setCity(checkoutForm.getShippingCity());
        ret.getShippingAddress().setCountryCode("IDN");
        ret.getShippingAddress().setFirstName(checkoutForm.getShippingFirstName());
        ret.getShippingAddress().setLastName(checkoutForm.getShippingLastName());
        ret.getShippingAddress().setPhone(checkoutForm.getShippingPhone());
        ret.getShippingAddress().setPostalCode(checkoutForm.getShippingPostalCode());

        return ret;
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

    private static class ViewCartItem {

        private Product product;
        private Integer count;
        private Long totalPrice;

        public ViewCartItem(final Product product, final Integer count) {
            this.product = product;
            this.count = count;
            this.totalPrice = new Long(count.longValue() * product.getPriceIdr().longValue());
        }

        public Product getProduct() {
            return product;
        }

        public void setProduct(final Product product) {
            this.product = product;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(final Integer count) {
            this.count = count;
        }

        public Long getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(final Long totalPrice) {
            this.totalPrice = totalPrice;
        }
    }
}