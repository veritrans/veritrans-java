package id.co.veritrans.mdk.v1;

import id.co.veritrans.mdk.v1.config.EnvironmentType;
import id.co.veritrans.mdk.v1.config.HttpConfig;

public class VtGatewayConfigBuilder {

    private EnvironmentType environmentType;
    private String serverKey;
    private String clientKey;
    private HttpConfig httpConfig;

    public VtGatewayConfigBuilder setEnvironmentType(final EnvironmentType environmentType) {
        this.environmentType = environmentType;
        return this;
    }

    public VtGatewayConfigBuilder setServerKey(final String serverKey) {
        this.serverKey = serverKey;
        return this;
    }

    public VtGatewayConfigBuilder setClientKey(final String clientKey) {
        this.clientKey = clientKey;
        return this;
    }

    public VtGatewayConfigBuilder setHttpConfig(final HttpConfig httpConfig) {
        this.httpConfig = httpConfig;
        return this;
    }

    public VtGatewayConfig createVtGatewayConfig() {
        return new VtGatewayConfig(environmentType, serverKey, clientKey, httpConfig);
    }
}
