package id.co.veritrans.mdk.v1.gateway.model.vtdirect;

/**
 * Bri Epay request
 */
public class BriEpayRequest extends VtDirectChargeRequest {

    /**
     * Get Bri Epay payment method
     * @return Bri Epay String (bri_epay)
     */
    @Override
    public String getPaymentMethod() {
        return "bri_epay";
    }
}
