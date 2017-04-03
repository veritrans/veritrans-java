package id.co.veritrans.mdk.v1.integration.snap;

import id.co.veritrans.mdk.v1.TestUtil;
import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.impl.DefaultSnap;
import id.co.veritrans.mdk.v1.gateway.impl.DefaultVtDirect;
import id.co.veritrans.mdk.v1.gateway.model.*;
import id.co.veritrans.mdk.v1.gateway.model.snap.SnapChargeRequest;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.MandiriClickpayRequest;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.CreditCard;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.MandiriClickpay;
import id.co.veritrans.mdk.v1.helper.JsonUtil;
import id.co.veritrans.mdk.v1.helper.StringConstant;
import id.co.veritrans.mdk.v1.integration.AbstractIntegrationTest;
import org.apache.http.client.utils.URIBuilder;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Created by adampahlevi on 3/29/17.
 */
public class SnapGatewayTest extends AbstractIntegrationTest {
    final String orderId = String.valueOf(System.nanoTime());

    @Test(groups = "integrationTest")
    public void testSnapGetToken() throws RestClientException, UnsupportedEncodingException {
        SnapChargeRequest request = new SnapChargeRequest();
        request.setTransactionDetails(new TransactionDetails(orderId, 10000L));
        request.setCustomerDetails(TestUtil.buildCustomerDetails());

        final SnapResponse snapResponse = snap.getToken(request);

        assertNotNull(snapResponse.getToken());
        assertNotEquals(snapResponse.getToken(), "");
    }
}
