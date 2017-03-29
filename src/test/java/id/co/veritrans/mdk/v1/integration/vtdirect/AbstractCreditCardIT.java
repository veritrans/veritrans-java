package id.co.veritrans.mdk.v1.integration.vtdirect;

import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.impl.DefaultVtDirect;
import id.co.veritrans.mdk.v1.gateway.model.CustomerDetails;
import id.co.veritrans.mdk.v1.gateway.model.TransactionDetails;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.CreditCardFullPanRequest;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.CreditCardRequest;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.CreditCard;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.CreditCardFullPan;
import id.co.veritrans.mdk.v1.helper.JsonUtil;
import id.co.veritrans.mdk.v1.helper.StringConstant;
import id.co.veritrans.mdk.v1.integration.AbstractIntegrationTest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Created by gde on 5/11/15.
 */
public class AbstractCreditCardIT extends AbstractIntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCreditCardIT.class);
    private static final Pattern PATTERN_PAREQ = Pattern.compile("name=\"PaReq\" value=\"(.*)\"");
    private static final Pattern PATTERN_TERMURL = Pattern.compile("name=\"TermUrl\" value=\"(.*)\"");
    private static final Pattern PATTERN_MD = Pattern.compile("name=\"MD\" value=\"(.*)\"");
    private static final Pattern PATTERN_STATUS = Pattern.compile("name=\"Status\" value=\"(.*)\"");
    private static final Pattern PATTERN_FORM_VERITRANS_ACTION = Pattern.compile("id=\"veritrans\".*action=\"(.*)\"");
    private static final Pattern PATTERN_FORM_ACS_ACTION = Pattern.compile("id='acsForm'.*action=\"(.*)\" method");

    public String getToken(String cardNumber, String expiryMonth, String expiryYear, String cvv) throws URISyntaxException, RestClientException {
        return getToken(cardNumber, expiryMonth, expiryYear, cvv, null);
    }

    public String getToken(String cardNumber, String expiryMonth, String expiryYear, String cvv, CreditCard.TransactionType transactionType) throws URISyntaxException, RestClientException {
        URIBuilder tokenUriBuilder = new URIBuilder(StringConstant.TOKEN)
                .addParameter("card_number", cardNumber)
                .addParameter("card_cvv", cvv)
                .addParameter("card_exp_month", expiryMonth)
                .addParameter("card_exp_year", expiryYear)
                .addParameter("client_key", vtGatewayFactory.getClientKey());

        if (transactionType != null) {
            tokenUriBuilder = tokenUriBuilder.addParameter("type", transactionType.getName());
        }

        final VtResponse vtResponse = ((DefaultVtDirect) vtDirect).getVtGatewaySession().getRestClient()
                .get(VtResponse.class, tokenUriBuilder.build().toString());

        assertEquals(vtResponse.getStatusCode(), "200");
        assertNotNull(vtResponse.getCardToken());
        return vtResponse.getCardToken();
    }

    public String getToken3Ds(String cardNumber, String expiryMonth, String expiryYear, String cvv, CreditCard.Bank bank) throws URISyntaxException, RestClientException, IOException {
        return getToken3Ds(cardNumber, expiryMonth, expiryYear, cvv, bank, null);
    }

    public String getToken3Ds(String cardNumber, String expiryMonth, String expiryYear, String cvv, CreditCard.Bank bank, CreditCard.TransactionType transactionType) throws URISyntaxException, RestClientException, IOException {
        URIBuilder tokenUriBuilder = new URIBuilder(StringConstant.TOKEN)
                .addParameter("card_number", cardNumber)
                .addParameter("card_cvv", cvv)
                .addParameter("card_exp_month", expiryMonth)
                .addParameter("card_exp_year", expiryYear)
                .addParameter("client_key", vtGatewayFactory.getClientKey())
                .addParameter("secure", "true")
                .addParameter("gross_amount", String.valueOf(10000))
                .addParameter("bank", bank.toString());

        if (transactionType != null) {
            tokenUriBuilder = tokenUriBuilder.addParameter("type", transactionType.getName());
        }

        final VtResponse vtResponse = ((DefaultVtDirect) vtDirect).getVtGatewaySession().getRestClient()
                .get(VtResponse.class, tokenUriBuilder.build().toString());

        assertEquals(vtResponse.getStatusCode(), "200");
        assertNotNull(vtResponse.getCardToken());
        assertNotNull(vtResponse.getRedirectUrl());

        do3Ds(vtResponse.getRedirectUrl());
        return vtResponse.getCardToken();
    }

    public void do3Ds(String redirectUrl) throws IOException {
        LOGGER.info("do3Ds opening redirectUrl: {}", redirectUrl);
        final String redirectUrlContent = consumeGetAsString(redirectUrl);
        final Matcher pareqMatcher = PATTERN_PAREQ.matcher(redirectUrlContent);
        final Matcher termurlMatcher = PATTERN_TERMURL.matcher(redirectUrlContent);
        final Matcher mdMatcher = PATTERN_MD.matcher(redirectUrlContent);
        final Matcher statusMatcher = PATTERN_STATUS.matcher(redirectUrlContent);
        final Matcher vtFormPostUrlMatcher = PATTERN_FORM_VERITRANS_ACTION.matcher(redirectUrlContent);

        pareqMatcher.find();
        termurlMatcher.find();
        mdMatcher.find();
        statusMatcher.find();
        vtFormPostUrlMatcher.find();

        final String pareq = pareqMatcher.group(1);
        LOGGER.info("do3Ds got PaReq: {}", pareq);

        final String termurl = termurlMatcher.group(1);
        LOGGER.info("do3Ds got TermUrl: {}", termurl);

        final String md = mdMatcher.group(1);
        LOGGER.info("do3Ds got MD: {}", md);

        final String status = statusMatcher.group(1);
        LOGGER.info("do3Ds got Status: {}", status);

        final String vtFormPostUrl = vtFormPostUrlMatcher.group(1);
        LOGGER.info("do3Ds got vtFormPostUrl: {}", vtFormPostUrl);

        final String d3sPage = consumePostAsString(vtFormPostUrl, Arrays.<NameValuePair>asList(
                new BasicNameValuePair("PaReq", pareq),
                new BasicNameValuePair("TermUrl", termurl),
                new BasicNameValuePair("MD", md),
                new BasicNameValuePair("Status", status)
        ));

        final Matcher acsFormPostUrlMatcher = PATTERN_FORM_ACS_ACTION.matcher(d3sPage);
        acsFormPostUrlMatcher.find();

        final String acsFormPostUrl = acsFormPostUrlMatcher.group(1);
        LOGGER.info("do3Ds got acsFormPostUrl: {}", acsFormPostUrl);

        final String d3sDonePage = consumePostAsString(acsFormPostUrl, Arrays.<NameValuePair>asList(
                new BasicNameValuePair("MD", md),
                new BasicNameValuePair("Status", status),
                new BasicNameValuePair("PaRes", "112233")
        ));
    }

    public String consumeGetAsString(String url) throws IOException {
        final HttpGet httpGet = new HttpGet(url);
        final HttpClient hc = HttpClients.createDefault();
        final HttpResponse resp = hc.execute(httpGet);

        return EntityUtils.toString(resp.getEntity());
    }

    public String consumePostAsString(String url, Iterable<NameValuePair> formParams) throws IOException {
        final HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new UrlEncodedFormEntity(formParams));

        final HttpClient hc = HttpClients.createDefault();
        final HttpResponse resp = hc.execute(httpPost);
        return EntityUtils.toString(resp.getEntity());
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

    public VtResponse charge(String orderId, CreditCardFullPan creditCardFullPan) throws RestClientException {
        final CreditCardFullPanRequest req = new CreditCardFullPanRequest();
        req.setTransactionDetails(new TransactionDetails(orderId, 10000l));
        req.setCustomerDetails(new CustomerDetails("gde", "satrigraha", "gde.satrigraha@veritrans.co.id", "123456789", null, null));
        req.setCreditCardFullPan(creditCardFullPan);

        LOGGER.info("charging: " + JsonUtil.toJson(req));
        final VtResponse vtResponse = vtDirect.charge(req);
        LOGGER.info("charge response: " + JsonUtil.toJson(vtResponse));
        return vtResponse;
    }
}
