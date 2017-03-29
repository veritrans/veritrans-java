package id.co.veritrans.mdk.v1.integration;

import id.co.veritrans.mdk.v1.TestUtil;
import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.model.*;
import id.co.veritrans.mdk.v1.gateway.model.builder.GetStatusParameterBuilder;
import id.co.veritrans.mdk.v1.gateway.model.builder.StatusRequestBuilder;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.BankTransferRequest;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.KlikBcaRequest;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.BankTransfer;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.KlikBca;
import id.co.veritrans.mdk.v1.helper.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by andes on 6/12/15.
 */
public class GetStatusIT extends AbstractIntegrationTest {

    private ArrayList<String> createTransactions() throws RestClientException {
        String orderId = String.valueOf(System.nanoTime());
        ArrayList<String> listOrderIds = new ArrayList<String>();

        /**
         * Klik Bca SnapChargeRequest
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

        return listOrderIds;
    }

    @Test(groups = "integrationTest")
    public void testGetStatusBatch() throws RestClientException, UnsupportedEncodingException {
        /**
         * Batch Get Status request
         */
        final StatusRequest statusRequest = new StatusRequestBuilder()
                .setOrderIds(createTransactions())
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

    @Test(groups = "integrationTest")
    public void testQueryGetStatus() throws RestClientException, URISyntaxException {
        /*
         * Query Get Status
         */
        final GetStatusParameter getStatusParameter = new GetStatusParameterBuilder()
                .setPaymentMethod(PaymentMethod.CREDIT_CARD)
                .setFromDate(new GregorianCalendar(2015, 04, 27).getTime())
                .setToDate(new GregorianCalendar(2015, 04, 28).getTime())
                .createGetStatusParameter();

        VtResponse response = vtDirect.queryStatus(getStatusParameter);
        System.out.println(JsonUtil.toJson(response));

        Assert.assertEquals(1, response.getListTransactionStatus().length);
        Assert.assertEquals(FraudStatus.ACCEPTED, response.getListTransactionStatus()[0].getFraudStatus());
        Assert.assertEquals("481111-1114", response.getListTransactionStatus()[0].getMaskedCardNumber());
        Assert.assertEquals("e8f66a0e-8e77-493a-953b-9ded28f5a6ae", response.getListTransactionStatus()[0].getOrderId());
        Assert.assertEquals(PaymentMethod.CREDIT_CARD.getName(), response.getListTransactionStatus()[0].getPaymentMethod());
        Assert.assertEquals("200", response.getListTransactionStatus()[0].getStatusCode());
        Assert.assertEquals("01906a7c-1957-43b3-be84-202dc5aa348d", response.getListTransactionStatus()[0].getTransactionId());
        Assert.assertEquals(TransactionStatus.SETTLED, response.getListTransactionStatus()[0].getTransactionStatus());
        Assert.assertNotNull(response.getListTransactionStatus()[0].getGrossAmount());
        Assert.assertNotNull(response.getListTransactionStatus()[0].getTransactionTime());
    }
}
