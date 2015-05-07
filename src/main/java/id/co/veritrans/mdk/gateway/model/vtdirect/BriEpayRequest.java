package id.co.veritrans.mdk.gateway.model.vtdirect;

import id.co.veritrans.mdk.gateway.model.PaymentMethod;

/**
 * Created by andes on 5/7/15.
 */
public class BriEpayRequest extends VtDirectChargeParam {

    @Override
    public PaymentMethod getPaymentMethod() {
        return PaymentMethod.BRI_EPAY;
    }
}
