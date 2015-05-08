package id.co.veritrans.mdk.v1.config;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * Created by gde on 5/8/15.
 */
public class HttpConfig {

    @Min(1)
    private final int maxConnectionPoolSize;
    @Valid
    private final ProxyConfig proxyConfig;

    public HttpConfig(final int maxConnectionPoolSize, final ProxyConfig proxyConfig) {
        this.maxConnectionPoolSize = maxConnectionPoolSize;
        this.proxyConfig = proxyConfig;
    }

    public int getMaxConnectionPoolSize() {
        return maxConnectionPoolSize;
    }

    public ProxyConfig getProxyConfig() {
        return proxyConfig;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final HttpConfig that = (HttpConfig) o;

        if (maxConnectionPoolSize != that.maxConnectionPoolSize) return false;
        if (proxyConfig != null ? !proxyConfig.equals(that.proxyConfig) : that.proxyConfig != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = maxConnectionPoolSize;
        result = 31 * result + (proxyConfig != null ? proxyConfig.hashCode() : 0);
        return result;
    }
}
