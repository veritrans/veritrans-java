package id.co.veritrans.mdk.config;

/**
 * Created by gde on 5/4/15.
 */
public enum EnvironmentType {
    SANDBOX("https://api.sandbox.veritrans.co.id/v2"),
    PRODUCTION("https://api.veritrans.co.id/v2");

    private final String baseUrl;

    private EnvironmentType(final String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
