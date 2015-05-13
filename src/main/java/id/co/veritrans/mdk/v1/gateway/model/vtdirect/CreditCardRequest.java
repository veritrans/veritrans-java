package id.co.veritrans.mdk.v1.gateway.model.vtdirect;

import id.co.veritrans.mdk.v1.gateway.model.CustomerDetails;
import id.co.veritrans.mdk.v1.gateway.model.PaymentMethod;
import id.co.veritrans.mdk.v1.gateway.model.TransactionDetails;
import id.co.veritrans.mdk.v1.gateway.model.TransactionItem;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.CreditCard;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Veritrans VT-Direct credit card request model
 */
public class CreditCardRequest extends VtDirectChargeRequest {

    @Valid
    @NotNull
    private CreditCard creditCard;

    /**
     * Credit card request constructor
     */
    public CreditCardRequest() {
    }

    /**
     * Credit catd request constructor
     * @param transactionDetails    {@link id.co.veritrans.mdk.v1.gateway.model.TransactionDetails Transaction details}
     * @param transactionItems      List of {@link id.co.veritrans.mdk.v1.gateway.model.TransactionItem transaction item}
     * @param customerDetails       {@link id.co.veritrans.mdk.v1.gateway.model.CustomerDetails Customer details}
     * @param creditCard            {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.CreditCard Credit card} detail
     */
    public CreditCardRequest(final TransactionDetails transactionDetails, final List<TransactionItem> transactionItems, final CustomerDetails customerDetails, final CreditCard creditCard) {
        super(transactionDetails, transactionItems, customerDetails);
        this.creditCard = creditCard;
    }

    /**
     * Get credit card payment method
     * @return Credit card {@link id.co.veritrans.mdk.v1.gateway.model.PaymentMethod#CREDIT_CARD payment method}
     */
    @Override
    public PaymentMethod getPaymentMethod() {
        return PaymentMethod.CREDIT_CARD;
    }

    /**
     * Get credit card detail
     * @return {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.CreditCard Credit card} detail
     */
    public CreditCard getCreditCard() {
        return creditCard;
    }

    /**
     * Set credit card detail
     * @param creditCard {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.CreditCard Credit card} detail
     */
    public void setCreditCard(final CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        final CreditCardRequest that = (CreditCardRequest) o;

        if (creditCard != null ? !creditCard.equals(that.creditCard) : that.creditCard != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (creditCard != null ? creditCard.hashCode() : 0);
        return result;
    }
}
