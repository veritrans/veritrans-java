package id.co.veritrans.mdk.config;

/**
 * Merchant proxy configuration
 */
public class ProxyConfig {
    private String proxyHost;
    private int proxyPort;
    private String proxyUsername;
    private String proxyPassword;

    /**
     * Get merchant proxy host configuration
     * @return Merchant proxy host config
     */
    public String getProxyHost() {
        return proxyHost;
    }

    /**
     * Set merchant proxy host configuration
     * @param proxyHost Merchant proxy host config
     */
    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    /**
     * Get merchant proxy port configuration
     * @return Merchant proxy port config
     */
    public int getProxyPort() {
        return proxyPort;
    }

    /**
     * Set merchant proxy port configuration
     * @param proxyPort Merchant proxy port config
     */
    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
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
}
