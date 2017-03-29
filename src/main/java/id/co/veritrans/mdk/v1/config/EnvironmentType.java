package id.co.veritrans.mdk.v1.config;

import java.net.URI;

/**
 * Enum of veritrans environment type API.
 */
public enum EnvironmentType {
    SANDBOX(URI.create("https://api.sandbox.veritrans.co.id/v2"), URI.create("https://app.sandbox.veritrans.co.id")),
    PRODUCTION(URI.create("https://api.veritrans.co.id/v2"), URI.create("https://app.sandbox.veritrans.co.id"));

    private final URI baseUrl;
    private final URI snapUrl;

    private EnvironmentType(final URI baseUrl, final URI snapUrl) {
        this.baseUrl = baseUrl;
        this.snapUrl = snapUrl;
    }

    /**
     * Returns veritrans base URL API based on environment that used
     *
     * @return  veritrans base URL API
     */
    public URI getBaseUrl() {
        return baseUrl;
    }

    /**
     * Return snap URL based on the environment being used
     * @return
     */
    public URI getSnapUrl() {
        return snapUrl;
    }
}
