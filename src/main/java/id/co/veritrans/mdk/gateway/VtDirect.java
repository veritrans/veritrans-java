package id.co.veritrans.mdk.gateway;

import id.co.veritrans.mdk.gateway.model.vtdirect.VtDirectChargeParam;
import id.co.veritrans.mdk.gateway.model.VtResponse;

/**
 * Created by gde on 4/30/15.
 */
public interface VtDirect extends VtGateway {

    /**
     * Send a payment charge requests to Veritrans.
     * @param vtDirectChargeParam
     * @return
     */
    VtResponse charge(VtDirectChargeParam vtDirectChargeParam);
}
