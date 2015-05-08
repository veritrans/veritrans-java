package id.co.veritrans.mdk.v1;

import id.co.veritrans.mdk.v1.config.EnvironmentType;
import id.co.veritrans.mdk.v1.config.ProxyConfig;
import id.co.veritrans.mdk.v1.exception.InvalidVtConfigException;
import id.co.veritrans.mdk.v1.gateway.VtDirect;
import id.co.veritrans.mdk.v1.gateway.VtGatewaySession;
import id.co.veritrans.mdk.v1.gateway.VtWeb;
import id.co.veritrans.mdk.v1.gateway.impl.DefaultVtDirect;
import id.co.veritrans.mdk.v1.gateway.impl.DefaultVtWeb;
import id.co.veritrans.mdk.v1.util.ValidationUtil;
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
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Convenience "factory" class to facilitate setup a gateway for connection to Veritrans API using VT-Direct or VT-Web.
 */
public class VtGatewayFactory {

    private final Validator validator = ValidationUtil.getValidator();
    private final VtGatewayConfig vtGatewayConfig;
    private final VtGatewaySessionImpl vtGatewaySession;

    /**
     * VtGatewayFactory constructor
     *
     * @param vtGatewayConfig Veritrans {@link id.co.veritrans.mdk.v1.VtGatewayConfig gateway configuration} (not null)
     */
    public VtGatewayFactory(VtGatewayConfig vtGatewayConfig) throws InvalidVtConfigException {
        if (vtGatewayConfig == null) {
            throw new NullPointerException("vtGatewayConfig");
        }

        validate(vtGatewayConfig);
        this.vtGatewayConfig = vtGatewayConfig;
        this.vtGatewaySession = new VtGatewaySessionImpl();
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
    public VtDirect vtDirect() throws InvalidVtConfigException {
        return new DefaultVtDirect(vtGatewaySession);
    }

    /**
     * Build new VtWeb object to setup the transaction
     *
     * @return Veritrans {@link id.co.veritrans.mdk.v1.gateway.VtWeb VtWeb} object to setup the transaction using VT-Web
     */
    public VtWeb vtWeb() throws InvalidVtConfigException {
        return new DefaultVtWeb(vtGatewaySession);
    }

    public void destroy() throws IOException {
        vtGatewaySession.destroy();
    }

    private void validate(VtGatewayConfig vtGatewayConfig) throws InvalidVtConfigException {
        final Set<ConstraintViolation<VtGatewayConfig>> constraintViolations = validator.validate(vtGatewayConfig);
        if (!constraintViolations.isEmpty()) {
            throw new InvalidVtConfigException(ValidationUtil.buildExceptionMessage(constraintViolations.toArray()));
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

    public class VtGatewaySessionImpl implements VtGatewaySession {

        private final PoolingHttpClientConnectionManager connectionManager;
        private final CloseableHttpClient httpClient;

        public VtGatewaySessionImpl() {
            connectionManager = new PoolingHttpClientConnectionManager();
            if (connectionManager.getMaxTotal() < vtGatewayConfig.getMaxConnectionPoolSize()) {
                connectionManager.setMaxTotal(vtGatewayConfig.getMaxConnectionPoolSize());
            }
            connectionManager.setDefaultMaxPerRoute(vtGatewayConfig.getMaxConnectionPoolSize());

            httpClient = buildHttpClient();
        }

        @Override
        public id.co.veritrans.mdk.v1.VtGatewayConfig getVtGatewayConfig() {
            return vtGatewayConfig;
        }

        @Override
        public void destroy() throws IOException {
            connectionManager.close();
            httpClient.close();
        }

        @Override
        public CloseableHttpClient getHttpClient() {
            return httpClient;
        }

        private CloseableHttpClient buildHttpClient() {
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

            final ProxyConfig proxyConfig = vtGatewayConfig.getProxyConfig();
            final HttpHost proxyHost = new HttpHost(proxyConfig.getHost(), proxyConfig.getPort());
            final BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();

            if (proxyConfig.getUsername() != null) {
                credentialsProvider.setCredentials(new AuthScope(proxyHost), new UsernamePasswordCredentials(
                        proxyConfig.getUsername(),
                        proxyConfig.getPassword()));
            }
            return httpClientBuilder
                    .setProxy(proxyHost)
                    .setDefaultCredentialsProvider(credentialsProvider);
        }
    }
}
