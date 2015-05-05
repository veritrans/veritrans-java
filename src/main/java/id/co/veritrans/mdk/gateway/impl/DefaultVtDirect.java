package id.co.veritrans.mdk.gateway.impl;

import id.co.veritrans.mdk.VtGateway;
import id.co.veritrans.mdk.gateway.VtDirect;
import id.co.veritrans.mdk.gateway.model.VtDirectChargeParam;
import id.co.veritrans.mdk.gateway.model.VtDirectResponse;

/**
 * Created by gde on 5/4/15.
 */
public class DefaultVtDirect implements VtDirect {

    private VtGateway vtGateway;

    public DefaultVtDirect() {
    }

    public DefaultVtDirect(final VtGateway vtGateway) {
        this.vtGateway = vtGateway;
    }

    public void setVtGateway(final VtGateway vtGateway) {
        this.vtGateway = vtGateway;
    }

    @Override
    public VtGateway getVtGateway() {
        return vtGateway;
    }

    @Override
    public VtDirectResponse charge(VtDirectChargeParam vtDirectChargeParam) {
        return null;
    }

    @Override
    public VtDirectResponse capture(String transactionId, Long amount) {
        return null;
    }

    @Override
    public VtDirectResponse status(String orderId) {
        return null;
    }

    @Override
    public VtDirectResponse approve(String orderId) {
        return null;
    }
}
