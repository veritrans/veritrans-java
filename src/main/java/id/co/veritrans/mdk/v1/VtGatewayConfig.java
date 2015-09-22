package id.co.veritrans.mdk.v1;

import id.co.veritrans.mdk.v1.config.EnvironmentType;
import id.co.veritrans.mdk.v1.config.ProxyConfig;

/**
 * Veritrans gateway configuration
 */
public class VtGatewayConfig {

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private final EnvironmentType environmentType;
    private final String serverKey;
    private final String clientKey;
    private final int maxConnectionPoolSize;
    private final ProxyConfig proxyConfig;

    /**
     * Veritrans gateway configuration constructor
     * @param environmentType       {@link id.co.veritrans.mdk.v1.config.EnvironmentType Environment type}
     * @param serverKey             Merchant <a href="https://my.sandbox.veritrans.co.id/login">server key</a>
     * @param clientKey             Merchant <a href="https://my.sandbox.veritrans.co.id/login">client key</a>
     * @param maxConnectionPoolSize Http client max connection pool size
     * @param proxyConfig           Http client {@link id.co.veritrans.mdk.v1.config.ProxyConfig proxy configuration}
     */
    public VtGatewayConfig(EnvironmentType environmentType, String serverKey, String clientKey, int maxConnectionPoolSize, ProxyConfig proxyConfig) {
        this.environmentType = environmentType;
        this.serverKey = serverKey;
        this.clientKey = clientKey;
        this.maxConnectionPoolSize = maxConnectionPoolSize;
        this.proxyConfig = proxyConfig;
    }

    /**
     * Get environment type
     * @return {@link id.co.veritrans.mdk.v1.config.EnvironmentType Environment type}
     */
    public EnvironmentType getEnvironmentType() {
        return environmentType;
    }

    /**
     * Get merchant server key
     * @return Merchant <a href="https://my.sandbox.veritrans.co.id/login">server key</a>
     */
    public String getServerKey() {
        return serverKey;
    }

    /**
     * Get merchant client key
     * @return Merchant <a href="https://my.sandbox.veritrans.co.id/login">client key</a>
     */
    public String getClientKey() {
        return clientKey;
    }

    /**
     * Get http client max connection pool size
     * @return Http client max connection pool size
     */
    public int getMaxConnectionPoolSize() {
        return maxConnectionPoolSize;
    }

    /**
     * Get http client proxy configuration
     * @return Http client {@link id.co.veritrans.mdk.v1.config.ProxyConfig proxy configuration}
     */
    public ProxyConfig getProxyConfig() {
        return proxyConfig;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VtGatewayConfig config = (VtGatewayConfig) o;

        if (maxConnectionPoolSize != config.maxConnectionPoolSize) return false;
        if (clientKey != null ? !clientKey.equals(config.clientKey) : config.clientKey != null) return false;
        if (environmentType != config.environmentType) return false;
        if (proxyConfig != null ? !proxyConfig.equals(config.proxyConfig) : config.proxyConfig != null) return false;
        if (serverKey != null ? !serverKey.equals(config.serverKey) : config.serverKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = environmentType != null ? environmentType.hashCode() : 0;
        result = 31 * result + (serverKey != null ? serverKey.hashCode() : 0);
        result = 31 * result + (clientKey != null ? clientKey.hashCode() : 0);
        result = 31 * result + maxConnectionPoolSize;
        result = 31 * result + (proxyConfig != null ? proxyConfig.hashCode() : 0);
        return result;
    }
}
