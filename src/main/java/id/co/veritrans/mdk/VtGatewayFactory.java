package id.co.veritrans.mdk;

import id.co.veritrans.mdk.config.EnvironmentType;
import id.co.veritrans.mdk.config.MerchantCredential;
import id.co.veritrans.mdk.impl.DefaultVtGateway;

/**
 * Created by gde on 4/30/15.
 */
public class VtGatewayFactory {

    private VtGatewayConfig vtGatewayConfig;

    public VtGatewayFactory() {
    }

    public VtGatewayFactory(final VtGatewayConfig vtGatewayConfig) {
        this.vtGatewayConfig = vtGatewayConfig;
    }

    public VtGatewayConfig getVtGatewayConfig() {
        return vtGatewayConfig;
    }

    public void setVtGatewayConfig(final VtGatewayConfig vtGatewayConfig) {
        this.vtGatewayConfig = vtGatewayConfig;
    }

    public VtGateway vtGateway() {
        return vtGateway(vtGatewayConfig);
    }

    public static VtGateway vtGateway(final MerchantCredential merchantCredential, final EnvironmentType environmentType) {
        return vtGateway(new VtGatewayConfig(merchantCredential, environmentType));
    }

    public static VtGateway vtGateway(final VtGatewayConfig vtGatewayConfig) {
        return new DefaultVtGateway(vtGatewayConfig);
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
