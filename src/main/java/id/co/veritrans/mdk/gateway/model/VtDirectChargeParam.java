package id.co.veritrans.mdk.gateway.model;

import id.co.veritrans.mdk.gateway.model.paymentmethod.BankTransfer;
import id.co.veritrans.mdk.gateway.model.paymentmethod.CimbClicks;
import id.co.veritrans.mdk.gateway.model.paymentmethod.CreditCard;
import id.co.veritrans.mdk.gateway.model.paymentmethod.MandiriClickpay;

import java.util.List;

/**
 * Created by gde on 5/4/15.
 */
public class VtDirectChargeParam {

    private PaymentMethod paymentMethod;
    private TransactionDetails transactionDetails;
    private List<TransactionItem> transactionItems;
    private CustomerDetails customerDetails;
    private CreditCard creditCard;
    private MandiriClickpay mandiriClickpay;
    private CimbClicks cimbClicks;
    private BankTransfer bankTransfer;

    public VtDirectChargeParam() {
    }

    public VtDirectChargeParam(final PaymentMethod paymentMethod, final TransactionDetails transactionDetails, final List<TransactionItem> transactionItems, final CustomerDetails customerDetails, final CreditCard creditCard, final MandiriClickpay mandiriClickpay, final CimbClicks cimbClicks, final BankTransfer bankTransfer) {
        this.paymentMethod = paymentMethod;
        this.transactionDetails = transactionDetails;
        this.transactionItems = transactionItems;
        this.customerDetails = customerDetails;
        this.creditCard = creditCard;
        this.mandiriClickpay = mandiriClickpay;
        this.cimbClicks = cimbClicks;
        this.bankTransfer = bankTransfer;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(final PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public TransactionDetails getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(final TransactionDetails transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    public List<TransactionItem> getTransactionItems() {
        return transactionItems;
    }

    public void setTransactionItems(final List<TransactionItem> transactionItems) {
        this.transactionItems = transactionItems;
    }

    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(final CustomerDetails customerDetails) {
        this.customerDetails = customerDetails;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(final CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public MandiriClickpay getMandiriClickpay() {
        return mandiriClickpay;
    }

    public void setMandiriClickpay(final MandiriClickpay mandiriClickpay) {
        this.mandiriClickpay = mandiriClickpay;
    }

    public CimbClicks getCimbClicks() {
        return cimbClicks;
    }

    public void setCimbClicks(final CimbClicks cimbClicks) {
        this.cimbClicks = cimbClicks;
    }

    public BankTransfer getBankTransfer() {
        return bankTransfer;
    }

    public void setBankTransfer(final BankTransfer bankTransfer) {
        this.bankTransfer = bankTransfer;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final VtDirectChargeParam that = (VtDirectChargeParam) o;

        if (bankTransfer != null ? !bankTransfer.equals(that.bankTransfer) : that.bankTransfer != null) return false;
        if (cimbClicks != null ? !cimbClicks.equals(that.cimbClicks) : that.cimbClicks != null) return false;
        if (creditCard != null ? !creditCard.equals(that.creditCard) : that.creditCard != null) return false;
        if (customerDetails != null ? !customerDetails.equals(that.customerDetails) : that.customerDetails != null)
            return false;
        if (mandiriClickpay != null ? !mandiriClickpay.equals(that.mandiriClickpay) : that.mandiriClickpay != null)
            return false;
        if (paymentMethod != that.paymentMethod) return false;
        if (transactionDetails != null ? !transactionDetails.equals(that.transactionDetails) : that.transactionDetails != null)
            return false;
        if (transactionItems != null ? !transactionItems.equals(that.transactionItems) : that.transactionItems != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = paymentMethod != null ? paymentMethod.hashCode() : 0;
        result = 31 * result + (transactionDetails != null ? transactionDetails.hashCode() : 0);
        result = 31 * result + (transactionItems != null ? transactionItems.hashCode() : 0);
        result = 31 * result + (customerDetails != null ? customerDetails.hashCode() : 0);
        result = 31 * result + (creditCard != null ? creditCard.hashCode() : 0);
        result = 31 * result + (mandiriClickpay != null ? mandiriClickpay.hashCode() : 0);
        result = 31 * result + (cimbClicks != null ? cimbClicks.hashCode() : 0);
        result = 31 * result + (bankTransfer != null ? bankTransfer.hashCode() : 0);
        return result;
    }
}
