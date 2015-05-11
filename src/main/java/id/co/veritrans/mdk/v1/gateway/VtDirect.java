package id.co.veritrans.mdk.v1.gateway;

import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.VtDirectChargeRequest;

/**
 * Created by gde on 4/30/15.
 */
public interface VtDirect extends VtGateway {

    /**
     * Send a payment charge requests to Veritrans.
     * @param vtDirectChargeRequest Veritrans charging message request. {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.CreditCardRequest Credit card request}, {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.BankTransferRequest bank transfer request}, {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.MandiriClickpayRequest mandiri clickpay request}. {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.CimbClicksRequest cimb clicks request}, or {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.BriEpayRequest bri epay request}.
     * @return {@link id.co.veritrans.mdk.v1.gateway.model.VtResponse Veritrans response} for charging transaction
     */
    VtResponse charge(VtDirectChargeRequest vtDirectChargeRequest) throws RestClientException;

    /**
     * Capture a previously authorized credit card charge request.
     * @param transactionId
     * @param amount
     * @return
     */
    VtResponse capture(String transactionId, Long amount) throws RestClientException;
}
