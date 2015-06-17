package id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod;

import id.co.veritrans.mdk.v1.gateway.model.FraudStatus;
import id.co.veritrans.mdk.v1.gateway.model.GetStatusParameter;
import id.co.veritrans.mdk.v1.gateway.model.PaymentMethod;
import id.co.veritrans.mdk.v1.gateway.model.TransactionStatus;
import id.co.veritrans.mdk.v1.gateway.model.builder.GetStatusParameterBuilder;
import id.co.veritrans.mdk.v1.helper.StringConstant;
import org.apache.http.client.utils.URIBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.GregorianCalendar;

/**
 * Created by andes on 6/17/15.
 */
public class GetStatusParameterTest {

    @Test
    public void testGetStatusParameterBuilder() throws URISyntaxException {
        GetStatusParameter parameter = new GetStatusParameterBuilder()
                .setFraudStatus(FraudStatus.CHALLENGE)
                .setTransactionStatus(TransactionStatus.AUTHORIZED)
                .setFromDate(new GregorianCalendar(2015, 0, 1).getTime())
                .setToDate(new GregorianCalendar(2015, 4, 5).getTime())
                .setGrossAmount(10000L)
                .setOrderId("12345")
                .setTransactionId("67890")
                .setPaymentMethod(PaymentMethod.BANK_TRANSFER)
                .setPage(2)
                .setRowPerPage(5)
                .createGetStatusParameter();

        URI uriParameter = new URIBuilder(StringConstant.GET_STATUS)
                .addParameters(parameter.toUrlParameter())
                .build();

        String stringParams = uriParameter.toString();
        Assert.assertTrue(stringParams.contains("order_id=12345"));
        Assert.assertTrue(stringParams.contains("transaction_id=67890"));
        Assert.assertTrue(stringParams.contains("gross_amount=10000"));
        Assert.assertTrue(stringParams.contains("transaction_status=authorize"));
        Assert.assertTrue(stringParams.contains("payment_type=bank_transfer"));
        Assert.assertTrue(stringParams.contains("from_date=20150101000000"));
        Assert.assertTrue(stringParams.contains("to_date=20150505000000"));
        Assert.assertTrue(stringParams.contains("fraud_status=challenge"));
        Assert.assertTrue(stringParams.contains("page=2"));
        Assert.assertTrue(stringParams.contains("row_per_page=5"));
    }
}
