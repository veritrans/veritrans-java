package id.co.veritrans.mdk.v1.net;

import id.co.veritrans.mdk.gateway.model.VtResponse;
import id.co.veritrans.mdk.v1.exception.VtConnectionException;
import id.co.veritrans.mdk.v1.gateway.model.VtRequest;

/**
 * Created by gde on 5/8/15.
 */
public interface VtRestClient {

    VtResponse get(String url) throws VtConnectionException;

    VtResponse post(String url) throws VtConnectionException;

    VtResponse post(String url, VtRequest vtRequest) throws VtConnectionException;
}
