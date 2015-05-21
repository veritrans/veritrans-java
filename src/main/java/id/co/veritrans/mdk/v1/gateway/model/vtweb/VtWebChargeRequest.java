package id.co.veritrans.mdk.v1.gateway.model.vtweb;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.co.veritrans.mdk.v1.gateway.model.AbstractVtRequest;
import id.co.veritrans.mdk.v1.gateway.model.CustomerDetails;
import id.co.veritrans.mdk.v1.gateway.model.TransactionDetails;
import id.co.veritrans.mdk.v1.gateway.model.TransactionItem;

import java.util.List;

/**
 * VtWeb charge request parameter used to make a VtWeb charge request.
 * Created by gde on 5/20/15.
 */
public class VtWebChargeRequest extends AbstractVtRequest {

    /**
     * Internal usage.
     */
    @JsonProperty
    private final String paymentType = "vtweb";
    /**
     * The {@link id.co.veritrans.mdk.v1.gateway.model.vtweb.VtWebParam} describing the configuration for the charge
     * request.
     */
    private VtWebParam vtWeb;
    /**
     * Details regarding with the customer information. (Optional).
     */
    private CustomerDetails customerDetails;

    public VtWebChargeRequest() {
    }

    public VtWebChargeRequest(final TransactionDetails transactionDetails, final List<TransactionItem> itemDetails, final VtWebParam vtWeb) {
        super(transactionDetails, itemDetails);
        this.vtWeb = vtWeb;
    }

    public VtWebParam getVtWeb() {
        return vtWeb;
    }

    public void setVtWeb(final VtWebParam vtWeb) {
        this.vtWeb = vtWeb;
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
        if (!super.equals(o)) return false;

        final VtWebChargeRequest that = (VtWebChargeRequest) o;

        if (vtWeb != null ? !vtWeb.equals(that.vtWeb) : that.vtWeb != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (vtWeb != null ? vtWeb.hashCode() : 0);
        return result;
    }
}
