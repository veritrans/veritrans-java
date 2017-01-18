package id.co.veritrans.mdk.v1.integration.vtdirect;

import id.co.veritrans.mdk.v1.gateway.model.CustomExpiry;
import id.co.veritrans.mdk.v1.gateway.model.TransactionDetails;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.BankTransferRequest;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.BankTransfer;
import org.testng.annotations.Test;

import id.co.veritrans.mdk.v1.helper.JsonUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.testng.Assert.assertTrue;

/**
 * Testing for BankTransferRequest parameters
 */
public class BankTransferParamTest {

    @Test
    public void testBankTransfer1() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss Z");;
        Date date = sdf.parse("2016-12-07 11:54:12 +0700");

        BankTransferRequest bankTransferRequest = new BankTransferRequest();
        bankTransferRequest.setTransactionDetails(new TransactionDetails("C17550", 145000L));
        bankTransferRequest.setBankTransfer(new BankTransfer(BankTransfer.Bank.PERMATA));
        bankTransferRequest.setCustomExpiry(new CustomExpiry(date, 60, CustomExpiry.Unit.MINUTE));


        String jsonBody = JsonUtil.toJson(bankTransferRequest);
        System.out.println(jsonBody);
        assertTrue(jsonBody.contains("\"payment_type\":\"bank_transfer\""));
        assertTrue(jsonBody.contains("\"bank_transfer\":{\"bank\":\"PERMATA\"}"));
        assertTrue(jsonBody.contains("\"transaction_details\":{\"order_id\":\"C17550\",\"gross_amount\":145000}"));
        assertTrue(jsonBody.contains("\"custom_expiry\":{\"order_time\":\"2016-12-07 11:54:12 +0700\",\"expiry_duration\":60,\"unit\":\"MINUTE\"}"));

    }

    @Test
    public void testBankTransfer2() throws ParseException {
        BankTransferRequest bankTransferRequest = new BankTransferRequest();
        bankTransferRequest.setTransactionDetails(new TransactionDetails("C17550", 145000L));
        bankTransferRequest.setBankTransfer(new BankTransfer(BankTransfer.Bank.PERMATA));
        bankTransferRequest.setCustomExpiry(new CustomExpiry(60));


        String jsonBody = JsonUtil.toJson(bankTransferRequest);
        System.out.println(jsonBody);
        assertTrue(jsonBody.contains("\"payment_type\":\"bank_transfer\""));
        assertTrue(jsonBody.contains("\"bank_transfer\":{\"bank\":\"PERMATA\"}"));
        assertTrue(jsonBody.contains("\"transaction_details\":{\"order_id\":\"C17550\",\"gross_amount\":145000}"));
        assertTrue(jsonBody.contains("\"custom_expiry\":{\"expiry_duration\":60}"));

    }

}
