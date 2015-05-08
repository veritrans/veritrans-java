package id.co.veritrans.mdk;

import id.co.veritrans.mdk.config.EnvironmentType;
import id.co.veritrans.mdk.config.ProxyConfig;

import javax.validation.constraints.NotNull;

/**
 * Veritrans gateway config. Used on {@link id.co.veritrans.mdk.VtGatewayFactory VtGatewayFactory} class.
 */
public class VtGatewayConfig {

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    /* operating environment type */
    @NotNull
    private EnvironmentType environmentType;

    /* server key as defined in MAP */
    @NotNull
    private String serverKey;

    /* client key as defined in MAP */
    @NotNull
    private String clientKey;

    /* Proxy configuration */
    private ProxyConfig proxyConfig;

    /* http client maximum connection pool size in total */
    private int maxHttpConnectionPoolSize;

    public VtGatewayConfig() {
        if (CPU_COUNT < 4) {
            this.maxHttpConnectionPoolSize = 4;
        } else {
            this.maxHttpConnectionPoolSize = CPU_COUNT;
        }
    }

    /**
     * @param serverKey       Merchant server key when connecting to Veritrans API. Can be obtain from veritrans <a href="https://my.sandbox.veritrans.co.id/login">Merchant Administration Portal</a>
     * @param clientKey       Merchant client key when connecting to Veritrans API. Can be obtain from veritrans <a href="https://my.sandbox.veritrans.co.id/login">Merchant Administration Portal</a>
     * @param environmentType Veritrans {@link id.co.veritrans.mdk.config.EnvironmentType environment type} when connecting to Veritrans API.
     */
    public VtGatewayConfig(final String serverKey, final String clientKey, final EnvironmentType environmentType) {
        this();
        this.serverKey = serverKey;
        this.clientKey = clientKey;
        this.environmentType = environmentType;
    }

    /**
     * Get veritrans API environment type
     *
     * @return Veritrans {@link id.co.veritrans.mdk.config.EnvironmentType environment type} when connecting to Veritrans API
     */
    public EnvironmentType getEnvironmentType() {
        return environmentType;
    }

    /**
     * Set veritrans API environment type
     *
     * @param environmentType Veritrans {@link id.co.veritrans.mdk.config.EnvironmentType environment type} when connecting to Veritrans API.
     */
    public void setEnvironmentType(final EnvironmentType environmentType) {
        this.environmentType = environmentType;
    }

    /**
     * Get the merchant server key
     *
     * @return Merchant server key when connecting to Veritrans API
     */
    public String getServerKey() {
        return serverKey;
    }

    /**
     * Set the merchant server key<br>
     *
     * @param serverKey Merchant server key when connecting to Veritrans API. Can be obtain from veritrans <a href="https://my.sandbox.veritrans.co.id/login">Merchant Administration Portal</a>
     */
    public void setServerKey(String serverKey) {
        this.serverKey = serverKey;
    }

    /**
     * Get the merchant client key
     *
     * @return Merchant client key when connecting to Veritrans API
     */
    public String getClientKey() {
        return clientKey;
    }

    /**
     * Set the merchant client key
     *
     * @param clientKey Merchant client key when connecting to Veritrans API. Can be obtain from veritrans <a href="https://my.sandbox.veritrans.co.id/login">Merchant Administration Portal</a>
     */
    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
    }

    /**
     * Get merchant proxy configuration
     *
     * @return {@link id.co.veritrans.mdk.config.ProxyConfig Merchant proxy config}
     */
    public ProxyConfig getProxyConfig() {
        return proxyConfig;
    }

    /**
     * Set merchant proxy configuration
     *
     * @param proxyConfig {@link id.co.veritrans.mdk.config.ProxyConfig Merchant proxy config}.
     */
    public void setProxyConfig(ProxyConfig proxyConfig) {
        this.proxyConfig = proxyConfig;
    }

    /**
     * Get the maximum connection pool size for the HTTP Client used to communicate with Veritrans Payment API Server.
     *
     * @return current maxHttpConnectionPoolSize value
     */
    public int getMaxHttpConnectionPoolSize() {
        return maxHttpConnectionPoolSize;
    }

    /**
     * Set the maximum connection pool size for the HTTP Client used to communicate with Veritrans Payment API Server.
     * You might want to set it to 1 if you don't cache the VtGatewayFactory instance. The default value depends on
     * the available amount of processors, if less than 4 processors are available, then default value will be 4, else
     * the default value will be the same as the amount of available processors.
     *
     * @param maxHttpConnectionPoolSize the new maxHttpConnectionPoolSize value.
     */
    public void setMaxHttpConnectionPoolSize(final int maxHttpConnectionPoolSize) {
        this.maxHttpConnectionPoolSize = maxHttpConnectionPoolSize;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final VtGatewayConfig that = (VtGatewayConfig) o;

        if (clientKey != null ? !clientKey.equals(that.clientKey) : that.clientKey != null) return false;
        if (environmentType != that.environmentType) return false;
        if (proxyConfig != null ? !proxyConfig.equals(that.proxyConfig) : that.proxyConfig != null) return false;
        if (serverKey != null ? !serverKey.equals(that.serverKey) : that.serverKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = environmentType != null ? environmentType.hashCode() : 0;
        result = 31 * result + (serverKey != null ? serverKey.hashCode() : 0);
        result = 31 * result + (clientKey != null ? clientKey.hashCode() : 0);
        result = 31 * result + (proxyConfig != null ? proxyConfig.hashCode() : 0);
        return result;
    }
}
