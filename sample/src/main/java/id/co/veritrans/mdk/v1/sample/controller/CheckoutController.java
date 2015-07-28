package id.co.veritrans.mdk.v1.sample.controller;

import id.co.veritrans.mdk.v1.gateway.VtDirect;
import id.co.veritrans.mdk.v1.sample.controller.model.CheckoutForm;
import id.co.veritrans.mdk.v1.sample.controller.model.ViewCartItem;
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

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by gde on 5/18/15.
 */
@Controller
@RequestMapping("/checkout")
public class CheckoutController {

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
        if (checkoutForm.getPaymentMethod().equals("vtdirect")) {
            return new ModelAndView("redirect:/checkout/vtdirect");
        } else if (checkoutForm.getPaymentMethod().equals("vtweb")) {
            return new ModelAndView("redirect:/checkout/vtweb");
        }
        return new ModelAndView("redirect:/checkout");
    }

    @RequestMapping(value = "vtdirect", method = RequestMethod.GET)
    public ModelAndView checkoutChoosePaymentVtDirectGet(final HttpSession httpSession, final CheckoutForm checkoutForm) {
        final SessionManager sessionManager = sessionManagerFactory.get(httpSession);
        return new ModelAndView("checkout/vtdirect", buildCartViewModel(sessionManager));
    }

    @RequestMapping(value = "vtdirect", method = RequestMethod.POST)
    public ModelAndView checkoutChoosePaymentVtDirectPost(final HttpSession httpSession, @RequestParam("paymentMethod") final String paymentMethod) {
        if (paymentMethod.equals("creditCard")) {
            return new ModelAndView("redirect:/checkout/credit_card");
        } else if (paymentMethod.equals("creditCardFullPan")) {
            return new ModelAndView("redirect:/checkout/credit_card_full_pan");
        } else if (paymentMethod.equals("cimbClicks")) {
            return new ModelAndView("redirect:/checkout/cimb_clicks");
        } else if (paymentMethod.equals("mandiriClickpay")) {
            return new ModelAndView("redirect:/checkout/mandiri_clickpay");
        } else if (paymentMethod.equals("bcaKlikpay")) {
            return new ModelAndView("redirect:/checkout/bca_klikpay");
        } else if (paymentMethod.equals("klikBca")) {
            return new ModelAndView("redirect:/checkout/klik_bca");
        } else if (paymentMethod.equals("bankTransfer")) {
            return new ModelAndView("redirect:/checkout/bank_transfer");
        } else if (paymentMethod.equals("briEpay")) {
            return new ModelAndView("redirect:/checkout/bri_epay");
        } else {
            return new ModelAndView("redirect:/checkout/vtdirect");
        }
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
}
