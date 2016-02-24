package id.co.veritrans.mdk.v1.integration.vtdirect;

import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.model.FraudStatus;
import id.co.veritrans.mdk.v1.gateway.model.TransactionStatus;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.CreditCardFullPan;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Created by gde on 5/11/15.
 */
public class CreditCardFullPan3DsIT extends AbstractCreditCardIT {

    private final String orderId = String.valueOf(System.nanoTime());

    @Test(groups = "integrationTest")
    public void testCharge() throws RestClientException, URISyntaxException, IOException {
        /* Get redirection URL for 3DS */
        VtResponse vtResponse = charge(orderId, new CreditCardFullPan("5211111111111117", "123", "01", "2020", true));
        String redirectUrl = vtResponse.getRedirectUrl();
        assertEquals(vtResponse.getStatusCode(), "201");
        assertNotNull(redirectUrl);

        /* Check status before follow redirect URL */
        vtResponse = vtDirect.status(orderId);
        assertEquals(vtResponse.getStatusCode(), "404");

        /* Check status after follow redirect URL*/
        do3Ds(redirectUrl);
        vtResponse = vtDirect.status(orderId);
        assertEquals(vtResponse.getStatusCode(), "200");
        assertEquals(vtResponse.getTransactionStatus(), TransactionStatus.CAPTURED);
        assertEquals(vtResponse.getFraudStatus(), FraudStatus.ACCEPTED);
    }

    @Test(groups = "integrationTest", dependsOnMethods = "testCharge")
    public void testCancelCharge() throws RestClientException, UnsupportedEncodingException {
        final VtResponse vtResponse = vtDirect.cancel(orderId);
        assertEquals(vtResponse.getStatusCode(), "200");
        assertEquals(vtResponse.getTransactionStatus(), TransactionStatus.CANCELLED);
        assertEquals(vtResponse.getFraudStatus(), FraudStatus.ACCEPTED);
    }

    @Test(groups = "integrationTest", dependsOnMethods = "testCancelCharge")
    public void testStatusAfterCancelCharge() throws RestClientException, UnsupportedEncodingException {
        final VtResponse vtResponse = vtDirect.status(orderId);
        assertEquals(vtResponse.getStatusCode(), "200");
        assertEquals(vtResponse.getTransactionStatus(), TransactionStatus.CANCELLED);
        assertEquals(vtResponse.getFraudStatus(), FraudStatus.ACCEPTED);
    }
}
