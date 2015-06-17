package id.co.veritrans.mdk.v1.gateway.impl;

import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.VtGateway;
import id.co.veritrans.mdk.v1.gateway.VtGatewaySession;
import id.co.veritrans.mdk.v1.gateway.model.GetStatusParameter;
import id.co.veritrans.mdk.v1.gateway.model.StatusRequest;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;
import id.co.veritrans.mdk.v1.helper.StringConstant;
import org.apache.http.client.utils.URIBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
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

    @Override
    public VtResponse approve(final String orderId) throws RestClientException, UnsupportedEncodingException {
        final String safeOrderId = URLEncoder.encode(orderId, "UTF-8");
        final String url = safeOrderId + "/" + StringConstant.APPROVE;

        return getVtGatewaySession().getRestClient().post(url);
    }

    @Override
    public VtResponse status(final String orderId) throws RestClientException, UnsupportedEncodingException {
        final String safeOrderId = URLEncoder.encode(orderId, "UTF-8");
        final String url = safeOrderId + "/" + StringConstant.GET_STATUS;

        return getVtGatewaySession().getRestClient().get(url);
    }

    @Override
    public VtResponse cancel(final String orderId) throws RestClientException, UnsupportedEncodingException {
        final String safeOrderId = URLEncoder.encode(orderId, "UTF-8");
        final String url = safeOrderId + "/" + StringConstant.CANCEL;

        return getVtGatewaySession().getRestClient().post(url);
    }

    /**
     * Get the status of list transaction using orderId from the charge request.
     *
     * @param statusRequest {@link id.co.veritrans.mdk.v1.gateway.model.StatusRequest Status request}
     * @return {@link id.co.veritrans.mdk.v1.gateway.model.VtResponse Veritrans response}
     * @throws id.co.veritrans.mdk.v1.exception.RestClientException when an exception was occurred during
     *                                                              executing the request.
     * @throws java.io.UnsupportedEncodingException                 when UTF-8 Encoding is not available.
     */
    @Override
    public VtResponse status(StatusRequest statusRequest) throws RestClientException, UnsupportedEncodingException {
        final String url = StringConstant.TRANSACTIONS;
        return getVtGatewaySession().getRestClient().post(url, statusRequest);
    }

    /**
     * Get transaction status using several query parameter
     *
     * @param getStatusParameter {@link id.co.veritrans.mdk.v1.gateway.model.GetStatusParameter Get status parameter}
     * @return {@link id.co.veritrans.mdk.v1.gateway.model.VtResponse Veritrans response}
     * @throws id.co.veritrans.mdk.v1.exception.RestClientException when an exception was occurred during executing the request.
     */
    @Override
    public VtResponse queryStatus(GetStatusParameter getStatusParameter) throws RestClientException, URISyntaxException {
        final URI uri = new URIBuilder(StringConstant.TRANSACTIONS).addParameters(getStatusParameter.toUrlParameter()).build();
        return getVtGatewaySession().getRestClient().get(uri);
    }
}
