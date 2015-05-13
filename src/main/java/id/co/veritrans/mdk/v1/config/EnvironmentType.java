package id.co.veritrans.mdk.v1.config;

import java.net.URI;

/**
 * Enum of veritrans environment type API.
 */
public enum EnvironmentType {
    SANDBOX(URI.create("https://api.sandbox.veritrans.co.id/v2")),
    PRODUCTION(URI.create("https://api.veritrans.co.id/v2"));

    private final URI baseUrl;

    private EnvironmentType(final URI baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * Returns veritrans base URL API based on environment that used
     *
     * @return  veritrans base URL API
     */
    public URI getBaseUrl() {
        return baseUrl;
    }
}
