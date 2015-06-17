package id.co.veritrans.mdk.v1.helper;

import id.co.veritrans.mdk.v1.gateway.model.TransactionStatus;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;
import id.co.veritrans.mdk.v1.gateway.model.builder.CreditCardBuilder;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.CreditCard;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Created by andes on 5/8/15.
 */
public class JsonUtilTest {

    private static final String JSON_RESPONSE = "{\"transaction_id\":\"af319f6e-b1fc-4fef-8a11-c60fa59fd483\",\"order_id\":\"1431316976886113000\",\"gross_amount\":10000.00,\"transaction_time\":\"2015-05-11 11:02:58\",\"transaction_status\":\"settlement\",\"status_code\":\"200\",\"status_message\":\"Success, Mandiri Clickpay transaction is successful\",\"approval_code\":\"1431316980859\",\"payment_type\":\"mandiri_clickpay\",\"masked_card\":\"411111-1111\"}";

    @Test
    public void testJsonSerializer() throws Exception {
        CreditCard creditCard = new CreditCardBuilder()
                .setCardToken("1234567890")
                .setAcquirerBank(CreditCard.Bank.BNI)
                .setInstallmentTerm(6)
                .setSaveCardToken(true)
                .createCreditCard();

        String json = JsonUtil.toJson(creditCard);
        Assert.assertTrue(json.contains("token_id"));
        Assert.assertTrue(json.contains("bank"));
        Assert.assertTrue(json.contains("installment_term"));
        Assert.assertTrue(json.contains("save_token_id"));
        Assert.assertFalse(json.contains("type"));
    }

    @Test
    public void testJsonDeserializer() throws Exception {
        HttpEntity entity = mock(HttpEntity.class);
        HttpResponse httpResponse = mock(HttpResponse.class);

        when(entity.getContent()).thenReturn(new ByteArrayInputStream(JSON_RESPONSE.getBytes()));
        when(httpResponse.getEntity()).thenReturn(entity);

        VtResponse response = JsonUtil.fromJson(httpResponse, VtResponse.class);
        Assert.assertEquals(response.getApprovalCode(), "1431316980859");
        Assert.assertEquals(response.getGrossAmount().toString(), "10000.00");
        Assert.assertEquals(response.getMaskedCardNumber(), "411111-1111");
        Assert.assertEquals(response.getOrderId(), "1431316976886113000");
        Assert.assertEquals(response.getPaymentMethod(), "mandiri_clickpay");
        Assert.assertEquals(response.getStatusCode(), "200");
        Assert.assertEquals(response.getStatusMessage(), "Success, Mandiri Clickpay transaction is successful");
        Assert.assertEquals(response.getTransactionId(), "af319f6e-b1fc-4fef-8a11-c60fa59fd483");
        Assert.assertEquals(response.getTransactionStatus(), TransactionStatus.SETTLED);
        Assert.assertNotNull(response.getTransactionTime());
    }
}
