package id.co.veritrans.mdk.gateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by gde on 5/4/15.
 */
public abstract class VtDirectChargeParam {

    @Valid
    @NotNull
    private TransactionDetails transactionDetails;
    private List<TransactionItem> transactionItems;
    @Valid
    @NotNull
    private CustomerDetails customerDetails;

    public VtDirectChargeParam() {
    }

    public VtDirectChargeParam(final TransactionDetails transactionDetails, final List<TransactionItem> transactionItems, final CustomerDetails customerDetails) {
        this.transactionDetails = transactionDetails;
        this.transactionItems = transactionItems;
        this.customerDetails = customerDetails;
    }

    @JsonProperty
    public abstract PaymentMethod getPaymentMethod();

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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final VtDirectChargeParam that = (VtDirectChargeParam) o;

        if (customerDetails != null ? !customerDetails.equals(that.customerDetails) : that.customerDetails != null)
            return false;
        if (transactionDetails != null ? !transactionDetails.equals(that.transactionDetails) : that.transactionDetails != null)
            return false;
        if (transactionItems != null ? !transactionItems.equals(that.transactionItems) : that.transactionItems != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = transactionDetails != null ? transactionDetails.hashCode() : 0;
        result = 31 * result + (transactionItems != null ? transactionItems.hashCode() : 0);
        result = 31 * result + (customerDetails != null ? customerDetails.hashCode() : 0);
        return result;
    }
}
