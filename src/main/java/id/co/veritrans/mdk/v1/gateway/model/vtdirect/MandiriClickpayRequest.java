package id.co.veritrans.mdk.v1.gateway.model.vtdirect;

import id.co.veritrans.mdk.v1.gateway.model.CustomerDetails;
import id.co.veritrans.mdk.v1.gateway.model.PaymentMethod;
import id.co.veritrans.mdk.v1.gateway.model.TransactionDetails;
import id.co.veritrans.mdk.v1.gateway.model.TransactionItem;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.MandiriClickpay;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by gde on 5/5/15.
 */
public class MandiriClickpayRequest extends VtDirectChargeParam {

    @Valid
    @NotNull
    private MandiriClickpay mandiriClickpay;

    public MandiriClickpayRequest() {
    }

    public MandiriClickpayRequest(final TransactionDetails transactionDetails, final List<TransactionItem> transactionItems, final CustomerDetails customerDetails, final MandiriClickpay mandiriClickpay) {
        super(transactionDetails, transactionItems, customerDetails);
        this.mandiriClickpay = mandiriClickpay;
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return PaymentMethod.MANDIRI_CLICKPAY;
    }

    public MandiriClickpay getMandiriClickpay() {
        return mandiriClickpay;
    }

    public void setMandiriClickpay(final MandiriClickpay mandiriClickpay) {
        this.mandiriClickpay = mandiriClickpay;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        final MandiriClickpayRequest that = (MandiriClickpayRequest) o;

        if (mandiriClickpay != null ? !mandiriClickpay.equals(that.mandiriClickpay) : that.mandiriClickpay != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (mandiriClickpay != null ? mandiriClickpay.hashCode() : 0);
        return result;
    }
}
