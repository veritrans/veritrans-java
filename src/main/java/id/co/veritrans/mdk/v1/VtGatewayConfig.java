package id.co.veritrans.mdk.v1;

import id.co.veritrans.mdk.v1.config.EnvironmentType;
import id.co.veritrans.mdk.v1.config.HttpConfig;

import javax.validation.Valid;
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
    @Valid
    @NotNull
    private final HttpConfig httpConfig;

    public VtGatewayConfig(final EnvironmentType environmentType, final String serverKey, final String clientKey, final HttpConfig httpConfig) {
        this.environmentType = environmentType;
        this.serverKey = serverKey;
        this.clientKey = clientKey;
        this.httpConfig = httpConfig;
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

    public HttpConfig getHttpConfig() {
        return httpConfig;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final VtGatewayConfig that = (VtGatewayConfig) o;

        if (clientKey != null ? !clientKey.equals(that.clientKey) : that.clientKey != null) return false;
        if (environmentType != that.environmentType) return false;
        if (httpConfig != null ? !httpConfig.equals(that.httpConfig) : that.httpConfig != null) return false;
        if (serverKey != null ? !serverKey.equals(that.serverKey) : that.serverKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = environmentType != null ? environmentType.hashCode() : 0;
        result = 31 * result + (serverKey != null ? serverKey.hashCode() : 0);
        result = 31 * result + (clientKey != null ? clientKey.hashCode() : 0);
        result = 31 * result + (httpConfig != null ? httpConfig.hashCode() : 0);
        return result;
    }
}
