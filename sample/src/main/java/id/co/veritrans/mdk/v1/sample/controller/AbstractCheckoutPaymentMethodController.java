package id.co.veritrans.mdk.v1.sample.controller;

import id.co.veritrans.mdk.v1.gateway.model.*;
import id.co.veritrans.mdk.v1.gateway.model.vtweb.VtWebChargeRequest;
import id.co.veritrans.mdk.v1.gateway.model.vtweb.VtWebParam;
import id.co.veritrans.mdk.v1.sample.controller.model.CheckoutForm;
import id.co.veritrans.mdk.v1.sample.db.model.Product;
import id.co.veritrans.mdk.v1.sample.db.model.Transaction;
import id.co.veritrans.mdk.v1.sample.db.repo.TransactionItemRepo;
import id.co.veritrans.mdk.v1.sample.db.repo.TransactionRepo;
import id.co.veritrans.mdk.v1.sample.manager.CartManager;
import id.co.veritrans.mdk.v1.sample.manager.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by gde on 5/21/15.
 */
@Controller
public class AbstractCheckoutPaymentMethodController {

    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private TransactionItemRepo transactionItemRepo;

    protected Transaction saveTransaction(final AbstractVtRequest vtRequest, final CartManager cartManager, final String paymentMethod) {
        final Transaction ret = new Transaction();
        ret.setBillingAddress(vtRequest.getCustomerDetails().getBillingAddress().getAddress());
        ret.setBillingCity(vtRequest.getCustomerDetails().getBillingAddress().getCity());
        ret.setBillingCountryCode(vtRequest.getCustomerDetails().getBillingAddress().getCountryCode());
        ret.setBillingFirstName(vtRequest.getCustomerDetails().getBillingAddress().getFirstName());
        ret.setBillingLastName(vtRequest.getCustomerDetails().getBillingAddress().getLastName());
        ret.setBillingPhone(vtRequest.getCustomerDetails().getBillingAddress().getPhone());
        ret.setBillingPostalCode(vtRequest.getCustomerDetails().getBillingAddress().getPostalCode());

        ret.setCustomerEmail(vtRequest.getCustomerDetails().getEmail());
        ret.setCustomerFirstName(vtRequest.getCustomerDetails().getFirstName());
        ret.setCustomerLastName(vtRequest.getCustomerDetails().getLastName());
        ret.setCustomerPhone(vtRequest.getCustomerDetails().getPhone());

        ret.setShippingAddress(vtRequest.getCustomerDetails().getShippingAddress().getAddress());
        ret.setShippingCity(vtRequest.getCustomerDetails().getShippingAddress().getCity());
        ret.setShippingCountryCode(vtRequest.getCustomerDetails().getShippingAddress().getCountryCode());
        ret.setShippingFirstName(vtRequest.getCustomerDetails().getShippingAddress().getFirstName());
        ret.setShippingLastName(vtRequest.getCustomerDetails().getShippingAddress().getLastName());
        ret.setShippingPhone(vtRequest.getCustomerDetails().getShippingAddress().getPhone());
        ret.setShippingPostalCode(vtRequest.getCustomerDetails().getShippingAddress().getPostalCode());

        ret.setPaymentFdsStatus(null);
        ret.setPaymentMethod(paymentMethod);
        ret.setPaymentOrderId(vtRequest.getTransactionDetails().getOrderId());
        ret.setPaymentStatus(null);
        ret.setPaymentTransactionId(null);

        ret.setTotalPriceIdr(vtRequest.getTransactionDetails().getGrossAmount());

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

    protected TransactionDetails toTransactionDetails(final CartManager cartManager) {
        final TransactionDetails ret = new TransactionDetails();

        ret.setGrossAmount(cartManager.calcTotalPrice());
        ret.setOrderId(UUID.randomUUID().toString());

        return ret;
    }

    protected List<TransactionItem> toTransactionItems(final CartManager cartManager) {
        final List<TransactionItem> ret = new ArrayList<TransactionItem>(cartManager.getCartSize());
        for (final CartItem cartItem : cartManager.getCartItems()) {
            final Product product = cartItem.getProduct();
            ret.add(new TransactionItem(
                    product.getId().toString(),
                    product.getShortName(),
                    product.getPriceIdr(),
                    cartItem.getAmount()
            ));
        }
        return ret;
    }

    protected CustomerDetails toCustomerDetails(final CheckoutForm checkoutForm) {
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
}
