package id.co.veritrans.mdk.v1.gateway.impl;

import id.co.veritrans.mdk.gateway.model.VtResponse;
import id.co.veritrans.mdk.v1.VtGatewayConfig;
import id.co.veritrans.mdk.v1.config.ProxyConfig;
import id.co.veritrans.mdk.v1.exception.VtConnectionException;
import id.co.veritrans.mdk.v1.gateway.VtGatewaySession;
import id.co.veritrans.mdk.v1.gateway.model.VtRequest;
import id.co.veritrans.mdk.v1.helper.JsonUtil;
import id.co.veritrans.mdk.v1.helper.ValidationUtil;
import id.co.veritrans.mdk.v1.net.VtRestClient;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by gde on 5/8/15.
 */
public class DefaultVtGatewaySession implements VtGatewaySession, VtRestClient {

    private final Validator validator;
    private final VtGatewayConfig vtGatewayConfig;
    private final PoolingHttpClientConnectionManager connectionManager;
    private final CloseableHttpClient httpClient;

    public DefaultVtGatewaySession(final VtGatewayConfig vtGatewayConfig, final Validator validator) {
        this.validator = validator;
        this.vtGatewayConfig = vtGatewayConfig;
        this.connectionManager = new PoolingHttpClientConnectionManager();

        if (connectionManager.getMaxTotal() < vtGatewayConfig.getMaxConnectionPoolSize()) {
            connectionManager.setMaxTotal(vtGatewayConfig.getMaxConnectionPoolSize());
        }
        connectionManager.setDefaultMaxPerRoute(vtGatewayConfig.getMaxConnectionPoolSize());

        httpClient = buildHttpClient();
    }

    @Override
    public VtGatewayConfig getVtGatewayConfig() {
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

    @Override
    public VtRestClient getRestClient() {
        return this;
    }

    @Override
    public VtResponse get(final String url) throws VtConnectionException {
        try {
            final HttpResponse httpResponse = getHttpClient().execute(new HttpGet(url));
            return null;
        } catch (IOException e) {
            throw new VtConnectionException(e);
        }
    }

    @Override
    public VtResponse post(final String url) throws VtConnectionException {
        try {
            final HttpResponse httpResponse = getHttpClient().execute(new HttpPost(url));
            return null;
        } catch (IOException e) {
            throw new VtConnectionException(e);
        }
    }

    @Override
    public VtResponse post(final String url, final VtRequest vtRequest) throws VtConnectionException {
        validate(vtRequest);
        try {
            final HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(JsonUtil.toJson(vtRequest)));

            final HttpResponse httpResponse = getHttpClient().execute(httpPost);
            return null;
        } catch (IOException e) {
            throw new VtConnectionException(e);
        }
    }

    private void validate(final VtRequest vtRequest) {
        final Set<ConstraintViolation<VtRequest>> err = ValidationUtil.getValidator().validate(vtRequest);
        if (!err.isEmpty()) {
            throw new ConstraintViolationException(err);
        }
    }
}
