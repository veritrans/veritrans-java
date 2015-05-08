package id.co.veritrans.mdk.v1.helper;

import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.CreditCard;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by andes on 5/8/15.
 */
public class JsonUtilTest {

    @Test
    public void testJsonSerializer() throws Exception {
        CreditCard creditCard = new CreditCard();
        creditCard.setTokenId("1234567890");
        creditCard.setAcquirerBank(CreditCard.Bank.BNI);
        creditCard.setInstallmentTerm(6);
        creditCard.setSaveTokenId(true);

        String json = JsonUtil.toJson(creditCard);
        Assert.assertTrue(json.contains("token_id"));
        Assert.assertTrue(json.contains("acquirer_bank"));
        Assert.assertTrue(json.contains("installment_term"));
        Assert.assertTrue(json.contains("save_token_id"));
        Assert.assertFalse(json.contains("transaction_type"));
    }
}
