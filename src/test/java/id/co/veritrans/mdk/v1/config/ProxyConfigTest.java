package id.co.veritrans.mdk.v1.config;

import id.co.veritrans.mdk.util.ValidationUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * Created by andes on 5/8/15.
 */
public class ProxyConfigTest {

    @Test
    public void testProxyConfig() {
        ProxyConfig proxyConfig = new ProxyConfigBuilder()
                .setHost("127.0.0.1")
                .setPort(1234)
                .setUsername("username")
                .setPassword("password")
                .build();

        Set<ConstraintViolation<ProxyConfig>> error = ValidationUtil.getValidator().validate(proxyConfig);
        Assert.assertTrue(error.isEmpty());

        Assert.assertEquals(proxyConfig.getHost(), "127.0.0.1");
        Assert.assertEquals(proxyConfig.getPort(), 1234);
        Assert.assertEquals(proxyConfig.getUsername(), "username");
        Assert.assertEquals(proxyConfig.getPassword(), "password");
    }

    @Test
    public void testProxyConfigError() {
        ProxyConfig proxyConfig = new ProxyConfigBuilder().build();

        Set<ConstraintViolation<ProxyConfig>> error = ValidationUtil.getValidator().validate(proxyConfig);
        Assert.assertFalse(error.isEmpty());

        String errorMessage = ValidationUtil.buildExceptionMessage(error.toArray());
        Assert.assertTrue(errorMessage.contains("host"));
    }
}
