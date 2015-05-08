package id.co.veritrans.mdk.config;

/**
 * Merchant proxy configuration
 */
public class ProxyConfig {
    private String host;
    private int port;
    private String username;
    private String password;

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
}
