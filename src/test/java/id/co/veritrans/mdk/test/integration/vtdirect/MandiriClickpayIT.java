package id.co.veritrans.mdk.test.integration.vtdirect;

import id.co.veritrans.mdk.test.integration.AbstractIntegrationTest;
import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.model.CustomerDetails;
import id.co.veritrans.mdk.v1.gateway.model.TransactionDetails;
import id.co.veritrans.mdk.v1.gateway.model.TransactionStatus;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.MandiriClickpayRequest;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.MandiriClickpay;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

/**
 * Created by gde on 5/8/15.
 */
public class MandiriClickpayIT extends AbstractIntegrationTest {

    final String orderId = String.valueOf(System.nanoTime());

    @Test(groups = "integrationTest")
    public void testChargeMandiriClickpay() throws RestClientException, UnsupportedEncodingException {
        final MandiriClickpayRequest req = new MandiriClickpayRequest();
        req.setTransactionDetails(new TransactionDetails(orderId, 10000l));
        req.setCustomerDetails(new CustomerDetails("gde", "satrigraha", "gde.satrigraha@veritrans.co.id", "123456789", null, null));
        req.setMandiriClickpay(new MandiriClickpay("4111111111111111", "1111111111", "10000", "12345", "000000"));

        final VtResponse vtResponse = vtDirect.charge(req);
        assertEquals(vtResponse.getStatusCode(), "200");
        assertEquals(vtResponse.getTransactionStatus(), TransactionStatus.SETTLED);
        assertNull(vtResponse.getFraudStatus());
    }

    @Test(groups = "integrationTest", dependsOnMethods = "testChargeMandiriClickpay")
    public void testStatusAfterCharge() throws RestClientException, UnsupportedEncodingException {
        final VtResponse vtResponse = vtDirect.status(orderId);
        assertEquals(vtResponse.getStatusCode(), "200");
        assertEquals(vtResponse.getTransactionStatus(), TransactionStatus.SETTLED);
    }

    @Test(groups = "integrationTest", dependsOnMethods = "testStatusAfterCharge")
    public void testCancelCharge() throws RestClientException, UnsupportedEncodingException {
        final VtResponse vtResponse = vtDirect.cancel(orderId);
        assertEquals(vtResponse.getStatusCode(), "412");
    }

    @Test(groups = "integrationTest", dependsOnMethods = "testCancelCharge")
    public void testStatusAfterCancelCharge() throws RestClientException, UnsupportedEncodingException {
        final VtResponse vtResponse = vtDirect.status(orderId);
        assertEquals(vtResponse.getStatusCode(), "200");
        assertEquals(vtResponse.getTransactionStatus(), TransactionStatus.SETTLED);
    }
}
