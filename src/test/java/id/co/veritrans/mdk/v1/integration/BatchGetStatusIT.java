package id.co.veritrans.mdk.v1.integration;

import id.co.veritrans.mdk.v1.TestUtil;
import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.model.CustomerDetails;
import id.co.veritrans.mdk.v1.gateway.model.StatusRequest;
import id.co.veritrans.mdk.v1.gateway.model.TransactionDetails;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;
import id.co.veritrans.mdk.v1.gateway.model.builder.StatusRequestBuilder;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.BankTransferRequest;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.KlikBcaRequest;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.BankTransfer;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.KlikBca;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by andes on 6/12/15.
 */
public class BatchGetStatusIT extends AbstractIntegrationTest {

    @Test(groups = "integrationTest")
    public void testGetStatusBatch() throws RestClientException, UnsupportedEncodingException {
        String orderId = String.valueOf(System.nanoTime());
        ArrayList<String> listOrderIds = new ArrayList<String>();

        /**
         * Klik Bca Request
         */
        final KlikBcaRequest klikBcaRequest = new KlikBcaRequest();
        klikBcaRequest.setTransactionDetails(new TransactionDetails(orderId, 10000L));
        klikBcaRequest.setCustomerDetails(TestUtil.buildCustomerDetails());
        klikBcaRequest.setItemDetails(TestUtil.buildTransactionItems());
        klikBcaRequest.setKlikBca(new KlikBca("userid", "Test transaction"));

        vtDirect.charge(klikBcaRequest);
        listOrderIds.add(orderId);

        /**
         * Bank Transfer request
         */
        orderId = String.valueOf(System.nanoTime());
        final BankTransferRequest bankTransferRequest = new BankTransferRequest();
        bankTransferRequest.setTransactionDetails(new TransactionDetails(orderId, 10000L));
        bankTransferRequest.setCustomerDetails(new CustomerDetails("gde", "satrigraha", "gde.satrigraha@veritrans.co.id", "123456789", null, null));
        bankTransferRequest.setBankTransfer(new BankTransfer(BankTransfer.Bank.PERMATA));

        vtDirect.charge(bankTransferRequest);
        listOrderIds.add(orderId);

        /**
         * Batch Get Status request
         */
        final StatusRequest statusRequest = new StatusRequestBuilder()
                .setOrderIds(listOrderIds.toArray(new String[listOrderIds.size()]))
                .setPage(1)
                .setRowPerPage(10)
                .createStatusRequest();

        VtResponse response = vtDirect.status(statusRequest);
        Assert.assertEquals(response.getPage(), Integer.valueOf(1));
        Assert.assertEquals(response.getTotalPage(), Integer.valueOf(1));
        Assert.assertEquals(response.getTotalRecord(), Integer.valueOf(2));
        Assert.assertEquals(response.getStatusCode(), "200");
        Assert.assertEquals(response.getListTransactionStatus().length, 2);
        Assert.assertNotNull(response.getListTransactionStatus()[0].getApprovalCode());
        Assert.assertNotNull(response.getListTransactionStatus()[0].getGrossAmount());
        Assert.assertNotNull(response.getListTransactionStatus()[0].getOrderId());
        Assert.assertNotNull(response.getListTransactionStatus()[0].getPaymentMethod());
        Assert.assertNotNull(response.getListTransactionStatus()[0].getStatusCode());
        Assert.assertNotNull(response.getListTransactionStatus()[0].getTransactionId());
        Assert.assertNotNull(response.getListTransactionStatus()[0].getTransactionStatus());
        Assert.assertNotNull(response.getListTransactionStatus()[0].getTransactionTime());

    }
}
