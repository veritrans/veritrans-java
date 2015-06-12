package id.co.veritrans.mdk.v1.integration.vtweb;

import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.model.CustomerDetails;
import id.co.veritrans.mdk.v1.gateway.model.TransactionDetails;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;
import id.co.veritrans.mdk.v1.gateway.model.vtweb.VtWebChargeRequest;
import id.co.veritrans.mdk.v1.gateway.model.vtweb.VtWebParam;
import id.co.veritrans.mdk.v1.integration.AbstractIntegrationTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Created by gde on 5/20/15.
 */
public class TestChargeIT extends AbstractIntegrationTest {

    final String orderId = String.valueOf(System.nanoTime());

    @Test
    public void testCharge() throws RestClientException {
        final VtWebChargeRequest request = new VtWebChargeRequest();
        request.setTransactionDetails(new TransactionDetails(orderId, 10000L));
        request.setCustomerDetails(new CustomerDetails("gde", "satrigraha", "gde.satrigraha@veritrans.co.id", "123456789", null, null));
        request.setVtWeb(new VtWebParam());
        request.getVtWeb().setCreditCardBins(new String[]{"411111"});

        final VtResponse vtResponse = vtWeb.charge(request);
        assertEquals(vtResponse.getStatusCode(), "201");
        assertNotNull(vtResponse.getRedirectUrl());
    }
}
