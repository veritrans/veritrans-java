package id.co.veritrans.mdk.v1.gateway.model.vtdirect;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.co.veritrans.mdk.v1.gateway.model.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by gde on 5/4/15.
 */
public abstract class VtDirectChargeRequest extends AbstractVtRequest {

    @Valid
    @NotNull
    private CustomerDetails customerDetails;

    public VtDirectChargeRequest() {
    }

    public VtDirectChargeRequest(final TransactionDetails transactionDetails, final List<TransactionItem> transactionItems, final CustomerDetails customerDetails) {
        super(transactionDetails, transactionItems);
        this.customerDetails = customerDetails;
    }

    @JsonProperty("payment_method")
    private String _getPaymentMethod() {
        return getPaymentMethod().getName();
    }

    public abstract PaymentMethod getPaymentMethod();

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
