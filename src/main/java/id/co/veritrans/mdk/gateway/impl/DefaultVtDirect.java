package id.co.veritrans.mdk.gateway.impl;

import id.co.veritrans.mdk.VtGatewayConfig;
import id.co.veritrans.mdk.VtGatewayFactory;
import id.co.veritrans.mdk.gateway.VtDirect;
import id.co.veritrans.mdk.gateway.model.VtResponse;
import id.co.veritrans.mdk.gateway.model.vtdirect.VtDirectChargeParam;

/**
 * Created by gde on 5/4/15.
 */
public class DefaultVtDirect extends DefaultVtGateway implements VtDirect {

    public DefaultVtDirect(final VtGatewayConfig vtGatewayConfig, final VtGatewayFactory.Session session) {
        super(vtGatewayConfig, session);
    }

    @Override
    public VtResponse charge(VtDirectChargeParam vtDirectChargeParam) {
        return null;
    }

    @Override
    public VtResponse capture(final String transactionId, final Long amount) {
        return null;
    }
}
