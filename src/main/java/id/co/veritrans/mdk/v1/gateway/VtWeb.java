package id.co.veritrans.mdk.v1.gateway;

import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;
import id.co.veritrans.mdk.v1.gateway.model.vtweb.VtWebChargeRequest;

/**
 * VT-Web class trigger transaction
 */
public interface VtWeb extends VtGateway {

    /**
     * Make a payment charge request to Veritrans Payment API using VT-Web payment type. Veritrans Payment API would
     * return a redirect URL inside the VtResponse object after successful charge request option.
     * The redirectUrl must be opened by the customer's browser,
     * either by redirecting the customer to the redirect URL or explicitly instruct the customer to open the redirectUrl.
     *
     * @param vtWebChargeRequest the request parameters used to customize the request.
     * @return VtResponse object with a statusCode indicating the result of the charge request operation.
     * @throws RestClientException hen an exception was occurred during executing the request.
     */
    VtResponse charge(VtWebChargeRequest vtWebChargeRequest) throws RestClientException;
}
