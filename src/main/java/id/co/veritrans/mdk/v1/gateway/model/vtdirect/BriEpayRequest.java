package id.co.veritrans.mdk.v1.gateway.model.vtdirect;

import id.co.veritrans.mdk.v1.gateway.model.PaymentMethod;

/**
 * Bri Epay request
 */
public class BriEpayRequest extends VtDirectChargeRequest {

    /**
     * Get Bri Epay payment method
     * @return Bri epay {@link id.co.veritrans.mdk.v1.gateway.model.PaymentMethod#BRI_EPAY payment method}
     */
    @Override
    public PaymentMethod getPaymentMethod() {
        return PaymentMethod.BRI_EPAY;
    }
}
