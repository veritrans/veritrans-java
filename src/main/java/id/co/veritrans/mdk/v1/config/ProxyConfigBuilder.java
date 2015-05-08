package id.co.veritrans.mdk.v1.config;

public class ProxyConfigBuilder {

    private String host;
    private int port;
    private String username;
    private String password;

    public ProxyConfigBuilder setHost(final String host) {
        this.host = host;
        return this;
    }

    public ProxyConfigBuilder setPort(final int port) {
        this.port = port;
        return this;
    }

    public ProxyConfigBuilder setUsername(final String username) {
        this.username = username;
        return this;
    }

    public ProxyConfigBuilder setPassword(final String password) {
        this.password = password;
        return this;
    }

    public ProxyConfig build() {
        return new ProxyConfig(host, port, username, password);
    }
}
