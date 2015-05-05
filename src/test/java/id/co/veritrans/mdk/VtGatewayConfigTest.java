package id.co.veritrans.mdk;

import id.co.veritrans.mdk.config.EnvironmentType;
import id.co.veritrans.mdk.config.MerchantCredential;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Created by andes on 5/5/15.
 */
public class VtGatewayConfigTest {

    @Test
    public void testParameterizedConstructor() {
        MerchantCredential merchantCredential = new MerchantCredential("server_key", "client_key");
        VtGatewayConfig config = new VtGatewayConfig(merchantCredential, EnvironmentType.SANDBOX);

        assertEquals(config.getMerchantCredential(), merchantCredential);
        assertEquals(config.getEnvironmentType(), EnvironmentType.SANDBOX);
    }

    @Test
    public void testEmptyConstructor() {
        MerchantCredential merchantCredential = new MerchantCredential("server_key", "client_key");
        VtGatewayConfig config = new VtGatewayConfig();

        config.setMerchantCredential(merchantCredential);
        config.setEnvironmentType(EnvironmentType.PRODUCTION);

        assertEquals(config.getMerchantCredential(), merchantCredential);
        assertEquals(config.getEnvironmentType(), EnvironmentType.PRODUCTION);
    }
}
