package id.co.veritrans.mdk;

import id.co.veritrans.mdk.gateway.model.CustomerDetails;
import id.co.veritrans.mdk.gateway.model.TransactionDetails;
import id.co.veritrans.mdk.gateway.model.TransactionItem;
import id.co.veritrans.mdk.gateway.model.vtdirect.paymentmethod.CreditCard;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by andes on 5/8/15.
 */
public class TestUtil {

    /* Build credit card object */
    public static CreditCard buildCreditCard() {
        CreditCard creditCard = new CreditCard();
        creditCard.setTokenId("abcdef");
        creditCard.setAcquirerBank(CreditCard.Bank.BNI);

        return creditCard;
    }

    /* Build transaction details object */
    public static TransactionDetails buildTransactionDetails() {
        TransactionDetails transactionDetails = new TransactionDetails();
        transactionDetails.setOrderId(new Date().toString());
        transactionDetails.setGrossAmount(10000L);

        return transactionDetails;
    }

    /* Build customer details object */
    public static CustomerDetails buildCustomerDetails() {
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setFirstName("Andes");
        customerDetails.setEmail("test@email.com");
        customerDetails.setPhone("081234567890");

        return customerDetails;
    }

    /* Build list of transaction item object */
    public static List<TransactionItem> buildTransactionItems() {
        List<TransactionItem> transactionItems = new LinkedList<TransactionItem>();
        transactionItems.add(new TransactionItem("1", "Phone", new BigDecimal(10000L), 1));

        return transactionItems;
    }
}
