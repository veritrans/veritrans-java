package id.co.veritrans.mdk;

import id.co.veritrans.mdk.config.EnvironmentType;
import id.co.veritrans.mdk.gateway.VtDirect;
import id.co.veritrans.mdk.gateway.VtWeb;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Created by andes on 5/6/15.
 */
public class VtGatewayTest {

    @Test
    public void testVtGatewayFactory() {
        /* Using default constructor */
        VtGatewayFactory factory = new VtGatewayFactory();
        factory.setServerKey("a");
        factory.setClientKey("b");
        factory.setProxyUsername("user");
        factory.setProxyPassword("pass");
        factory.setEnvironmentType(EnvironmentType.SANDBOX);

        assertEquals(factory.getServerKey(), "a");
        assertEquals(factory.getClientKey(), "b");
        assertEquals(factory.getProxyUsername(), "user");
        assertEquals(factory.getProxyPassword(), "pass");
        assertEquals(factory.getEnvironmentType(), EnvironmentType.SANDBOX);
        assertEquals(factory.getVtGatewayConfig().getServerKey(), "a");
        assertEquals(factory.getVtGatewayConfig().getClientKey(), "b");
        assertEquals(factory.getVtGatewayConfig().getProxyUsername(), "user");
        assertEquals(factory.getVtGatewayConfig().getProxyPassword(), "pass");
        assertEquals(factory.getVtGatewayConfig().getEnvironmentType(), EnvironmentType.SANDBOX);

        /* Using parameterized constructor*/
        VtGatewayConfig config = new VtGatewayConfig("a", "b", EnvironmentType.PRODUCTION);
        VtGatewayFactory factoryParamterized = new VtGatewayFactory(config);
        assertEquals(factoryParamterized.getServerKey(), "a");
        assertEquals(factoryParamterized.getClientKey(), "b");
        assertEquals(factoryParamterized.getEnvironmentType(), EnvironmentType.PRODUCTION);
        assertNull(factoryParamterized.getProxyUsername());
        assertNull(factoryParamterized.getProxyPassword());
    }

    @Test
    public void testNewVtDirectAndVtWeb() {
        VtGatewayFactory factory = new VtGatewayFactory();
        factory.setServerKey("a");
        factory.setClientKey("b");
        factory.setEnvironmentType(EnvironmentType.SANDBOX);

        VtDirect vtDirect = factory.newVtDirect();
        assertNotNull(vtDirect);

        VtWeb vtWeb = factory.newVtWeb();
        assertNotNull(vtWeb);
    }
}
