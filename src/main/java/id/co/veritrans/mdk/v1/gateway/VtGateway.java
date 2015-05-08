package id.co.veritrans.mdk.v1.gateway;

import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;

import java.io.UnsupportedEncodingException;

/**
 * Created by gde on 4/30/15.
 */
public interface VtGateway {

    /**
     * Approve a charge transaction which has <b>challenge</b> ChargeResult.
     *
     * @param orderId
     * @return
     */
    VtResponse approve(String orderId) throws RestClientException, UnsupportedEncodingException;

    /**
     * Get the status of a transaction using the orderId from the charge request.
     *
     * @param orderId
     * @return
     */
    VtResponse status(String orderId) throws RestClientException, UnsupportedEncodingException;

    VtResponse cancel(String orderId) throws RestClientException, UnsupportedEncodingException;
}
