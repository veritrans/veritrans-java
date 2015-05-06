package id.co.veritrans.mdk.config;

/**
 * Enum of veritrans environment type API.
 */
public enum EnvironmentType {
    SANDBOX("https://api.sandbox.veritrans.co.id/v2"),
    PRODUCTION("https://api.veritrans.co.id/v2");

    private final String baseUrl;

    private EnvironmentType(final String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * Returns veritrans base URL API based on environment that used
     *
     * @return  veritrans base URL API
     */
    public String getBaseUrl() {
        return baseUrl;
    }
}
