package id.co.veritrans.mdk.v1.gateway.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.VtDirect;
import id.co.veritrans.mdk.v1.gateway.VtGatewaySession;
import id.co.veritrans.mdk.v1.gateway.model.VtRequest;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.VtDirectChargeRequest;
import id.co.veritrans.mdk.v1.helper.StringConstant;

/**
 * Created by gde on 5/4/15.
 */
public class DefaultVtDirect extends DefaultVtGateway implements VtDirect {

    public DefaultVtDirect(final VtGatewaySession vtGatewaySession) {
        super(vtGatewaySession);
    }

    @Override
    public VtResponse charge(VtDirectChargeRequest vtDirectChargeRequest) throws RestClientException {
        final String url = StringConstant.CHARGE;
        return getVtGatewaySession().getRestClient().post(VtResponse.class, url, vtDirectChargeRequest);
    }

    @Override
    public VtResponse capture(final String transactionId, final Long amount) throws RestClientException {
        final String url = StringConstant.CAPTURE;
        return getVtGatewaySession().getRestClient().post(VtResponse.class, url, new VtRequest() {
            @JsonProperty("transaction_id")
            public final String _1 = transactionId;
            @JsonProperty("gross_amount")
            public final Long _2 = amount;
        });
    }
}
