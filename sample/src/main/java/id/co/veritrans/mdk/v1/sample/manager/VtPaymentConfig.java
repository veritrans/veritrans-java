package id.co.veritrans.mdk.v1.sample.manager;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by gde on 5/14/15.
 */
@Component
@ConfigurationProperties(prefix = "payment.config.vt")
public class VtPaymentConfig {

    private String clientKey;
    private String serverKey;
    private String environment;
    private int connectionPoolSize;

    public String getClientKey() {
        return clientKey;
    }

    public void setClientKey(final String clientKey) {
        this.clientKey = clientKey;
    }

    public String getServerKey() {
        return serverKey;
    }

    public void setServerKey(final String serverKey) {
        this.serverKey = serverKey;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(final String environment) {
        this.environment = environment;
    }

    public int getConnectionPoolSize() {
        return connectionPoolSize;
    }

    public void setConnectionPoolSize(final int connectionPoolSize) {
        this.connectionPoolSize = connectionPoolSize;
    }
}
