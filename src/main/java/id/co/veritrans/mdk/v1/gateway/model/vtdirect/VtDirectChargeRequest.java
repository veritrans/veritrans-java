package id.co.veritrans.mdk.v1.gateway.model.vtdirect;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.co.veritrans.mdk.v1.gateway.model.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Abstract of VtDirectChargeRequest
 */
public abstract class VtDirectChargeRequest extends AbstractVtRequest {

    @Valid
    @NotNull
    private CustomerDetails customerDetails;

    /**
     * VtDirectChargeRequest constructor
     */
    public VtDirectChargeRequest() {
    }

    /**
     * VtDirectChargeRequest constructor
     * @param transactionDetails    {@link id.co.veritrans.mdk.v1.gateway.model.TransactionDetails Transaction details}
     * @param customerDetails       Transaction {@link id.co.veritrans.mdk.v1.gateway.model.CustomerDetails customer details}
     */
    public VtDirectChargeRequest(final TransactionDetails transactionDetails, final CustomerDetails customerDetails) {
        super(transactionDetails, null);
        this.customerDetails = customerDetails;
    }

    /**
     * VtDirectChargeRequest constructor
     * @param transactionDetails    {@link id.co.veritrans.mdk.v1.gateway.model.TransactionDetails Transaction details}
     * @param transactionItems      List of {@link id.co.veritrans.mdk.v1.gateway.model.TransactionItem transaction item}
     * @param customerDetails       Transaction {@link id.co.veritrans.mdk.v1.gateway.model.CustomerDetails customer details}
     */
    public VtDirectChargeRequest(final TransactionDetails transactionDetails, final List<TransactionItem> transactionItems, final CustomerDetails customerDetails) {
        super(transactionDetails, transactionItems);
        this.customerDetails = customerDetails;
    }

    /**
     * Get transaction payment method
     * @return the payment method String representation.
     */
    @JsonProperty("payment_type")
    public abstract String getPaymentMethod();

    /**
     * Get transaction customer details
     * @return Transaction {@link id.co.veritrans.mdk.v1.gateway.model.CustomerDetails customer details}
     */
    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    /**
     * Set transaction customer details
     * @param customerDetails Transaction {@link id.co.veritrans.mdk.v1.gateway.model.CustomerDetails customer details}
     */
    public void setCustomerDetails(final CustomerDetails customerDetails) {
        this.customerDetails = customerDetails;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        final VtDirectChargeRequest that = (VtDirectChargeRequest) o;

        if (customerDetails != null ? !customerDetails.equals(that.customerDetails) : that.customerDetails != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (customerDetails != null ? customerDetails.hashCode() : 0);
        return result;
    }
}
