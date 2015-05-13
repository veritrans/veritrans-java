package id.co.veritrans.mdk.v1.gateway;

import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.VtDirectChargeRequest;

/**
 * VT-Direct class for trigger transaction
 */
public interface VtDirect extends VtGateway {

    /**
     * Send a payment charge requests to Veritrans.
     * @param vtDirectChargeRequest Veritrans charging message request. {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.CreditCardRequest Credit card request}, {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.BankTransferRequest bank transfer request}, {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.MandiriClickpayRequest mandiri clickpay request}. {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.CimbClicksRequest cimb clicks request}, or {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.BriEpayRequest bri epay request}.
     * @return {@link id.co.veritrans.mdk.v1.gateway.model.VtResponse Veritrans response}
     * @throws RestClientException when an exception was occurred during
     */
    VtResponse charge(VtDirectChargeRequest vtDirectChargeRequest) throws RestClientException;

    /**
     * Capture a previously authorized credit card charge request.
     * @param transactionId {@link id.co.veritrans.mdk.v1.gateway.model.VtResponse#getTransactionId() Transaction id} of authorized request
     * @param amount        Total amount that will be captured (less or equal then authorized gross amount)
     * @return              {@link id.co.veritrans.mdk.v1.gateway.model.VtResponse Veritrans response}
     * @throws RestClientException when an exception was occurred during
     * executing the request.
     */
    VtResponse capture(String transactionId, Long amount) throws RestClientException;
}
