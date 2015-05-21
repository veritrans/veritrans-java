package id.co.veritrans.mdk.v1.gateway.impl;

import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.VtGatewaySession;
import id.co.veritrans.mdk.v1.gateway.VtWeb;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;
import id.co.veritrans.mdk.v1.gateway.model.vtweb.VtWebChargeRequest;
import id.co.veritrans.mdk.v1.helper.StringConstant;

/**
 * Created by gde on 5/7/15.
 */
public class DefaultVtWeb extends DefaultVtGateway implements VtWeb {

    public DefaultVtWeb(final VtGatewaySession vtGatewaySession) {
        super(vtGatewaySession);
    }

    @Override
    public VtResponse charge(final VtWebChargeRequest vtWebChargeRequest) throws RestClientException {
        final String url = StringConstant.CHARGE;
        return getVtGatewaySession().getRestClient().post(url, vtWebChargeRequest);
    }
}
