package id.co.veritrans.mdk.v1.integration.vtdirect;

import id.co.veritrans.mdk.v1.TestUtil;
import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.model.TransactionDetails;
import id.co.veritrans.mdk.v1.gateway.model.TransactionStatus;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.BcaKlikpayRequest;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.BcaKlikpay;
import id.co.veritrans.mdk.v1.integration.AbstractIntegrationTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;

/**
 * Created by andes on 5/13/15.
 */
public class BcaKlikpayIT extends AbstractIntegrationTest {

    final String orderId = String.valueOf(System.nanoTime());

    @Test(groups = "integrationTest")
    public void testChargeBcaKlikpay() throws RestClientException, UnsupportedEncodingException {
        final BcaKlikpayRequest request = new BcaKlikpayRequest();
        request.setTransactionDetails(new TransactionDetails(orderId, 10000L));
        request.setCustomerDetails(TestUtil.buildCustomerDetails());
        request.setItemDetails(TestUtil.buildTransactionItems());
        request.setBcaKlikpay(new BcaKlikpay(1, 0, "Test"));

        VtResponse response = vtDirect.charge(request);
        Assert.assertEquals(response.getOrderId(), orderId);
        Assert.assertEquals(response.getStatusCode(), "201");
        Assert.assertEquals(response.getTransactionStatus(), TransactionStatus.PENDING);
        Assert.assertTrue(response.getStatusMessage().contains("success"));
        Assert.assertNotNull(response.getRedirectUrl());

        response = vtDirect.status(orderId);
        Assert.assertEquals(response.getTransactionStatus(), TransactionStatus.PENDING);
        Assert.assertEquals(response.getStatusCode(), "201");
        Assert.assertTrue(response.getStatusMessage().startsWith("Success"));
    }

    @Test(groups = "integrationTest", dependsOnMethods = "testChargeBcaKlikpay")
    public void testCancelBcaKlikpay() throws RestClientException, UnsupportedEncodingException {
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
