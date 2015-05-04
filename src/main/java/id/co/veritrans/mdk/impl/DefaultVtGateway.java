package id.co.veritrans.mdk.impl;

import id.co.veritrans.mdk.VtGateway;
import id.co.veritrans.mdk.VtGatewayConfig;
import id.co.veritrans.mdk.gateway.VtDirect;
import id.co.veritrans.mdk.gateway.VtWeb;
import id.co.veritrans.mdk.gateway.impl.DefaultVtDirect;

/**
 * Created by gde on 5/4/15.
 */
public class DefaultVtGateway implements VtGateway {

    private VtGatewayConfig vtGatewayConfig;

    public DefaultVtGateway() {
    }

    public DefaultVtGateway(final VtGatewayConfig vtGatewayConfig) {
        this.vtGatewayConfig = vtGatewayConfig;
    }

    @Override
    public VtGatewayConfig getVtGatewayConfig() {
        return vtGatewayConfig;
    }

    public void setVtGatewayConfig(final VtGatewayConfig vtGatewayConfig) {
        this.vtGatewayConfig = vtGatewayConfig;
    }

    @Override
    public VtDirect vtDirect() {
        return new DefaultVtDirect(this);
    }

    @Override
    public VtWeb vtWeb() {
        return null;
    }
}
