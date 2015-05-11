package id.co.veritrans.mdk.v1.gateway.impl;

import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.VtGateway;
import id.co.veritrans.mdk.v1.gateway.VtGatewaySession;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;
import id.co.veritrans.mdk.v1.helper.StringConstant;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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

    public VtResponse approve(final String orderId) throws RestClientException, UnsupportedEncodingException {
        final String safeOrderId = URLEncoder.encode(orderId, "UTF-8");
        final VtGatewaySession vtGatewaySession = getVtGatewaySession();
        final String url = vtGatewaySession.getVtGatewayConfig().getEnvironmentType().getBaseUrl() +
                "/" +
                safeOrderId +
                "/" +
                StringConstant.APPROVE;

        return getVtGatewaySession().getRestClient().post(url);
    }

    public VtResponse status(final String orderId) throws RestClientException, UnsupportedEncodingException {
        final String safeOrderId = URLEncoder.encode(orderId, "UTF-8");
        final VtGatewaySession vtGatewaySession = getVtGatewaySession();
        final String url = vtGatewaySession.getVtGatewayConfig().getEnvironmentType().getBaseUrl() +
                "/" +
                safeOrderId +
                "/" +
                StringConstant.GET_STATUS;

        return getVtGatewaySession().getRestClient().post(url);
    }

    public VtResponse cancel(final String orderId) throws RestClientException, UnsupportedEncodingException {
        final String safeOrderId = URLEncoder.encode(orderId, "UTF-8");
        final VtGatewaySession vtGatewaySession = getVtGatewaySession();
        final String url = vtGatewaySession.getVtGatewayConfig().getEnvironmentType().getBaseUrl() +
                "/" +
                safeOrderId +
                "/" +
                StringConstant.CANCEL;

        return getVtGatewaySession().getRestClient().post(url);
    }
}
