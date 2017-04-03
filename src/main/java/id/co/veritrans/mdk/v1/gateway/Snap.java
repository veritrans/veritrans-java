package id.co.veritrans.mdk.v1.gateway;

import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.model.SnapResponse;
import id.co.veritrans.mdk.v1.gateway.model.snap.SnapChargeRequest;

/**
 * Created by adampahlevi on 3/29/17.
 */
public interface Snap {
    SnapResponse getToken(SnapChargeRequest snapRequest) throws RestClientException;
}
