package id.co.veritrans.mdk.v1.gateway;

import id.co.veritrans.mdk.v1.VtGatewayConfig;
import id.co.veritrans.mdk.v1.net.VtRestClient;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;

/**
 * VT-Gateway session
 */
public interface VtGatewaySession {

    VtGatewayConfig getVtGatewayConfig();

    CloseableHttpClient getHttpClient();

    VtRestClient getRestClient();

    void destroy() throws IOException;
}
