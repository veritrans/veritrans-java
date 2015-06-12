package id.co.veritrans.mdk.v1.integration.vtdirect;

import id.co.veritrans.mdk.v1.TestUtil;
import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.model.TransactionDetails;
import id.co.veritrans.mdk.v1.gateway.model.TransactionStatus;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.CimbClicksRequest;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.CimbClicks;
import id.co.veritrans.mdk.v1.integration.AbstractIntegrationTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;

/**
 * Created by andes on 5/11/15.
 */
public class CimbClicksIT extends AbstractIntegrationTest {

    final String orderId = String.valueOf(System.nanoTime());

    @Test(groups = "integrationTest")
    public void testCimbClicksCharge() throws RestClientException, UnsupportedEncodingException {
        final CimbClicksRequest request = new CimbClicksRequest();
        request.setTransactionDetails(new TransactionDetails(orderId, 10000L));
        request.setCustomerDetails(TestUtil.buildCustomerDetails());
        request.setCimbClicks(new CimbClicks("Test transaksi"));

        VtResponse response = vtDirect.charge(request);
        Assert.assertEquals(response.getOrderId(), orderId);
        Assert.assertEquals(response.getStatusCode(), "201");
        Assert.assertEquals(response.getTransactionStatus(), TransactionStatus.PENDING);
        Assert.assertEquals(response.getPaymentMethod(), "cimb_clicks");
        Assert.assertTrue(response.getStatusMessage().startsWith("Success"));
        Assert.assertNotNull(response.getRedirectUrl());

        response = vtDirect.status(orderId);
        Assert.assertEquals(response.getTransactionStatus(), TransactionStatus.PENDING);
        Assert.assertEquals(response.getStatusCode(), "201");
        Assert.assertTrue(response.getStatusMessage().startsWith("Success"));
    }

    @Test(groups = "integrationTest", dependsOnMethods = "testCimbClicksCharge")
    public void testCancelCimbClicks() throws RestClientException, UnsupportedEncodingException {
        VtResponse response = vtDirect.cancel(orderId);
        Assert.assertEquals(response.getOrderId(), orderId);
        Assert.assertEquals(response.getTransactionStatus(), TransactionStatus.CANCELLED);
        Assert.assertEquals(response.getStatusCode(), "200");
        Assert.assertTrue(response.getStatusMessage().startsWith("Success"));

        response = vtDirect.status(orderId);
        Assert.assertEquals(response.getTransactionStatus(), TransactionStatus.CANCELLED);
        Assert.assertEquals(response.getStatusCode(), "200");
        Assert.assertTrue(response.getStatusMessage().startsWith("Success"));
    }
}
