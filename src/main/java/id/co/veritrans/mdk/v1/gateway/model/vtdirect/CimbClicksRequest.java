package id.co.veritrans.mdk.v1.gateway.model.vtdirect;

import id.co.veritrans.mdk.v1.gateway.model.CustomerDetails;
import id.co.veritrans.mdk.v1.gateway.model.PaymentMethod;
import id.co.veritrans.mdk.v1.gateway.model.TransactionDetails;
import id.co.veritrans.mdk.v1.gateway.model.TransactionItem;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.CimbClicks;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by gde on 5/5/15.
 */
public class CimbClicksRequest extends VtDirectChargeRequest {

    @Valid
    @NotNull
    private CimbClicks cimbClicks;

    public CimbClicksRequest() {
    }

    public CimbClicksRequest(final TransactionDetails transactionDetails, final List<TransactionItem> transactionItems, final CustomerDetails customerDetails, final CimbClicks cimbClicks) {
        super(transactionDetails, transactionItems, customerDetails);
        this.cimbClicks = cimbClicks;
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return PaymentMethod.CIMB_CLICKS;
    }

    public CimbClicks getCimbClicks() {
        return cimbClicks;
    }

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
