package id.co.veritrans.mdk;

import id.co.veritrans.mdk.config.EnvironmentType;
import id.co.veritrans.mdk.exception.InvalidVtConfigException;
import id.co.veritrans.mdk.gateway.VtDirect;
import id.co.veritrans.mdk.gateway.VtWeb;
import id.co.veritrans.mdk.gateway.impl.DefaultVtDirect;
import id.co.veritrans.mdk.gateway.impl.DefaultVtWeb;
import id.co.veritrans.mdk.util.ExceptionUtil;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Convenience "factory" class to facilitate setup a gateway for connection to Veritrans API using VT-Direct or VT-Web.
 */
public class VtGatewayFactory {

    private Validator validator;
    private Set<ConstraintViolation<VtGatewayConfig>> constraintViolations;
    private VtGatewayConfig vtGatewayConfig;

    /**
     * VtGatewayFactory constructor
     */
    public VtGatewayFactory() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
        vtGatewayConfig = new VtGatewayConfig();
    }

    /**
     * VtGatewayFactory constructor
     *
     * @param vtGatewayConfig Veritrans {@link id.co.veritrans.mdk.VtGatewayConfig gateway configuration}
     */
    public VtGatewayFactory(VtGatewayConfig vtGatewayConfig) {
        this.vtGatewayConfig = vtGatewayConfig;
    }

    /**
     * Get veritrans gateway config
     *
     * @return {@link id.co.veritrans.mdk.VtGatewayConfig Veritrans gateway config} when connectng to Veritrans API
     */
    public VtGatewayConfig getVtGatewayConfig() {
        return vtGatewayConfig;
    }

    /**
     * Set veritrans gateway config
     *
     * @param vtGatewayConfig {@link id.co.veritrans.mdk.VtGatewayConfig Veritrans gateway config} when connecting to Veritrans API
     */
    public void setVtGatewayConfig(final VtGatewayConfig vtGatewayConfig) {
        this.vtGatewayConfig = vtGatewayConfig;
    }

    /**
     * Get veritrans API environment type
     *
     * @return Veritrans {@link id.co.veritrans.mdk.config.EnvironmentType environment type} when connecting to Veritrans API
     */
    public EnvironmentType getEnvironmentType() {
        return this.vtGatewayConfig.getEnvironmentType();
    }

    /**
     * Set veritrans API environment type
     *
     * @param environmentType Veritrans {@link id.co.veritrans.mdk.config.EnvironmentType environment type} when connecting to Veritrans API.
     */
    public void setEnvironmentType(final EnvironmentType environmentType) {
        this.vtGatewayConfig.setEnvironmentType(environmentType);
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
     * Set the merchant server key<br>
     *
     * @param serverKey Merchant server key when connecting to Veritrans API. Can be obtain from veritrans <a href="https://my.sandbox.veritrans.co.id/login">Merchant Administration Portal</a>
     */
    public void setServerKey(final String serverKey) {
        this.vtGatewayConfig.setServerKey(serverKey);
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
     * Set the merchant client key
     *
     * @param clientKey Merchant client key when connecting to Veritrans API. Can be obtain from veritrans <a href="https://my.sandbox.veritrans.co.id/login">Merchant Administration Portal</a>
     */
    public void setClientKey(final String clientKey) {
        this.vtGatewayConfig.setClientKey(clientKey);
    }

    /**
     * Get merchant proxy host configuration
     * @return Merchant proxy host config
     */
    public String getProxyHost() {
        return vtGatewayConfig.getProxyConfig().getProxyHost();
    }

    /**
     * Set merchant proxy host configuration
     * @param proxyHost Merchant proxy host config
     */
    public void setProxyHost(String proxyHost) {
        vtGatewayConfig.getProxyConfig().setProxyHost(proxyHost);
    }

    /**
     * Get merchant proxy port configuration
     * @return Merchant proxy port config
     */
    public int getProxyPort() {
        return vtGatewayConfig.getProxyConfig().getProxyPort();
    }

    /**
     * Set merchant proxy port configuration
     * @param proxyPort Merchant proxy port config
     */
    public void setProxyPort(int proxyPort) {
        vtGatewayConfig.getProxyConfig().setProxyPort(proxyPort);
    }

    /**
     * Get proxy username to connect to Veritrans API
     *
     * @return Merchant proxy username config
     */
    public String getProxyUsername() {
        return this.vtGatewayConfig.getProxyConfig().getProxyUsername();
    }

    /**
     * Set proxy username to connect to Veritrans API
     *
     * @param proxyUsername Merchant proxy username config
     */
    public void setProxyUsername(String proxyUsername) {
        this.vtGatewayConfig.getProxyConfig().setProxyUsername(proxyUsername);
    }

    /**
     * Get proxy password to connect to Veritrans API
     *
     * @return Merchant proxy password config
     */
    public String getProxyPassword() {
        return this.vtGatewayConfig.getProxyConfig().getProxyPassword();
    }

    /**
     * Set proxy password to connect to Veritrans API
     *
     * @param proxyPassword Merchant proxy password config
     */
    public void setProxyPassword(String proxyPassword) {
        this.vtGatewayConfig.getProxyConfig().setProxyPassword(proxyPassword);
    }

    /**
     * Build new VtDirect object to setup the transaction
     *
     * @return Veritrans {@link id.co.veritrans.mdk.gateway.VtDirect VtDirect} object to setup the transaction using VT-Direct
     */
    public VtDirect vtDirect() throws InvalidVtConfigException {
        validate(vtGatewayConfig);
        return new DefaultVtDirect(vtGatewayConfig);
    }

    /**
     * Build new VtWeb object to setup the transaction
     *
     * @return Veritrans {@link id.co.veritrans.mdk.gateway.VtWeb VtWeb} object to setup the transaction using VT-Web
     */
    public VtWeb vtWeb() throws InvalidVtConfigException {
        validate(vtGatewayConfig);
        return new DefaultVtWeb(vtGatewayConfig);
    }

    private void validate(VtGatewayConfig vtGatewayConfig) throws InvalidVtConfigException {
        constraintViolations = validator.validate(vtGatewayConfig);
        if (!constraintViolations.isEmpty()) {
            throw new InvalidVtConfigException(ExceptionUtil.buildExceptionMessage(constraintViolations.toArray()));
        }
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
