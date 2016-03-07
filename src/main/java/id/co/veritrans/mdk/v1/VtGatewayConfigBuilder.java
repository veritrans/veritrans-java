package id.co.veritrans.mdk.v1;

import id.co.veritrans.mdk.v1.config.EnvironmentType;
import id.co.veritrans.mdk.v1.config.ProxyConfig;

/**
 * {@link id.co.veritrans.mdk.v1.VtGatewayConfig VtGatewayConfig} builder class
 */
public class VtGatewayConfigBuilder {
    private EnvironmentType environmentType;
    private String serverKey;
    private String clientKey;
    private int maxConnectionPoolSize = 16;
    private ProxyConfig proxyConfig;
    private int connectTimeout = DEFAULT_CONNECT_TIMEOUT;
    private int socketTimeout = DEFAULT_SOCKET_TIMEOUT;

    /**
     * Default connect timeout
     */
    public static final int DEFAULT_CONNECT_TIMEOUT = 5000;

    /**
     * Default socket timeout
     */
    public static final int DEFAULT_SOCKET_TIMEOUT = 30000;

    /**
     * Set VtGatewayConfig environment type
     * @param environmentType   VtGatewayConfig {@link id.co.veritrans.mdk.v1.config.EnvironmentType environment type}
     * @return                  {@link id.co.veritrans.mdk.v1.VtGatewayConfigBuilder VtGatewayConfigBuilder}
     */
    public VtGatewayConfigBuilder setEnvironmentType(EnvironmentType environmentType) {
        this.environmentType = environmentType;
        return this;
    }

    /**
     * Set VtGatewayConfig server key
     * @param serverKey VtGatewayConfig server key
     * @return          {@link id.co.veritrans.mdk.v1.VtGatewayConfigBuilder VtGatewayConfigBuilder}
     */
    public VtGatewayConfigBuilder setServerKey(String serverKey) {
        this.serverKey = serverKey;
        return this;
    }

    /**
     * Set VtGatewayConfig client key
     * @param clientKey VtGatewayConfig client key
     * @return          {@link id.co.veritrans.mdk.v1.VtGatewayConfigBuilder VtGatewayConfigBuilder}
     */
    public VtGatewayConfigBuilder setClientKey(String clientKey) {
        this.clientKey = clientKey;
        return this;
    }

    /**
     * Set VtGatewayConfig max connection pool size
     * @param maxConnectionPoolSize Max http client connection pool size
     * @return                      {@link id.co.veritrans.mdk.v1.VtGatewayConfigBuilder VtGatewayConfigBuilder}
     */
    public VtGatewayConfigBuilder setMaxConnectionPoolSize(int maxConnectionPoolSize) {
        this.maxConnectionPoolSize = maxConnectionPoolSize;
        return this;
    }

    /**
     * Set VtGatewayConfig http client connect timeout
     * @param connectTimeout Http client connect timeout
     * @return {@link id.co.veritrans.mdk.v1.VtGatewayConfigBuilder VtGatewayConfigBuilder}
     */
    public VtGatewayConfigBuilder setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    /**
     * Set VtGatewayConfig http client socket timeout
     * @param socketTimeout Http client socket timeout
     * @return {@link id.co.veritrans.mdk.v1.VtGatewayConfigBuilder VtGatewayConfigBuilder}
     */
    public VtGatewayConfigBuilder setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
        return this;
    }

    /**
     * Set VtGatewayConfig proxy configuration
     * @param proxyConfig   {@link id.co.veritrans.mdk.v1.config.ProxyConfig Proxy config}
     * @return              {@link id.co.veritrans.mdk.v1.VtGatewayConfigBuilder VtGatewayConfigBuilder}
     */
    public VtGatewayConfigBuilder setProxyConfig(ProxyConfig proxyConfig) {
        this.proxyConfig = proxyConfig;
        return this;
    }

    /**
     * Build VtGatewayConfig object from builder
     * @return {@link id.co.veritrans.mdk.v1.VtGatewayConfig VtGatewayConfig}
     */
    public VtGatewayConfig createVtGatewayConfig() {
        return new VtGatewayConfig(environmentType, serverKey, clientKey, maxConnectionPoolSize, connectTimeout, socketTimeout, proxyConfig);
    }
}
