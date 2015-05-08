package id.co.veritrans.mdk.gateway.impl;

import id.co.veritrans.mdk.VtGatewayConfig;
import id.co.veritrans.mdk.VtGatewayFactory;
import id.co.veritrans.mdk.gateway.VtGateway;
import id.co.veritrans.mdk.gateway.model.VtResponse;

/**
 * Created by gde on 5/4/15.
 */
public abstract class DefaultVtGateway implements VtGateway {

    private VtGatewayConfig vtGatewayConfig;
    private VtGatewayFactory.Session session;

    public DefaultVtGateway(final VtGatewayConfig vtGatewayConfig, final VtGatewayFactory.Session session) {
        this.vtGatewayConfig = vtGatewayConfig;
        this.session = session;
    }

    public VtGatewayConfig getVtGatewayConfig() {
        return vtGatewayConfig;
    }

    public void setVtGatewayConfig(final VtGatewayConfig vtGatewayConfig) {
        this.vtGatewayConfig = vtGatewayConfig;
    }

    public VtGatewayFactory.Session getSession() {
        return session;
    }

    public void setSession(final VtGatewayFactory.Session session) {
        this.session = session;
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
