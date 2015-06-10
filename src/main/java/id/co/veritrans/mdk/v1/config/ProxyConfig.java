package id.co.veritrans.mdk.v1.config;

import javax.validation.constraints.NotNull;

/**
 * Merchant proxy configuration
 */
public class ProxyConfig {

    @NotNull
    private final String host;
    private final int port;
    private final String username;
    private final String password;

    /**
     * Proxy config constructor.
     *
     * @param host the proxy server host address.
     * @param port the proxy server listening port.
     * @param username the username used to authenticate against the proxy server.
     * @param password the password used to authenticate against the proxy server.
     */
    public ProxyConfig(final String host, final int port, final String username, final String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    /**
     * Get merchant proxy host configuration.
     *
     * @return Merchant proxy host config.
     */
    public String getHost() {
        return host;
    }

    /**
     * Get merchant proxy port configuration
     *
     * @return Merchant proxy port config
     */
    public int getPort() {
        return port;
    }

    /**
     * Get proxy username to connect to Veritrans API
     *
     * @return Merchant proxy username config
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get proxy password to connect to Veritrans API
     *
     * @return Merchant proxy password config
     */
    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final ProxyConfig that = (ProxyConfig) o;

        if (port != that.port) return false;
        if (host != null ? !host.equals(that.host) : that.host != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = host != null ? host.hashCode() : 0;
        result = 31 * result + port;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
