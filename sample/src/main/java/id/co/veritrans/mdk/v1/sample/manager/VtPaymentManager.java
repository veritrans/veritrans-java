package id.co.veritrans.mdk.v1.sample.manager;

import id.co.veritrans.mdk.v1.VtGatewayConfigBuilder;
import id.co.veritrans.mdk.v1.VtGatewayFactory;
import id.co.veritrans.mdk.v1.config.EnvironmentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by gde on 5/14/15.
 */
@Component
public class VtPaymentManager {

    @Autowired
    private VtPaymentConfig vtPaymentConfig;
    private VtGatewayFactory vtGatewayFactory;

    @PostConstruct
    public void setup() {
        vtGatewayFactory = new VtGatewayFactory(new VtGatewayConfigBuilder()
                .setClientKey(vtPaymentConfig.getClientKey())
                .setServerKey(vtPaymentConfig.getServerKey())
                .setEnvironmentType(EnvironmentType.valueOf(vtPaymentConfig.getEnvironment()))
                .setMaxConnectionPoolSize(vtPaymentConfig.getConnectionPoolSize())
                .createVtGatewayConfig()
        );
    }

    public VtGatewayFactory getVtGatewayFactory() {
        return vtGatewayFactory;
    }
}
