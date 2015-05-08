package id.co.veritrans.mdk.v1.config;

public class HttpConfigBuilder {

    private int maxConnectionPoolSize;
    private ProxyConfig proxyConfig;

    public HttpConfigBuilder setMaxConnectionPoolSize(final int maxConnectionPoolSize) {
        this.maxConnectionPoolSize = maxConnectionPoolSize;
        return this;
    }

    public HttpConfigBuilder setProxyConfig(final ProxyConfig proxyConfig) {
        this.proxyConfig = proxyConfig;
        return this;
    }

    public HttpConfig createHttpConfig() {
        return new HttpConfig(maxConnectionPoolSize, proxyConfig);
    }
}
