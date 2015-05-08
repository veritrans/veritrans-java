package id.co.veritrans.mdk.v1.net;

import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.model.VtRequest;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;

/**
 * Created by gde on 5/8/15.
 */
public interface VtRestClient {

    VtResponse get(String url) throws RestClientException;

    VtResponse post(String url) throws RestClientException;

    VtResponse post(String url, VtRequest vtRequest) throws RestClientException;
}
