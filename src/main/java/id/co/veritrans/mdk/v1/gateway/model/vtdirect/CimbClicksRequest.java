package id.co.veritrans.mdk.v1.gateway.model.vtdirect;

import id.co.veritrans.mdk.v1.gateway.model.CustomerDetails;
import id.co.veritrans.mdk.v1.gateway.model.TransactionDetails;
import id.co.veritrans.mdk.v1.gateway.model.TransactionItem;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.CimbClicks;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * CIMB clicks request
 */
public class CimbClicksRequest extends VtDirectChargeRequest {

    @Valid
    @NotNull
    private CimbClicks cimbClicks;

    /**
     * CIMB clicks request constructor
     */
    public CimbClicksRequest() {
    }

    /**
     * CIMB clicks request constructor
     * @param transactionDetails    {@link id.co.veritrans.mdk.v1.gateway.model.TransactionDetails Transaction details}
     * @param transactionItems      List of {@link id.co.veritrans.mdk.v1.gateway.model.TransactionItem transaction item}
     * @param customerDetails       {@link id.co.veritrans.mdk.v1.gateway.model.CustomerDetails Customer details}
     * @param cimbClicks            {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.CimbClicks CIMB clicks} details
     */
    public CimbClicksRequest(final TransactionDetails transactionDetails, final List<TransactionItem> transactionItems, final CustomerDetails customerDetails, final CimbClicks cimbClicks) {
        super(transactionDetails, transactionItems, customerDetails);
        this.cimbClicks = cimbClicks;
    }

    /**
     * Get CIMB clicks payment method
     * @return CIMB Clicks String (cimb_clicks)
     */
    @Override
    public String getPaymentMethod() {
        return "cimb_clicks";
    }

    /**
     * Get CIMB clicks detail
     * @return {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.CimbClicks CIMB clicks} detail
     */
    public CimbClicks getCimbClicks() {
        return cimbClicks;
    }

    /**
     * Set CIMB clicks detail
     * @param cimbClicks {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.CimbClicks CIMB clicks} detail
     */
    public void setCimbClicks(final CimbClicks cimbClicks) {
        this.cimbClicks = cimbClicks;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        final CimbClicksRequest that = (CimbClicksRequest) o;

        if (cimbClicks != null ? !cimbClicks.equals(that.cimbClicks) : that.cimbClicks != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (cimbClicks != null ? cimbClicks.hashCode() : 0);
        return result;
    }
}
