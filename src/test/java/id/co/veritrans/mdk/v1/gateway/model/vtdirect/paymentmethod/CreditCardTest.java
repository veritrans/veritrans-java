package id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod;

import id.co.veritrans.mdk.v1.gateway.model.builder.CreditCardBuilder;
import id.co.veritrans.mdk.v1.helper.JsonUtil;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by andes on 5/7/15.
 */
public class CreditCardTest {

    @Test
    public void testCreditCardNormal() {
        List<String> bins = new LinkedList<String>();
        bins.add("1234");
        bins.add("5678");

        CreditCard creditCard = new CreditCardBuilder()
                .setCardToken("abcdef")
                .setAcquirerBank(CreditCard.Bank.BNI)
                .setBins(bins)
                .setInstallmentTerm(6)
                .setSaveCardToken(true)
                .setTransactionType(CreditCard.TransactionType.AUTHORIZE)
                .setFraudSector("Travel")
                .createCreditCard();

        assertEquals(creditCard.getCardToken(), "abcdef");
        assertEquals(creditCard.getAcquirerBank(), CreditCard.Bank.BNI);
        assertEquals(creditCard.getBins(), bins);
        assertEquals(creditCard.getInstallmentTerm(), Integer.valueOf(6));
        assertEquals(creditCard.getTransactionType(), CreditCard.TransactionType.AUTHORIZE);
        assertEquals(creditCard.getFraudSector(), "Travel");
        assertTrue(creditCard.getSaveCardToken());

        String jsonCreditCard = JsonUtil.toJson(creditCard);
        assertTrue(jsonCreditCard.contains("\"installment_term\":6"));
        assertTrue(jsonCreditCard.contains("\"bins\":[\"1234\",\"5678\"]"));
        assertTrue(jsonCreditCard.contains("\"fraud_sector\":\"Travel\""));
        assertTrue(jsonCreditCard.contains("\"token_id\":\"abcdef\""));
        assertTrue(jsonCreditCard.contains("\"bank\":\"bni\""));
        assertTrue(jsonCreditCard.contains("\"type\":\"authorize\""));
        assertTrue(jsonCreditCard.contains("\"save_token_id\":true"));
    }

}
