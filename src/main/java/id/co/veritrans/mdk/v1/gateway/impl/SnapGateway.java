package id.co.veritrans.mdk.v1.gateway.impl;

import id.co.veritrans.mdk.v1.VtGatewayConfig;
import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.VtGateway;
import id.co.veritrans.mdk.v1.gateway.VtGatewaySession;
import id.co.veritrans.mdk.v1.gateway.model.GetStatusParameter;
import id.co.veritrans.mdk.v1.gateway.model.SnapResponse;
import id.co.veritrans.mdk.v1.gateway.model.StatusRequest;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;
import id.co.veritrans.mdk.v1.gateway.model.snap.SnapChargeRequest;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.CreditCardFullPanRequest;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.VtDirectChargeRequest;
import id.co.veritrans.mdk.v1.helper.JsonUtil;
import id.co.veritrans.mdk.v1.helper.StringConstant;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

/**
 * Gateway for Snap
 */
public class SnapGateway extends DefaultVtGateway implements VtGateway {
    private VtGatewayConfig vtGatewayConfig;

    public SnapGateway(VtGatewaySession vtGatewaySession) {
        super(vtGatewaySession);
        this.vtGatewayConfig = this.getVtGatewaySession().getVtGatewayConfig();
    }

    /**
     * Generate a token for being used for checkout using Snap's front end view
     * @param snapRequest
     * @return snap response object
     * @throws RestClientException
     */
    public SnapResponse getToken(SnapChargeRequest snapRequest) throws RestClientException {
        final String url = "snap/v1/transactions";
        return getVtGatewaySession().getRestClient().post(SnapResponse.class, url, snapRequest);
    }

    @Override
    public VtResponse approve(String orderId) throws RestClientException, UnsupportedEncodingException {
        throw new RestClientException("Not supported request");
    }

    @Override
    public VtResponse status(String orderId) throws RestClientException, UnsupportedEncodingException {
        throw new RestClientException("Not supported request");
    }

    @Override
    public VtResponse cancel(String orderId) throws RestClientException, UnsupportedEncodingException {
        throw new RestClientException("Not supported request");
    }

    @Override
    public VtResponse status(StatusRequest statusRequest) throws RestClientException, UnsupportedEncodingException {
        throw new RestClientException("Not supported request");
    }

    @Override
    public VtResponse queryStatus(GetStatusParameter getStatusParameter) throws RestClientException, URISyntaxException {
        throw new RestClientException("Not supported request");
    }
}
