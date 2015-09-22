package id.co.veritrans.mdk.v1;

import id.co.veritrans.mdk.v1.config.EnvironmentType;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by andes on 5/8/15.
 */
public class VtGatewayConfigTest {

    @Test
    public void testVtGatewayConfigBuilder() {
        VtGatewayConfig config = new VtGatewayConfigBuilder()
                .setServerKey("a")
                .setClientKey("b")
                .setEnvironmentType(EnvironmentType.SANDBOX)
                .setMaxConnectionPoolSize(1)
                .setProxyConfig(TestUtil.buildProxyConfig())
                .createVtGatewayConfig();

        Assert.assertEquals(config.getServerKey(), "a");
        Assert.assertEquals(config.getClientKey(), "b");
        Assert.assertEquals(config.getEnvironmentType(), EnvironmentType.SANDBOX);
        Assert.assertEquals(config.getMaxConnectionPoolSize(), 1);
        Assert.assertNotNull(config.getProxyConfig());
    }
}
