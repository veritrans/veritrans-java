package id.co.veritrans.mdk.v1.gateway.model.vtdirect;

import id.co.veritrans.mdk.v1.gateway.model.PaymentMethod;

/**
 * Bri epay request
 */
public class BriEpayRequest extends VtDirectChargeRequest {

    /**
     * Get Bri epay payment method
     * @return Bri epay {@link id.co.veritrans.mdk.v1.gateway.model.PaymentMethod#BRI_EPAY payment method}
     */
    @Override
    public PaymentMethod getPaymentMethod() {
        return PaymentMethod.BRI_EPAY;
    }
}
