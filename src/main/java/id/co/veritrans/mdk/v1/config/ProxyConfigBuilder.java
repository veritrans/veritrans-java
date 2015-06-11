package id.co.veritrans.mdk.v1.config;

/**
 * Proxy configuration builder
 */
public class ProxyConfigBuilder {

    private String host;
    private int port = -1;
    private String username;
    private String password;

    /**
     * Set proxy host config
     * @param host Proxy host
     * @return ProxyConfigBuilder
     */
    public ProxyConfigBuilder setHost(final String host) {
        this.host = host;
        return this;
    }

    /**
     * Set proxy port config
     * @param port Proxy port
     * @return ProxyConfigBuilder
     */
    public ProxyConfigBuilder setPort(final int port) {
        this.port = port;
        return this;
    }

    /**
     * Set proxy username config
     * @param username Proxy username config
     * @return ProxyConfigBuilder
     */
    public ProxyConfigBuilder setUsername(final String username) {
        this.username = username;
        return this;
    }

    /**
     * Set proxy password config
     * @param password Proxy password config
     * @return ProxyConfigBuilder
     */
    public ProxyConfigBuilder setPassword(final String password) {
        this.password = password;
        return this;
    }

    /**
     * Create proxy config
     * @return {@link id.co.veritrans.mdk.v1.config.ProxyConfig Proxy configuration}
     */
    public ProxyConfig createProxyConfig() {
        return new ProxyConfig(host, port, username, password);
    }
}
