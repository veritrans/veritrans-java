package id.co.veritrans.mdk.v1.gateway.impl;

import id.co.veritrans.mdk.v1.gateway.VtGateway;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;
import id.co.veritrans.mdk.v1.gateway.VtGatewaySession;

/**
 * Created by gde on 5/4/15.
 */
public abstract class DefaultVtGateway implements VtGateway {

    private final VtGatewaySession vtGatewaySession;

    public DefaultVtGateway(final VtGatewaySession vtGatewaySession) {
        this.vtGatewaySession = vtGatewaySession;
    }

    public VtGatewaySession getVtGatewaySession() {
        return vtGatewaySession;
    }

    @Override
    public VtResponse approve(final String orderId) {
        return null;
    }

    @Override
    public VtResponse status(final String orderId) {
        return null;
    }

    @Override
    public VtResponse cancel(final String orderId) {
        return null;
    }
}
