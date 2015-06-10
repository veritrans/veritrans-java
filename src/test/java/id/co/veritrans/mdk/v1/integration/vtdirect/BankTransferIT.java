package id.co.veritrans.mdk.v1.integration.vtdirect;

import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.model.CustomerDetails;
import id.co.veritrans.mdk.v1.gateway.model.TransactionDetails;
import id.co.veritrans.mdk.v1.gateway.model.TransactionStatus;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.BankTransferRequest;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.BankTransfer;
import id.co.veritrans.mdk.v1.integration.AbstractIntegrationTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;

/**
 * Created by andes on 5/11/15.
 */
public class BankTransferIT extends AbstractIntegrationTest {

    final String orderId = String.valueOf(System.nanoTime());

    @Test(groups = "integrationTest")
    public void testChargeBankTransfer() throws RestClientException, UnsupportedEncodingException {
        final BankTransferRequest request = new BankTransferRequest();
        request.setTransactionDetails(new TransactionDetails(orderId, 10000L));
        request.setCustomerDetails(new CustomerDetails("gde", "satrigraha", "gde.satrigraha@veritrans.co.id", "123456789", null, null));
        request.setBankTransfer(new BankTransfer(BankTransfer.Bank.PERMATA));

        VtResponse response = vtDirect.charge(request);
        Assert.assertTrue(response.getStatusMessage().startsWith("Success"));
        Assert.assertEquals(response.getOrderId(), orderId);
        Assert.assertEquals(response.getTransactionStatus(), TransactionStatus.PENDING);
        Assert.assertEquals(response.getStatusCode(), "201");
        Assert.assertNotNull(response.getPermataVaNumber());
        Assert.assertNotNull(response.getTransactionId());

        response = vtDirect.status(orderId);
        Assert.assertEquals(response.getTransactionStatus(), TransactionStatus.PENDING);
        Assert.assertEquals(response.getStatusCode(), "201");
        Assert.assertTrue(response.getStatusMessage().startsWith("Success"));
    }

    @Test(groups = "integrationTest", dependsOnMethods = "testChargeBankTransfer")
    public void testCancelBankTransfer() throws RestClientException, UnsupportedEncodingException {
        VtResponse response = vtDirect.cancel(orderId);
        Assert.assertEquals(response.getTransactionStatus(), TransactionStatus.CANCELLED);
        Assert.assertEquals(response.getStatusCode(), "200");
        Assert.assertTrue(response.getStatusMessage().startsWith("Success"));

        response = vtDirect.status(orderId);
        Assert.assertEquals(response.getTransactionStatus(), TransactionStatus.CANCELLED);
        Assert.assertEquals(response.getStatusCode(), "200");
        Assert.assertTrue(response.getStatusMessage().startsWith("Success"));
    }
}
