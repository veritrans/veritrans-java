package id.co.veritrans.mdk;

import id.co.veritrans.mdk.config.EnvironmentType;

/**
 * Veritrans gateway config. Used on {@link id.co.veritrans.mdk.VtGatewayFactory VtGatewayFactory} class.
 */
public class VtGatewayConfig {

    private EnvironmentType environmentType;
    private String serverKey;
    private String clientKey;
    private String proxyUsername;
    private String proxyPassword;

    public VtGatewayConfig() {};

    /**
     * @param serverKey         Merchant server key when connecting to Veritrans API. Can be obtain from veritrans <a href="https://my.sandbox.veritrans.co.id/login">Merchant Administration Portal</a>
     * @param clientKey         Merchant client key when connecting to Veritrans API. Can be obtain from veritrans <a href="https://my.sandbox.veritrans.co.id/login">Merchant Administration Portal</a>
     * @param environmentType   Veritrans {@link id.co.veritrans.mdk.config.EnvironmentType environment type} when connecting to Veritrans API.
     */
    public VtGatewayConfig(final String serverKey, final String clientKey, final EnvironmentType environmentType) {
        this.serverKey = serverKey;
        this.clientKey = clientKey;
        this.environmentType = environmentType;
    }

    /**
     * Get veritrans API environment type
     * @return Veritrans {@link id.co.veritrans.mdk.config.EnvironmentType environment type} when connecting to Veritrans API
     */
    public EnvironmentType getEnvironmentType() {
        return environmentType;
    }

    /**
     * Set veritrans API environment type
     * @param environmentType   Veritrans {@link id.co.veritrans.mdk.config.EnvironmentType environment type} when connecting to Veritrans API.
     */
    public void setEnvironmentType(final EnvironmentType environmentType) {
        this.environmentType = environmentType;
    }

    /**
     * Get the merchant server key
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
     * @return Merchant client key when connecting to Veritrans API
     */
    public String getClientKey() {
        return clientKey;
    }

    /**
     * Set the merchant client key
     * @param clientKey Merchant client key when connecting to Veritrans API. Can be obtain from veritrans <a href="https://my.sandbox.veritrans.co.id/login">Merchant Administration Portal</a>
     */
    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
    }

    /**
     * Get proxy username to connect to Veritrans API
     * @return Merchant proxy username config
     */
    public String getProxyUsername() {
        return proxyUsername;
    }

    /**
     * Set proxy username to connect to Veritrans API
     * @param proxyUsername Merchant proxy username config
     */
    public void setProxyUsername(String proxyUsername) {
        this.proxyUsername = proxyUsername;
    }

    /**
     * Get proxy password to connect to Veritrans API
     * @return Merchant proxy password config
     */
    public String getProxyPassword() {
        return proxyPassword;
    }

    /**
     * Set proxy password to connect to Veritrans API
     * @param proxyPassword Merchant proxy password config
     */
    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final VtGatewayConfig that = (VtGatewayConfig) o;

        if (environmentType != that.environmentType) return false;
        if (serverKey != that.serverKey) return false;
        if (clientKey != that.clientKey) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = serverKey != null ? serverKey.hashCode() : 0;
        result = 31 * result + (clientKey != null ? clientKey.hashCode() : 0);
        result = 31 * result + (environmentType != null ? environmentType.hashCode() : 0);
        return result;
    }
}
