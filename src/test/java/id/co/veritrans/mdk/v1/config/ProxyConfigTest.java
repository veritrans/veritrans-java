package id.co.veritrans.mdk.v1.config;

import org.testng.Assert;
import org.testng.annotations.Test;

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
                .createProxyConfig();

        Assert.assertEquals(proxyConfig.getHost(), "127.0.0.1");
        Assert.assertEquals(proxyConfig.getPort(), 1234);
        Assert.assertEquals(proxyConfig.getUsername(), "username");
        Assert.assertEquals(proxyConfig.getPassword(), "password");
    }

}
