package id.co.veritrans.mdk.v1.gateway.impl;

import id.co.veritrans.mdk.v1.VtGatewayConfig;
import id.co.veritrans.mdk.v1.config.ProxyConfig;
import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.VtGatewaySession;
import id.co.veritrans.mdk.v1.gateway.model.VtRequest;
import id.co.veritrans.mdk.v1.helper.JsonUtil;
import id.co.veritrans.mdk.v1.net.VtRestClient;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gde on 5/8/15.
 */
public class DefaultVtGatewaySession implements VtGatewaySession, VtRestClient {

    private final VtGatewayConfig vtGatewayConfig;
    private final PoolingHttpClientConnectionManager connectionManager;
    private final CloseableHttpClient httpClient;

    public DefaultVtGatewaySession(final VtGatewayConfig vtGatewayConfig) {
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
        final String authHeaderVal = vtGatewayConfig.getServerKey() + ":";
        final List<Header> defaultHeaders = Arrays.asList(
                (Header) new BasicHeader("Accept", "application/json"),
                (Header) new BasicHeader("Authorization", "Basic " + Base64.encodeBase64String(authHeaderVal.getBytes()))
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
    public <T> T get(Class<T> responseClass, URI uri) throws RestClientException {
        try {
            final String url = vtGatewayConfig.getEnvironmentType().getBaseUrl() + "/" + uri.toString();
            final HttpGet httpGet = new HttpGet(url);
            httpGet.setConfig(getRequestConfig());

            final CloseableHttpResponse httpResponse = getHttpClient().execute(httpGet);
            try {
                return JsonUtil.fromJson(httpResponse, responseClass);
            } finally {
                httpResponse.close();
            }
        } catch (Exception e) {
            throw new RestClientException(e);
        }
    }

    @Override
    public <T> T get(Class<T> responseClass, final String path) throws RestClientException {
        try {
            final String url = vtGatewayConfig.getEnvironmentType().getBaseUrl() + "/" + path;
            final HttpGet httpGet = new HttpGet(url);
            httpGet.setConfig(getRequestConfig());

            final CloseableHttpResponse httpResponse = getHttpClient().execute(httpGet);
            try {
                return JsonUtil.fromJson(httpResponse, responseClass);
            } finally {
                httpResponse.close();
            }
        } catch (Exception e) {
            throw new RestClientException(e);
        }
    }

    @Override
    public <T> T post(Class<T> responseClass, String path) throws RestClientException {
        try {
            final String url = vtGatewayConfig.getEnvironmentType().getBaseUrl() + "/" + path;
            final HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(getRequestConfig());

            final CloseableHttpResponse httpResponse = getHttpClient().execute(httpPost);
            try {
                return JsonUtil.fromJson(httpResponse, responseClass);
            } finally {
                httpResponse.close();
            }
        } catch (Exception e) {
            throw new RestClientException(e);
        }
    }

    @Override
    public <T> T post(Class<T> responseClass, String path, final VtRequest vtRequest) throws RestClientException {
        try {
            final String url = vtGatewayConfig.getEnvironmentType().getBaseUrl() + "/" + path;
            final HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(getRequestConfig());
            httpPost.setEntity(new StringEntity(JsonUtil.toJson(vtRequest)));
            httpPost.addHeader(new BasicHeader("Content-Type", "application/json"));

            final CloseableHttpResponse httpResponse = getHttpClient().execute(httpPost);
            try {
                return JsonUtil.fromJson(httpResponse, responseClass);
            } finally {
                httpResponse.close();
            }
        } catch (Exception e) {
            throw new RestClientException(e);
        }
    }

    private RequestConfig getRequestConfig() {
        RequestConfig requestConfig = RequestConfig.copy(RequestConfig.DEFAULT)
                .setConnectTimeout(vtGatewayConfig.getConnectTimeout())
                .setSocketTimeout(vtGatewayConfig.getSocketTimeout())
                .build();

        return requestConfig;
    }
}
