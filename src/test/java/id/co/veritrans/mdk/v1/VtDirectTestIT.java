package id.co.veritrans.mdk.v1;

import id.co.veritrans.mdk.v1.config.EnvironmentType;
import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.VtDirect;
import id.co.veritrans.mdk.v1.gateway.model.*;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.MandiriClickpayRequest;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.MandiriClickpay;
import id.co.veritrans.mdk.v1.helper.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;

/**
 * Created by gde on 5/8/15.
 */
public class VtDirectTestIT {

    private final VtGatewayFactory vtGatewayFactory = new VtGatewayFactory(new VtGatewayConfigBuilder()
            .setClientKey("VT-client-L0ixAGIFOyskZpAi")
            .setServerKey("VT-server-O49fyFz6BWRkAfyQuyBWj3U4")
            .setEnvironmentType(EnvironmentType.SANDBOX)
            .setMaxConnectionPoolSize(16)
            .createVtGatewayConfig()
    );
    private final VtDirect vtDirect = vtGatewayFactory.vtDirect();

    @Test
    public void testChargeMandiriClickpay() throws RestClientException, UnsupportedEncodingException {
        final String orderId = String.valueOf(System.nanoTime());
        final MandiriClickpayRequest req = new MandiriClickpayRequest();
        req.setTransactionDetails(new TransactionDetails(orderId, 10000l));
        req.setMandiriClickpay(new MandiriClickpay("4111111111111111", "1111111111", "10000", "12345", "000000"));
        req.setCustomerDetails(new CustomerDetails("gde", "satrigraha", "gde.satrigraha@veritrans.co.id", "123456789", null, null));

        VtResponse vtResponse = vtDirect.charge(req);
        System.out.println("charge: " + JsonUtil.toJson(vtResponse));
        Assert.assertEquals(vtResponse.getStatusCode(), "200");
        Assert.assertEquals(vtResponse.getTransactionStatus(), TransactionStatus.SETTLED);
        Assert.assertNull(vtResponse.getFraudStatus());

        vtResponse = vtDirect.cancel(orderId);
        System.out.println("cancel: " + JsonUtil.toJson(vtResponse));
        Assert.assertEquals(vtResponse.getStatusCode(), "412");
        Assert.assertNull(vtResponse.getFraudStatus());

        vtResponse = vtDirect.status(orderId);
        System.out.println("status: " + JsonUtil.toJson(vtResponse));
        Assert.assertEquals(vtResponse.getStatusCode(), "200");
        Assert.assertEquals(vtResponse.getTransactionStatus(), TransactionStatus.SETTLED);
        Assert.assertNull(vtResponse.getFraudStatus());
    }

    @Test
    public void testCaptureUnknown() throws RestClientException, UnsupportedEncodingException {
        VtResponse vtResponse = vtDirect.capture("unknown", 10000l);
        Assert.assertEquals(vtResponse.getStatusCode(), "404");
    }
}
