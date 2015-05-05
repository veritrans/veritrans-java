package id.co.veritrans.mdk;

import id.co.veritrans.mdk.config.EnvironmentType;
import id.co.veritrans.mdk.config.MerchantCredential;

/**
 * This class will be use to setup gateway config before trigger the transaction and need to setup to construct
 * {@link id.co.veritrans.mdk.VtGatewayFactory#setVtGatewayConfig(VtGatewayConfig) VtGatewayFactory} object.<br>
 *
 * <br><br>
 * Created by gde on 5/4/15.
 */
public class VtGatewayConfig {

    private MerchantCredential merchantCredential;
    private EnvironmentType environmentType;

    /**
     * Construct new VtGatewayConfig object
     * <br>
     * <i>If using this constructor, is mandatory to setup {@link id.co.veritrans.mdk.config.MerchantCredential Merchant credential} and
     * {@link id.co.veritrans.mdk.config.EnvironmentType Environment type} manually</i>
     */
    public VtGatewayConfig() {
    }

    /**
     * Construct new VtGatewayConfig object
     * using merchant credential (server_key and client_key) and
     * environment type (sandbox or production)
     *
     * <br><br>
     * <i>Merchant credential value can recieve from veritrans Merchant Administration Portal</i>
     *
     * @param merchantCredential    {@link id.co.veritrans.mdk.config.MerchantCredential#MerchantCredential(String, String) Merchant server_key and client_key}
     * @param environmentType       {@link id.co.veritrans.mdk.config.EnvironmentType Production or sandbox environment}
     */
    public VtGatewayConfig(final MerchantCredential merchantCredential, final EnvironmentType environmentType) {
        this.merchantCredential = merchantCredential;
        this.environmentType = environmentType;
    }

    /**
     * Get detail of merchant credential
     * (server_key and client_key)
     *
     * @return  merchant credential detail (server_key and client_key)
     */
    public MerchantCredential getMerchantCredential() {
        return merchantCredential;
    }

    /**
     * Set merchant credential detail (server_key and client_key)
     * This parameter is mandatory to setup if VtGatewayConfig construct using empty parameter
     * This value can recieve from veritrans Merchant Administration Portal
     *
     * @param merchantCredential    merchant credential detail (server_key and client_key)
     */
    public void setMerchantCredential(final MerchantCredential merchantCredential) {
        this.merchantCredential = merchantCredential;
    }

    /**
     * Get environment type that used for the transaction
     * (production or sandbox environment)
     *
     * @return  environment type (production or sandbox)
     */
    public EnvironmentType getEnvironmentType() {
        return environmentType;
    }

    /**
     * Set environment type that will be use for the transaction
     * (production or sandbox environment)
     *
     * @param environmentType   environment type (production or sandbox)
     */
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
