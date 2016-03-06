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
    private int connectTimeout = DEFAULT_CONNECT_TIMEOUT;
    private int socketTimeout = DEFAULT_SOCKET_TIMEOUT;

    /**
     * Default connect timeout
     */
    public static int DEFAULT_CONNECT_TIMEOUT = 5000;

    /**
     * Default socket timeout
     */
    public static int DEFAULT_SOCKET_TIMEOUT = 30000;

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
     * Veritrans gateway configuration constructor
     * @param environmentType       {@link id.co.veritrans.mdk.v1.config.EnvironmentType Environment type}
     * @param serverKey             Merchant <a href="https://my.sandbox.veritrans.co.id/login">server key</a>
     * @param clientKey             Merchant <a href="https://my.sandbox.veritrans.co.id/login">client key</a>
     * @param maxConnectionPoolSize Http client max connection pool size
     * @param connectTimeout        Http client connect timeout
     * @param socketTimeout         Http client socket timeout
     * @param proxyConfig           Http client {@link id.co.veritrans.mdk.v1.config.ProxyConfig proxy configuration}
     */
    public VtGatewayConfig(EnvironmentType environmentType, String serverKey, String clientKey, int maxConnectionPoolSize,
                           int connectTimeout, int socketTimeout, ProxyConfig proxyConfig) {
        this.environmentType = environmentType;
        this.serverKey = serverKey;
        this.clientKey = clientKey;
        this.maxConnectionPoolSize = maxConnectionPoolSize;
        this.proxyConfig = proxyConfig;
        this.connectTimeout = connectTimeout;
        this.socketTimeout = socketTimeout;
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

    /**
     * Get http client connect timeout
     * @return Http client connect timeout
     */
    public int getConnectTimeout() {
        return connectTimeout;
    }

    /**
     * Get http client socket timeout
     * @return Http client socket timeout
     */
    public int getSocketTimeout() {
        return socketTimeout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VtGatewayConfig config = (VtGatewayConfig) o;

        if (maxConnectionPoolSize != config.maxConnectionPoolSize) return false;
        if (socketTimeout != config.socketTimeout) return false;
        if (connectTimeout!= config.connectTimeout) return false;
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
        result = 31 * result + socketTimeout;
        result = 31 * result + connectTimeout;
        result = 31 * result + (proxyConfig != null ? proxyConfig.hashCode() : 0);
        return result;
    }
}
