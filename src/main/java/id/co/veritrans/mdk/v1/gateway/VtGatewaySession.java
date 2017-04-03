package id.co.veritrans.mdk.v1.gateway;

import id.co.veritrans.mdk.v1.VtGatewayConfig;
import id.co.veritrans.mdk.v1.net.VtRestClient;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.net.URI;

/**
 * VT-Gateway session
 */
public interface VtGatewaySession {

    VtGatewayConfig getVtGatewayConfig();

    CloseableHttpClient getHttpClient();

    VtRestClient getRestClient();

    void setBaseUrl(URI baseUrl);

    void destroy() throws IOException;
}
