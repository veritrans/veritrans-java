package id.co.veritrans.mdk.gateway.impl;

import id.co.veritrans.mdk.VtGatewayConfig;
import id.co.veritrans.mdk.gateway.VtWeb;

/**
 * Created by gde on 5/7/15.
 */
public class DefaultVtWeb extends DefaultVtGateway implements VtWeb {

    public DefaultVtWeb(final VtGatewayConfig vtGatewayConfig) {
        super(vtGatewayConfig);
    }
}
