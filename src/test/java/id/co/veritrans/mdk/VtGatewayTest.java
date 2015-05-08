package id.co.veritrans.mdk;

import id.co.veritrans.mdk.v1.VtGatewayConfig;
import id.co.veritrans.mdk.v1.VtGatewayConfigBuilder;
import id.co.veritrans.mdk.v1.VtGatewayFactory;
import id.co.veritrans.mdk.v1.config.EnvironmentType;
import id.co.veritrans.mdk.v1.config.ProxyConfigBuilder;
import id.co.veritrans.mdk.v1.gateway.VtDirect;
import id.co.veritrans.mdk.v1.gateway.VtWeb;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolationException;

import static org.testng.Assert.*;

/**
 * Created by andes on 5/6/15.
 */
public class VtGatewayTest {

    @Test
    public void testVtGatewayFactory() {
        /* Using default constructor */
        VtGatewayFactory factory = new VtGatewayFactory(new VtGatewayConfigBuilder()
                .setClientKey("b")
                .setServerKey("a")
                .setEnvironmentType(EnvironmentType.SANDBOX)
                .setMaxConnectionPoolSize(16)
                .setProxyConfig(new ProxyConfigBuilder().setHost("host").setUsername("user").setPassword("pass").createProxyConfig())
                .createVtGatewayConfig());

        assertEquals(factory.getServerKey(), "a");
        assertEquals(factory.getClientKey(), "b");
        assertEquals(factory.getProxyUsername(), "user");
        assertEquals(factory.getProxyPassword(), "pass");
        assertEquals(factory.getEnvironmentType(), EnvironmentType.SANDBOX);
        assertEquals(factory.getVtGatewayConfig().getServerKey(), "a");
        assertEquals(factory.getVtGatewayConfig().getClientKey(), "b");
        assertEquals(factory.getVtGatewayConfig().getProxyConfig().getUsername(), "user");
        assertEquals(factory.getVtGatewayConfig().getProxyConfig().getPassword(), "pass");
        assertEquals(factory.getVtGatewayConfig().getEnvironmentType(), EnvironmentType.SANDBOX);

        /* Using parameterized constructor*/
        VtGatewayConfig config = new VtGatewayConfig(EnvironmentType.PRODUCTION, "a", "b", 16, null);
        VtGatewayFactory factoryParamterized = new VtGatewayFactory(config);
        assertEquals(factoryParamterized.getServerKey(), "a");
        assertEquals(factoryParamterized.getClientKey(), "b");
        assertEquals(factoryParamterized.getEnvironmentType(), EnvironmentType.PRODUCTION);
        assertNull(factoryParamterized.getProxyUsername());
        assertNull(factoryParamterized.getProxyPassword());
    }

    @Test
    public void testNewVtDirectAndVtWeb() {
        VtGatewayFactory factory = new VtGatewayFactory(new VtGatewayConfigBuilder()
                .setClientKey("b")
                .setServerKey("a")
                .setEnvironmentType(EnvironmentType.SANDBOX)
                .setMaxConnectionPoolSize(16)
                .createVtGatewayConfig());

        VtDirect vtDirect = factory.vtDirect();
        assertNotNull(vtDirect);

        VtWeb vtWeb = factory.vtWeb();
        assertNotNull(vtWeb);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testInvalidConfigVtDirect() {
        VtGatewayFactory factory = new VtGatewayFactory(new VtGatewayConfigBuilder()
                .setServerKey("a")
                .setEnvironmentType(EnvironmentType.SANDBOX)
                .createVtGatewayConfig());

        VtDirect vtDirect = factory.vtDirect();
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testInvalidConfigVtWeb() {
        VtGatewayFactory factory = new VtGatewayFactory(new VtGatewayConfigBuilder()
                .setServerKey("a")
                .setEnvironmentType(EnvironmentType.PRODUCTION)
                .createVtGatewayConfig());

        VtWeb vtWeb = factory.vtWeb();
    }
}
