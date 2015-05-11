package id.co.veritrans.mdk.v1.integration.vtdirect;

import id.co.veritrans.mdk.v1.helper.JsonUtil;
import id.co.veritrans.mdk.v1.integration.AbstractIntegrationTest;
import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.impl.DefaultVtDirect;
import id.co.veritrans.mdk.v1.gateway.model.CustomerDetails;
import id.co.veritrans.mdk.v1.gateway.model.TransactionDetails;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.CreditCardRequest;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.CreditCard;
import id.co.veritrans.mdk.v1.helper.StringConstant;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Created by gde on 5/11/15.
 */
public class AbstractCreditCardIT extends AbstractIntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCreditCardIT.class);

    public String getToken(String cardNumber, String expiryMonth, String expiryYear, String cvv) throws URISyntaxException, RestClientException {
        final URIBuilder tokenUriBuilder = new URIBuilder(vtGatewayFactory.getEnvironmentType().getBaseUrl() + "/" + StringConstant.TOKEN)
                .addParameter("card_number", cardNumber)
                .addParameter("card_cvv", cvv)
                .addParameter("card_exp_month", expiryMonth)
                .addParameter("card_exp_year", expiryYear)
                .addParameter("client_key", vtGatewayFactory.getClientKey());

        final VtResponse vtResponse = ((DefaultVtDirect) vtDirect).getVtGatewaySession().getRestClient()
                .get(tokenUriBuilder.build().toString());

        assertEquals(vtResponse.getStatusCode(), "200");
        assertNotNull(vtResponse.getCardToken());
        return vtResponse.getCardToken();
    }

    public VtResponse charge(String orderId, CreditCard creditCard) throws RestClientException {
        final CreditCardRequest req = new CreditCardRequest();
        req.setTransactionDetails(new TransactionDetails(orderId, 10000l));
        req.setCustomerDetails(new CustomerDetails("gde", "satrigraha", "gde.satrigraha@veritrans.co.id", "123456789", null, null));
        req.setCreditCard(creditCard);

        LOGGER.info("charging: " + JsonUtil.toJson(req));
        final VtResponse vtResponse = vtDirect.charge(req);
        LOGGER.info("charge response: " + JsonUtil.toJson(vtResponse));
        return vtResponse;
    }
}
