package id.co.veritrans.mdk;

import id.co.veritrans.mdk.gateway.VtDirect;
import id.co.veritrans.mdk.gateway.VtWeb;

/**
 * Created by gde on 4/30/15.
 */
public interface VtGateway {

    VtGatewayConfig getVtGatewayConfig();

    VtDirect vtDirect();

    VtWeb vtWeb();
}
