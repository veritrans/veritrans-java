package id.co.veritrans.mdk.v1;

import id.co.veritrans.mdk.TestUtil;
import id.co.veritrans.mdk.v1.config.EnvironmentType;
import id.co.veritrans.mdk.v1.helper.ValidationUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

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

        Set<ConstraintViolation<VtGatewayConfig>> err = ValidationUtil.getValidator().validate(config);
        Assert.assertTrue(err.isEmpty());

        Assert.assertEquals(config.getServerKey(), "a");
        Assert.assertEquals(config.getClientKey(), "b");
        Assert.assertEquals(config.getEnvironmentType(), EnvironmentType.SANDBOX);
        Assert.assertEquals(config.getMaxConnectionPoolSize(), 1);
        Assert.assertNotNull(config.getProxyConfig());
    }

    @Test
    public void testVtGatewayConfigError() {
        VtGatewayConfig config = new VtGatewayConfigBuilder()
                .setEnvironmentType(EnvironmentType.PRODUCTION)
                .setMaxConnectionPoolSize(0)
                .createVtGatewayConfig();

        Set<ConstraintViolation<VtGatewayConfig>> err = ValidationUtil.getValidator().validate(config);
        Assert.assertFalse(err.isEmpty());

        String errorMessage = ValidationUtil.buildExceptionMessage(err.toArray(new ConstraintViolation[0]));
        Assert.assertTrue(errorMessage.contains("maxConnectionPoolSize"));
        Assert.assertTrue(errorMessage.contains("clientKey"));
        Assert.assertTrue(errorMessage.contains("serverKey"));
    }
}
