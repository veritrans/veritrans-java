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

/**
 * Created by gde on 5/11/15.
 */
public class CreditCardAttempt3DsDenyTransactionIT extends AbstractCreditCardIT {

    private final String orderId = String.valueOf(System.nanoTime());

    @Test(groups = "integrationTest")
    public void testCharge() throws RestClientException, URISyntaxException {
        final String cardToken = getToken("4711111111111115", "01", "2020", "123");
        final VtResponse vtResponse = charge(orderId, new CreditCard(cardToken, CreditCard.Bank.BNI, null, null, null, null));

        assertEquals(vtResponse.getStatusCode(), "202");
        assertEquals(vtResponse.getTransactionStatus(), TransactionStatus.DENIED);
        assertEquals(vtResponse.getFraudStatus(), FraudStatus.ACCEPTED);
        assertEquals(vtResponse.getPaymentMethod(), "credit_card");
    }

    @Test(groups = "integrationTest", dependsOnMethods = "testCharge")
    public void testStatusAfterCharge() throws RestClientException, UnsupportedEncodingException {
        final VtResponse vtResponse = vtDirect.status(orderId);
        assertEquals(vtResponse.getStatusCode(), "202");
        assertEquals(vtResponse.getTransactionStatus(), TransactionStatus.DENIED);
        assertEquals(vtResponse.getFraudStatus(), FraudStatus.ACCEPTED);
    }
}
