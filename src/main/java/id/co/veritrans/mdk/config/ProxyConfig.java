package id.co.veritrans.mdk.config;

import javax.validation.constraints.NotNull;

/**
 * Merchant proxy configuration
 */
public class ProxyConfig {

    @NotNull
    private String host;
    private int port;
    private String username;
    private String password;

    public ProxyConfig() {
    }

    /**
     * Get merchant proxy host configuration
     * @return Merchant proxy host config
     */
    public String getHost() {
        return host;
    }

    /**
     * Set merchant proxy host configuration
     * @param host Merchant proxy host config
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * Get merchant proxy port configuration
     * @return Merchant proxy port config
     */
    public int getPort() {
        return port;
    }

    /**
     * Set merchant proxy port configuration
     * @param port Merchant proxy port config
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Get proxy username to connect to Veritrans API
     * @return Merchant proxy username config
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set proxy username to connect to Veritrans API
     * @param username Merchant proxy username config
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get proxy password to connect to Veritrans API
     * @return Merchant proxy password config
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set proxy password to connect to Veritrans API
     * @param password Merchant proxy password config
     */
    public void setPassword(String password) {
        this.password = password;
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
