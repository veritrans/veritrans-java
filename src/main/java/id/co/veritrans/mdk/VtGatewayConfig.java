package id.co.veritrans.mdk;

import id.co.veritrans.mdk.config.EnvironmentType;
import id.co.veritrans.mdk.config.MerchantCredential;

/**
 * Created by gde on 5/4/15.
 */
public class VtGatewayConfig {

    private MerchantCredential merchantCredential;
    private EnvironmentType environmentType;

    public VtGatewayConfig() {
    }

    public VtGatewayConfig(final MerchantCredential merchantCredential, final EnvironmentType environmentType) {
        this.merchantCredential = merchantCredential;
        this.environmentType = environmentType;
    }

    public MerchantCredential getMerchantCredential() {
        return merchantCredential;
    }

    public void setMerchantCredential(final MerchantCredential merchantCredential) {
        this.merchantCredential = merchantCredential;
    }

    public EnvironmentType getEnvironmentType() {
        return environmentType;
    }

    public void setEnvironmentType(final EnvironmentType environmentType) {
        this.environmentType = environmentType;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final VtGatewayConfig that = (VtGatewayConfig) o;

        if (environmentType != that.environmentType) return false;
        if (merchantCredential != null ? !merchantCredential.equals(that.merchantCredential) : that.merchantCredential != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = merchantCredential != null ? merchantCredential.hashCode() : 0;
        result = 31 * result + (environmentType != null ? environmentType.hashCode() : 0);
        return result;
    }
}
