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
 * Mandiri clickpay request class
 */
public class MandiriClickpayRequest extends VtDirectChargeRequest {

    @Valid
    @NotNull
    private MandiriClickpay mandiriClickpay;

    /**
     * Mandiri clickpay request constructor
     */
    public MandiriClickpayRequest() {
    }

    /**
     * Mandiri clickpay request constructor
     *
     * @param transactionDetails    {@link id.co.veritrans.mdk.v1.gateway.model.TransactionDetails Transaction details}
     * @param transactionItems      List of {@link id.co.veritrans.mdk.v1.gateway.model.TransactionItem transaction item}
     * @param customerDetails       {@link id.co.veritrans.mdk.v1.gateway.model.CustomerDetails Customer details}
     * @param mandiriClickpay       {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.MandiriClickpay Mandiri clickpay detail request}
     */
    public MandiriClickpayRequest(final TransactionDetails transactionDetails, final List<TransactionItem> transactionItems, final CustomerDetails customerDetails, final MandiriClickpay mandiriClickpay) {
        super(transactionDetails, transactionItems, customerDetails);
        this.mandiriClickpay = mandiriClickpay;
    }

    /**
     * Get mandiri clickpay payment method
     * @return {@link id.co.veritrans.mdk.v1.gateway.model.PaymentMethod#MANDIRI_CLICKPAY Mandiri clickpay payment method}
     */
    @Override
    public PaymentMethod getPaymentMethod() {
        return PaymentMethod.MANDIRI_CLICKPAY;
    }

    /**
     * Get mandiri clickpay detail request
     * @return {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.MandiriClickpay Mandiri clickpay} detail
     */
    public MandiriClickpay getMandiriClickpay() {
        return mandiriClickpay;
    }

    /**
     * Set mandiri clickpay detail request
     * @param mandiriClickpay {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.MandiriClickpay Mandiri clickpay} detail
     */
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
