package id.co.veritrans.mdk.v1;

import id.co.veritrans.mdk.TestUtil;
import id.co.veritrans.mdk.v1.exception.InvalidVtConfigException;
import id.co.veritrans.mdk.v1.gateway.VtDirect;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by andes on 5/8/15.
 */
public class VtGatewayFactoryTest {

    @Test
    public void testVtGatewayFactory() throws InvalidVtConfigException {
        VtGatewayFactory factory = new VtGatewayFactory(TestUtil.buildVtGatewayConfig());
        VtDirect vtDirect = factory.vtDirect();

        Assert.assertNotNull(vtDirect);
    }
}
