package id.co.veritrans.mdk.test.integration;

import id.co.veritrans.mdk.v1.VtGatewayConfigBuilder;
import id.co.veritrans.mdk.v1.VtGatewayFactory;
import id.co.veritrans.mdk.v1.config.EnvironmentType;
import id.co.veritrans.mdk.v1.gateway.VtDirect;
import id.co.veritrans.mdk.v1.gateway.VtWeb;

/**
 * Created by gde on 5/11/15.
 */
public class AbstractIntegrationTest {

    protected final VtGatewayFactory vtGatewayFactory = new VtGatewayFactory(new VtGatewayConfigBuilder()
            .setClientKey("VT-client-L0ixAGIFOyskZpAi")
            .setServerKey("VT-server-O49fyFz6BWRkAfyQuyBWj3U4")
            .setEnvironmentType(EnvironmentType.SANDBOX)
            .setMaxConnectionPoolSize(16)
            .createVtGatewayConfig()
    );
    protected final VtDirect vtDirect = vtGatewayFactory.vtDirect();
    protected final VtWeb vtWeb = vtGatewayFactory.vtWeb();
}
