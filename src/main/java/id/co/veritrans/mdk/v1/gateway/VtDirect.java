package id.co.veritrans.mdk.v1.gateway;

import id.co.veritrans.mdk.v1.gateway.model.VtResponse;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.VtDirectChargeParam;

/**
 * Created by gde on 4/30/15.
 */
public interface VtDirect extends VtGateway {

    /**
     * Send a payment charge requests to Veritrans.
     * @param vtDirectChargeParam Veritrans charging message request. {@link id.co.veritrans.mdk.gateway.model.vtdirect.CreditCardRequest Credit card request}, {@link id.co.veritrans.mdk.gateway.model.vtdirect.BankTransferRequest bank transfer request}, {@link id.co.veritrans.mdk.gateway.model.vtdirect.MandiriClickpayRequest mandiri clickpay request}. {@link id.co.veritrans.mdk.gateway.model.vtdirect.CimbClicksRequest cimb clicks request}, or {@link id.co.veritrans.mdk.gateway.model.vtdirect.BriEpayRequest bri epay request}.
     * @return {@link id.co.veritrans.mdk.gateway.model.VtResponse Veritrans response} for charging transaction
     */
    VtResponse charge(VtDirectChargeParam vtDirectChargeParam);

    /**
     * Capture a previously authorized credit card charge request.
     * @param transactionId
     * @param amount
     * @return
     */
    VtResponse capture(String transactionId, Long amount);
}
