package id.co.veritrans.mdk.v1.gateway;

import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;

import java.io.UnsupportedEncodingException;

/**
 * VT Gateway
 */
public interface VtGateway {

    /**
     * Approve a charge transaction which has <b>challenge</b> ChargeResult.
     *
     * @param orderId   {@link id.co.veritrans.mdk.v1.gateway.model.VtResponse#orderId Order id} of transaction
     * @return          {@link id.co.veritrans.mdk.v1.gateway.model.VtResponse Veritrans response}
     */
    VtResponse approve(String orderId) throws RestClientException, UnsupportedEncodingException;

    /**
     * Get the status of a transaction using the orderId from the charge request.
     *
     * @param orderId   {@link id.co.veritrans.mdk.v1.gateway.model.VtResponse#orderId Order id} of transaction
     * @return          {@link id.co.veritrans.mdk.v1.gateway.model.VtResponse Veritrans response}
     */
    VtResponse status(String orderId) throws RestClientException, UnsupportedEncodingException;

    /**
     * Cancel a charge transaction which has been approved
     *
     * @param orderId   {@link id.co.veritrans.mdk.v1.gateway.model.VtResponse#orderId Order id} of transaction
     * @return          {@link id.co.veritrans.mdk.v1.gateway.model.VtResponse Veritrans response}
     * @throws RestClientException
     * @throws UnsupportedEncodingException
     */
    VtResponse cancel(String orderId) throws RestClientException, UnsupportedEncodingException;
}
