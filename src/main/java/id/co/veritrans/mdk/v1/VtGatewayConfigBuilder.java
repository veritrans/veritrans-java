package id.co.veritrans.mdk.v1;

import id.co.veritrans.mdk.v1.config.EnvironmentType;
import id.co.veritrans.mdk.v1.config.ProxyConfig;

public class VtGatewayConfigBuilder {
    private EnvironmentType environmentType;
    private String serverKey;
    private String clientKey;
    private int maxConnectionPoolSize;
    private ProxyConfig proxyConfig;

    public VtGatewayConfigBuilder setEnvironmentType(EnvironmentType environmentType) {
        this.environmentType = environmentType;
        return this;
    }

    public VtGatewayConfigBuilder setServerKey(String serverKey) {
        this.serverKey = serverKey;
        return this;
    }

    public VtGatewayConfigBuilder setClientKey(String clientKey) {
        this.clientKey = clientKey;
        return this;
    }

    public VtGatewayConfigBuilder setMaxConnectionPoolSize(int maxConnectionPoolSize) {
        this.maxConnectionPoolSize = maxConnectionPoolSize;
        return this;
    }

    public VtGatewayConfigBuilder setProxyConfig(ProxyConfig proxyConfig) {
        this.proxyConfig = proxyConfig;
        return this;
    }

    public VtGatewayConfig createVtGatewayConfig() {
        return new VtGatewayConfig(environmentType, serverKey, clientKey, maxConnectionPoolSize, proxyConfig);
    }
}
