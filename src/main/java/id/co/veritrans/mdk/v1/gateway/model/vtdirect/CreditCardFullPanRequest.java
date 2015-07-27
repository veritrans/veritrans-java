package id.co.veritrans.mdk.v1.gateway.model.vtdirect;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.co.veritrans.mdk.v1.gateway.model.CustomerDetails;
import id.co.veritrans.mdk.v1.gateway.model.TransactionDetails;
import id.co.veritrans.mdk.v1.gateway.model.TransactionItem;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.CreditCardFullPan;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Veritrans VT-Direct credit card request model
 */
public class CreditCardFullPanRequest extends VtDirectChargeRequest {

    @Valid
    @NotNull
    @JsonProperty("credit_card")
    private CreditCardFullPan creditCardFullPan;

    /**
     * Credit card request constructor
     */
    public CreditCardFullPanRequest() {
    }

    /**
     * @param transactionDetails {@link id.co.veritrans.mdk.v1.gateway.model.TransactionDetails Transaction details}
     * @param itemDetails        List of {@link id.co.veritrans.mdk.v1.gateway.model.TransactionItem transaction item}
     * @param customerDetails    {@link id.co.veritrans.mdk.v1.gateway.model.CustomerDetails Customer details}
     * @param creditCardFullPan  {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.CreditCardFullPan Credit card full pan} detail
     */
    public CreditCardFullPanRequest(TransactionDetails transactionDetails, List<TransactionItem> itemDetails, CustomerDetails customerDetails, CreditCardFullPan creditCardFullPan) {
        super(transactionDetails, itemDetails, customerDetails);
        this.creditCardFullPan = creditCardFullPan;
    }

    /**
     * Get credit card payment method
     *
     * @return Credit Card String (credit_card)
     */
    @Override
    public String getPaymentMethod() {
        return "credit_card";
    }

    /**
     * Get {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.CreditCardFullPan credit card full pan} detail
     *
     * @return {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.CreditCardFullPan Credit card full pan} detail
     */
    public CreditCardFullPan getCreditCardFullPan() {
        return creditCardFullPan;
    }

    /**
     * Set {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.CreditCardFullPan credit card full pan} detail
     *
     * @param creditCardFullPan {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.CreditCardFullPan Credit card full pan} detail
     */
    public void setCreditCardFullPan(CreditCardFullPan creditCardFullPan) {
        this.creditCardFullPan = creditCardFullPan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CreditCardFullPanRequest that = (CreditCardFullPanRequest) o;

        if (creditCardFullPan != null ? !creditCardFullPan.equals(that.creditCardFullPan) : that.creditCardFullPan != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (creditCardFullPan != null ? creditCardFullPan.hashCode() : 0);
        return result;
    }
}
