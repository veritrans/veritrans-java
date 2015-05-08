package id.co.veritrans.mdk;

import id.co.veritrans.mdk.config.EnvironmentType;
import id.co.veritrans.mdk.config.ProxyConfig;
import id.co.veritrans.mdk.exception.InvalidVtConfigException;
import id.co.veritrans.mdk.gateway.VtDirect;
import id.co.veritrans.mdk.gateway.VtWeb;
import id.co.veritrans.mdk.gateway.impl.DefaultVtDirect;
import id.co.veritrans.mdk.gateway.impl.DefaultVtWeb;
import id.co.veritrans.mdk.util.ValidationUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Convenience "factory" class to facilitate setup a gateway for connection to Veritrans API using VT-Direct or VT-Web.
 */
public class VtGatewayFactory {

    private Validator validator;
    private Set<ConstraintViolation<VtGatewayConfig>> constraintViolations;
    private VtGatewayConfig vtGatewayConfig;
    private Session session;

    /**
     * VtGatewayFactory constructor
     */
    public VtGatewayFactory() {
        this(new VtGatewayConfig());
    }

    /**
     * VtGatewayFactory constructor
     *
     * @param vtGatewayConfig Veritrans {@link id.co.veritrans.mdk.VtGatewayConfig gateway configuration} (not null)
     */
    public VtGatewayFactory(VtGatewayConfig vtGatewayConfig) {
        this.validator = ValidationUtil.getValidator();
        setVtGatewayConfig(vtGatewayConfig);
    }

    /**
     * Get veritrans gateway config.
     *
     * @return {@link id.co.veritrans.mdk.VtGatewayConfig Veritrans gateway config} when connectng to Veritrans API
     */
    public VtGatewayConfig getVtGatewayConfig() {
        return vtGatewayConfig;
    }

    /**
     * Set veritrans gateway config
     *
     * @param vtGatewayConfig {@link id.co.veritrans.mdk.VtGatewayConfig Veritrans gateway config} when connecting to Veritrans API (not null).
     */
    public void setVtGatewayConfig(final VtGatewayConfig vtGatewayConfig) {
        if (vtGatewayConfig == null) {
            throw new NullPointerException("vtGatewayConfig");
        }
        this.vtGatewayConfig = vtGatewayConfig;

        if (this.session != null) {
            this.session.close();
        }
        this.session = new Session();
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
     * Set merchant proxy host configuration
     *
     * @param proxyHost Merchant proxy host config
     */
    public void setProxyHost(String proxyHost) {
        allocateProxyConfigIfNull();
        vtGatewayConfig.getProxyConfig().setHost(proxyHost);
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
     * Set merchant proxy port configuration
     *
     * @param proxyPort Merchant proxy port config
     */
    public void setProxyPort(int proxyPort) {
        allocateProxyConfigIfNull();
        vtGatewayConfig.getProxyConfig().setPort(proxyPort);
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
     * Set proxy username to connect to Veritrans API
     *
     * @param proxyUsername Merchant proxy username config
     */
    public void setProxyUsername(String proxyUsername) {
        allocateProxyConfigIfNull();
        this.vtGatewayConfig.getProxyConfig().setUsername(proxyUsername);
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
     * Set proxy password to connect to Veritrans API
     *
     * @param proxyPassword Merchant proxy password config
     */
    public void setProxyPassword(String proxyPassword) {
        allocateProxyConfigIfNull();
        this.vtGatewayConfig.getProxyConfig().setPassword(proxyPassword);
    }

    /**
     * Build new VtDirect object to setup the transaction
     *
     * @return Veritrans {@link id.co.veritrans.mdk.gateway.VtDirect VtDirect} object to setup the transaction using VT-Direct
     */
    public VtDirect vtDirect() throws InvalidVtConfigException {
        validate(vtGatewayConfig);
        return new DefaultVtDirect(vtGatewayConfig, session);
    }

    /**
     * Build new VtWeb object to setup the transaction
     *
     * @return Veritrans {@link id.co.veritrans.mdk.gateway.VtWeb VtWeb} object to setup the transaction using VT-Web
     */
    public VtWeb vtWeb() throws InvalidVtConfigException {
        validate(vtGatewayConfig);
        return new DefaultVtWeb(vtGatewayConfig, session);
    }

    private void validate(VtGatewayConfig vtGatewayConfig) throws InvalidVtConfigException {
        constraintViolations = validator.validate(vtGatewayConfig);
        if (!constraintViolations.isEmpty()) {
            throw new InvalidVtConfigException(ValidationUtil.buildExceptionMessage(constraintViolations.toArray()));
        }
    }

    private void allocateProxyConfigIfNull() {
        if (vtGatewayConfig.getProxyConfig() == null) {
            vtGatewayConfig.setProxyConfig(new ProxyConfig());
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

    /**
     * This is internal class with design flaw. We should make the whole config classes immutable from the beginning
     * so don't have to worry about the configuration change, hence we can reuse the returned CloseableHttpClient without
     * worry.
     */
    public class Session {

        private final PoolingHttpClientConnectionManager connectionManager;

        public Session() {
            connectionManager = new PoolingHttpClientConnectionManager();
        }

        public void close() {
            connectionManager.close();
        }

        public CloseableHttpClient getHttpClient() {
            checkConnectionManagerConfiguration();
            final List<Header> defaultHeaders = Arrays.asList(
                    (Header) new BasicHeader("Accept", "application/json"),
                    (Header) new BasicHeader("Content-Type", "application/json"),
                    (Header) new BasicHeader("Authorization", "Basic " + Base64.encodeBase64String(vtGatewayConfig.getServerKey().getBytes()))
            );

            return configureProxy(HttpClients.custom()
                    .setConnectionManager(connectionManager)
                    .setDefaultHeaders(defaultHeaders)).build();
        }

        private HttpClientBuilder configureProxy(HttpClientBuilder httpClientBuilder) {
            if (vtGatewayConfig.getProxyConfig() == null) {
                return httpClientBuilder;
            }

            final HttpHost proxyHost = new HttpHost(vtGatewayConfig.getProxyConfig().getHost(), vtGatewayConfig.getProxyConfig().getPort());
            final BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            if (vtGatewayConfig.getProxyConfig().getUsername() != null) {
                credentialsProvider.setCredentials(new AuthScope(proxyHost), new UsernamePasswordCredentials(
                        vtGatewayConfig.getProxyConfig().getUsername(),
                        vtGatewayConfig.getProxyConfig().getPassword()));
            }
            return httpClientBuilder
                    .setProxy(proxyHost)
                    .setDefaultCredentialsProvider(credentialsProvider);
        }

        private void checkConnectionManagerConfiguration() {
            if (connectionManager.getMaxTotal() < vtGatewayConfig.getMaxHttpConnectionPoolSize()) {
                connectionManager.setMaxTotal(vtGatewayConfig.getMaxHttpConnectionPoolSize());
            }
            connectionManager.setDefaultMaxPerRoute(vtGatewayConfig.getMaxHttpConnectionPoolSize());
        }
    }
}
