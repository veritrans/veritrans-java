package id.co.veritrans.mdk.v1.integration.vtdirect;

import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.model.FraudStatus;
import id.co.veritrans.mdk.v1.gateway.model.TransactionStatus;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;
import id.co.veritrans.mdk.v1.gateway.model.builder.CreditCardBuilder;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.CreditCard;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import static org.testng.Assert.assertEquals;

/**
 * Created by gde on 5/11/15.
 */
public class CreditCardAttempt3DsIT extends AbstractCreditCardIT {

    private final String orderId = String.valueOf(System.nanoTime());

    @Test(groups = "integrationTest")
    public void testCharge() throws RestClientException, URISyntaxException {
        final String cardToken = getToken("4411111111111118", "01", "2020", "123");
        final VtResponse vtResponse = charge(orderId, new CreditCardBuilder()
                .setCardToken(cardToken)
                .setAcquirerBank(CreditCard.Bank.BNI)
                .createCreditCard());

        assertEquals(vtResponse.getStatusCode(), "200");
        assertEquals(vtResponse.getTransactionStatus(), TransactionStatus.CAPTURED);
        assertEquals(vtResponse.getFraudStatus(), FraudStatus.ACCEPTED);
        assertEquals(vtResponse.getPaymentMethod(), "credit_card");
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
}
