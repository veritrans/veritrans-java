package id.co.veritrans.mdk.v1.integration.vtdirect;

import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.model.FraudStatus;
import id.co.veritrans.mdk.v1.gateway.model.TransactionStatus;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.CreditCard;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Created by gde on 5/11/15.
 */
public class CreditCardFull3DsChargeSavedTokenIT extends AbstractCreditCardIT {

    private final String orderId = String.valueOf(System.nanoTime());
    private final String orderId2 = String.valueOf(System.nanoTime() + 1);
    private String cardToken;

    @Test(groups = "integrationTest")
    public void testCharge() throws RestClientException, URISyntaxException {
        final String cardToken = getToken("4811111111111114", "01", "2020", "123");
        final VtResponse vtResponse = charge(orderId, new CreditCard(cardToken, CreditCard.Bank.BNI, null, null, null, Boolean.TRUE));

        assertEquals(vtResponse.getStatusCode(), "200");
        assertEquals(vtResponse.getTransactionStatus(), TransactionStatus.CAPTURED);
        assertEquals(vtResponse.getFraudStatus(), FraudStatus.ACCEPTED);
        assertNotNull(vtResponse.getTransactionId());
        assertNotNull(vtResponse.getGrossAmount());

        assertNotNull(vtResponse.getSavedCardToken());
        assertNotNull(vtResponse.getSavedCardTokenExpiredAt());
        assertNotNull(vtResponse.getSecureToken());

        this.cardToken = vtResponse.getSavedCardToken();
    }

    @Test(groups = "integrationTest", dependsOnMethods = "testCharge")
    public void testStatusAfterCharge() throws RestClientException, UnsupportedEncodingException {
        final VtResponse vtResponse = vtDirect.status(orderId);
        assertEquals(vtResponse.getStatusCode(), "200");
        assertEquals(vtResponse.getTransactionStatus(), TransactionStatus.CAPTURED);
        assertEquals(vtResponse.getFraudStatus(), FraudStatus.ACCEPTED);
    }

    @Test(groups = "integrationTest", dependsOnMethods = "testStatusAfterCharge")
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

    @Test(groups = "disabled", dependsOnMethods = "testCharge")
    public void testChargeUsingSavedToken() throws RestClientException, URISyntaxException {
        final VtResponse vtResponse = charge(orderId2, new CreditCard(cardToken, CreditCard.Bank.BNI, null, null, null, null));

        assertEquals(vtResponse.getStatusCode(), "200");
        assertEquals(vtResponse.getTransactionStatus(), TransactionStatus.CAPTURED);
        assertEquals(vtResponse.getFraudStatus(), FraudStatus.ACCEPTED);
        assertNotNull(vtResponse.getTransactionId());
        assertNotNull(vtResponse.getGrossAmount());

        assertNotNull(vtResponse.getSavedCardToken());
        assertNotNull(vtResponse.getSavedCardTokenExpiredAt());
        assertNotNull(vtResponse.getSecureToken());
    }
}
