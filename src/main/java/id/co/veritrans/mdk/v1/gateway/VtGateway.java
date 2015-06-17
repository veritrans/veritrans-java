package id.co.veritrans.mdk.v1.gateway;

import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.model.GetStatusParameter;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;
import id.co.veritrans.mdk.v1.gateway.model.StatusRequest;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

/**
 * VT Gateway.
 */
public interface VtGateway {

    /**
     * Approve a charge transaction which has <b>challenge</b> ChargeResult.
     *
     * @param orderId {@link id.co.veritrans.mdk.v1.gateway.model.VtResponse#orderId Order id} of transaction
     * @return {@link id.co.veritrans.mdk.v1.gateway.model.VtResponse Veritrans response}
     * @throws RestClientException          when an exception was occurred during
     *                                      executing the request.
     * @throws UnsupportedEncodingException when UTF-8 Encoding is not available.
     */
    VtResponse approve(String orderId) throws RestClientException, UnsupportedEncodingException;

    /**
     * Get the status of a transaction using the orderId from the charge request.
     *
     * @param orderId {@link id.co.veritrans.mdk.v1.gateway.model.VtResponse#orderId Order id} of transaction
     * @return {@link id.co.veritrans.mdk.v1.gateway.model.VtResponse Veritrans response}
     * @throws RestClientException          when an exception was occurred during
     *                                      executing the request.
     * @throws UnsupportedEncodingException when UTF-8 Encoding is not available.
     */
    VtResponse status(String orderId) throws RestClientException, UnsupportedEncodingException;

    /**
     * Cancel a charge transaction which has been processed.
     *
     * @param orderId {@link id.co.veritrans.mdk.v1.gateway.model.VtResponse#orderId Order id} of the transaction
     * @return {@link id.co.veritrans.mdk.v1.gateway.model.VtResponse Veritrans response}
     * @throws RestClientException          when an exception was occurred during
     *                                      executing the request.
     * @throws UnsupportedEncodingException when UTF-8 Encoding is not available.
     */
    VtResponse cancel(String orderId) throws RestClientException, UnsupportedEncodingException;

    /**
     * Get the status of list transaction using orderId from the charge request.
     *
     * @param statusRequest {@link id.co.veritrans.mdk.v1.gateway.model.StatusRequest Status request}
     * @return {@link id.co.veritrans.mdk.v1.gateway.model.VtResponse Veritrans response}
     * @throws RestClientException          when an exception was occurred during
     *                                      executing the request.
     * @throws UnsupportedEncodingException when UTF-8 Encoding is not available.
     */
    VtResponse status(StatusRequest statusRequest) throws RestClientException, UnsupportedEncodingException;

    /**
     * Get transaction status using several query parameter
     *
     * @param getStatusParameter {@link id.co.veritrans.mdk.v1.gateway.model.GetStatusParameter Get status parameter}
     * @return {@link id.co.veritrans.mdk.v1.gateway.model.VtResponse Veritrans response}
     * @throws RestClientException when an exception was occurred during executing the request.
     */
    VtResponse queryStatus(GetStatusParameter getStatusParameter) throws RestClientException, URISyntaxException;
}
