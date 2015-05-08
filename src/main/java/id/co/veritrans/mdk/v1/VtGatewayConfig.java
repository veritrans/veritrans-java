package id.co.veritrans.mdk.v1;

import id.co.veritrans.mdk.v1.config.EnvironmentType;
import id.co.veritrans.mdk.v1.config.ProxyConfig;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by gde on 5/8/15.
 */
public class VtGatewayConfig {

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    @NotNull
    private final EnvironmentType environmentType;
    @NotNull
    private final String serverKey;
    @NotNull
    private final String clientKey;
    @Min(1)
    private final int maxConnectionPoolSize;
    @Valid
    private final ProxyConfig proxyConfig;

    public VtGatewayConfig(EnvironmentType environmentType, String serverKey, String clientKey, int maxConnectionPoolSize, ProxyConfig proxyConfig) {
        this.environmentType = environmentType;
        this.serverKey = serverKey;
        this.clientKey = clientKey;
        this.maxConnectionPoolSize = maxConnectionPoolSize;
        this.proxyConfig = proxyConfig;
    }

    public EnvironmentType getEnvironmentType() {
        return environmentType;
    }

    public String getServerKey() {
        return serverKey;
    }

    public String getClientKey() {
        return clientKey;
    }

    public int getMaxConnectionPoolSize() {
        return maxConnectionPoolSize;
    }

    public ProxyConfig getProxyConfig() {
        return proxyConfig;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VtGatewayConfig config = (VtGatewayConfig) o;

        if (maxConnectionPoolSize != config.maxConnectionPoolSize) return false;
        if (clientKey != null ? !clientKey.equals(config.clientKey) : config.clientKey != null) return false;
        if (environmentType != config.environmentType) return false;
        if (proxyConfig != null ? !proxyConfig.equals(config.proxyConfig) : config.proxyConfig != null) return false;
        if (serverKey != null ? !serverKey.equals(config.serverKey) : config.serverKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = environmentType != null ? environmentType.hashCode() : 0;
        result = 31 * result + (serverKey != null ? serverKey.hashCode() : 0);
        result = 31 * result + (clientKey != null ? clientKey.hashCode() : 0);
        result = 31 * result + maxConnectionPoolSize;
        result = 31 * result + (proxyConfig != null ? proxyConfig.hashCode() : 0);
        return result;
    }
}
