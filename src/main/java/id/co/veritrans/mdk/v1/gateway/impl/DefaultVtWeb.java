package id.co.veritrans.mdk.v1.gateway.impl;

import id.co.veritrans.mdk.v1.gateway.VtGatewaySession;
import id.co.veritrans.mdk.v1.gateway.VtWeb;

/**
 * Created by gde on 5/7/15.
 */
public class DefaultVtWeb extends DefaultVtGateway implements VtWeb {

    public DefaultVtWeb(final VtGatewaySession vtGatewaySession) {
        super(vtGatewaySession);
    }
}
