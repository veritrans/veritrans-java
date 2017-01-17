package id.co.veritrans.mdk.v1.integration.vtdirect;

import id.co.veritrans.mdk.v1.gateway.model.CustomExpiry;
import id.co.veritrans.mdk.v1.gateway.model.TransactionDetails;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.BankTransferRequest;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.BankTransfer;
import org.testng.annotations.Test;

import id.co.veritrans.mdk.v1.helper.JsonUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.testng.Assert.assertTrue;

/**
 * Testing for BankTransferRequest parameters
 */
public class BankTransferParamTest {

    @Test
    public void testBankTransfer() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss Z");
        Date date = new Date();
        try {
            date = sdf.parse("2016-12-07 11:54:12 +0700");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        BankTransferRequest param = new BankTransferRequest();
        param.setTransactionDetails(new TransactionDetails("C17550", 145000L));
        param.setBankTransfer(new BankTransfer(BankTransfer.Bank.PERMATA));
        param.setCustomExpiry(new CustomExpiry(date, 60, CustomExpiry.Unit.MINUTE));


        String jsonBody = JsonUtil.toJson(param);
        System.out.println(jsonBody);
        assertTrue(jsonBody.contains("\"payment_type\":\"bank_transfer\""));
        assertTrue(jsonBody.contains("\"bank_transfer\":{\"bank\":\"PERMATA\"}"));
        assertTrue(jsonBody.contains("\"transaction_details\":{\"order_id\":\"C17550\",\"gross_amount\":145000}"));
        assertTrue(jsonBody.contains("\"custom_expiry\":{\"order_time\":\"2016-12-07 11:54:12 +0700\",\"expiry_duration\":60,\"unit\":\"MINUTE\"}"));

    }

}
