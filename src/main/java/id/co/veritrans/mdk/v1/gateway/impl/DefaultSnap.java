package id.co.veritrans.mdk.v1.gateway.impl;

import id.co.veritrans.mdk.v1.VtGatewayConfig;
import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.Snap;
import id.co.veritrans.mdk.v1.gateway.VtGateway;
import id.co.veritrans.mdk.v1.gateway.VtGatewaySession;
import id.co.veritrans.mdk.v1.gateway.model.GetStatusParameter;
import id.co.veritrans.mdk.v1.gateway.model.SnapResponse;
import id.co.veritrans.mdk.v1.gateway.model.StatusRequest;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;
import id.co.veritrans.mdk.v1.gateway.model.snap.SnapChargeRequest;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

/**
 * Gateway for Snap
 */
public class DefaultSnap implements Snap {
    private VtGatewayConfig vtGatewayConfig;
    private final VtGatewaySession vtGatewaySession;

    public DefaultSnap(VtGatewaySession vtGatewaySession) {
        this.vtGatewaySession = vtGatewaySession;
        this.vtGatewayConfig = vtGatewaySession.getVtGatewayConfig();
    }

    /**
     * Generate a token for being used for checkout using Snap's front end view
     * @param snapRequest
     * @return snap response object
     * @throws RestClientException
     */
    public SnapResponse getToken(SnapChargeRequest snapRequest) throws RestClientException {
        SnapResponse response;

        // change the default URL for SnapGateway to use Snap
        this.vtGatewaySession.setBaseUrl(vtGatewayConfig.getEnvironmentType().getSnapUrl());

        try {
            final String url = "snap/v1/transactions";
            response = vtGatewaySession.getRestClient().post(SnapResponse.class, url, snapRequest);
        } finally {
            this.vtGatewaySession.setBaseUrl(vtGatewayConfig.getEnvironmentType().getBaseUrl());
        }

        return response;
    }
}
