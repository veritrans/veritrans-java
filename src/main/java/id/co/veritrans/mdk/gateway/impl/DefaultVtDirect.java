package id.co.veritrans.mdk.gateway.impl;

import id.co.veritrans.mdk.VtGateway;
import id.co.veritrans.mdk.gateway.VtDirect;
import id.co.veritrans.mdk.gateway.model.vtdirect.VtDirectChargeParam;
import id.co.veritrans.mdk.gateway.model.VtResponse;

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

    public VtGateway getVtGateway() {
        return vtGateway;
    }

    @Override
    public VtResponse charge(VtDirectChargeParam vtDirectChargeParam) {
        return null;
    }

    @Override
    public VtResponse capture(String transactionId, Long amount) {
        return null;
    }

    @Override
    public VtResponse status(String orderId) {
        return null;
    }

    @Override
    public VtResponse approve(String orderId) {
        return null;
    }
}
