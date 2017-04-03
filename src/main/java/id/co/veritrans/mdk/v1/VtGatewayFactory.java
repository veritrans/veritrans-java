package id.co.veritrans.mdk.v1;

import id.co.veritrans.mdk.v1.config.EnvironmentType;
import id.co.veritrans.mdk.v1.gateway.VtDirect;
import id.co.veritrans.mdk.v1.gateway.VtWeb;
import id.co.veritrans.mdk.v1.gateway.impl.DefaultVtDirect;
import id.co.veritrans.mdk.v1.gateway.impl.DefaultVtGatewaySession;
import id.co.veritrans.mdk.v1.gateway.impl.DefaultVtWeb;
import id.co.veritrans.mdk.v1.gateway.impl.DefaultSnap;

import javax.validation.ConstraintViolationException;
import java.io.IOException;

/**
 * Convenience "factory" class to facilitate setup a gateway for connection to Veritrans API using VT-Direct or VT-Web.
 */
public class VtGatewayFactory {

    private final VtGatewayConfig vtGatewayConfig;
    private final DefaultVtGatewaySession vtGatewaySession;

    /**
     * VtGatewayFactory constructor
     *
     * @param vtGatewayConfig Veritrans {@link id.co.veritrans.mdk.v1.VtGatewayConfig gateway configuration} (not null)
     */
    public VtGatewayFactory(VtGatewayConfig vtGatewayConfig) throws ConstraintViolationException {
        if (vtGatewayConfig == null) {
            throw new NullPointerException("vtGatewayConfig");
        }

        this.vtGatewayConfig = vtGatewayConfig;
        this.vtGatewaySession = new DefaultVtGatewaySession(vtGatewayConfig);
    }

    /**
     * Get veritrans gateway config.
     *
     * @return {@link id.co.veritrans.mdk.v1.VtGatewayConfig Veritrans gateway config} when connectng to Veritrans API
     */
    public VtGatewayConfig getVtGatewayConfig() {
        return vtGatewayConfig;
    }

    /**
     * Get veritrans API environment type
     *
     * @return Veritrans {@link id.co.veritrans.mdk.v1.config.EnvironmentType environment type} when connecting to Veritrans API
     */
    public EnvironmentType getEnvironmentType() {
        return this.vtGatewayConfig.getEnvironmentType();
    }

    /**
     * Get the merchant server key
     *
     * @return Merchant server key when connecting to Veritrans API
     */
    public String getServerKey() {
        return this.vtGatewayConfig.getServerKey();
    }

    /**
     * Get the merchant client key
     *
     * @return Merchant client key when connecting to Veritrans API
     */
    public String getClientKey() {
        return this.vtGatewayConfig.getClientKey();
    }

    /**
     * Get merchant proxy host configuration
     *
     * @return Merchant proxy host config
     */
    public String getProxyHost() {
        if (vtGatewayConfig.getProxyConfig() == null) {
            return null;
        }
        return vtGatewayConfig.getProxyConfig().getHost();
    }

    /**
     * Get merchant proxy port configuration
     *
     * @return Merchant proxy port config
     */
    public int getProxyPort() {
        if (vtGatewayConfig.getProxyConfig() == null) {
            return 0;
        }
        return vtGatewayConfig.getProxyConfig().getPort();
    }

    /**
     * Get proxy username to connect to Veritrans API
     *
     * @return Merchant proxy username config
     */
    public String getProxyUsername() {
        if (vtGatewayConfig.getProxyConfig() == null) {
            return null;
        }
        return this.vtGatewayConfig.getProxyConfig().getUsername();
    }

    /**
     * Get proxy password to connect to Veritrans API
     *
     * @return Merchant proxy password config
     */
    public String getProxyPassword() {
        if (vtGatewayConfig.getProxyConfig() == null) {
            return null;
        }
        return this.vtGatewayConfig.getProxyConfig().getPassword();
    }

    /**
     * Build new VtDirect object to setup the transaction
     *
     * @return Veritrans {@link id.co.veritrans.mdk.v1.gateway.VtDirect VtDirect} object to setup the transaction using VT-Direct
     */
    public VtDirect vtDirect() {
        return new DefaultVtDirect(vtGatewaySession);
    }

    /**
     * Build new VtWeb object to setup the transaction
     *
     * @return Veritrans {@link id.co.veritrans.mdk.v1.gateway.VtWeb VtWeb} object to setup the transaction using VT-Web
     */
    public VtWeb vtWeb() {
        return new DefaultVtWeb(vtGatewaySession);
    }

    public DefaultSnap snap() { return new DefaultSnap(vtGatewaySession); }

    public void destroy() throws IOException {
        vtGatewaySession.destroy();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final VtGatewayFactory that = (VtGatewayFactory) o;

        if (vtGatewayConfig != null ? !vtGatewayConfig.equals(that.vtGatewayConfig) : that.vtGatewayConfig != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return vtGatewayConfig != null ? vtGatewayConfig.hashCode() : 0;
    }
}
