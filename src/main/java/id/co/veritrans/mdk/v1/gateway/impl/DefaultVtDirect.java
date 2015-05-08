package id.co.veritrans.mdk.v1.gateway.impl;

import id.co.veritrans.mdk.v1.gateway.model.vtdirect.VtDirectChargeParam;
import id.co.veritrans.mdk.v1.gateway.VtDirect;
import id.co.veritrans.mdk.v1.gateway.VtGatewaySession;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;

/**
 * Created by gde on 5/4/15.
 */
public class DefaultVtDirect extends DefaultVtGateway implements VtDirect {

    public DefaultVtDirect(final VtGatewaySession vtGatewaySession) {
        super(vtGatewaySession);
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
