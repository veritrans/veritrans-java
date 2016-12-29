package id.co.veritrans.mdk.v1.gateway.model.vtweb;

import org.testng.annotations.Test;

import id.co.veritrans.mdk.v1.gateway.model.PaymentMethod;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.BankTransfer;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.CreditCard;
import id.co.veritrans.mdk.v1.helper.JsonUtil;

import static org.testng.Assert.assertTrue;

/**
 * Testing for VtWebParam class
 */
public class VtWebParamTest {
    @Test
    public void testVtWebParameter() {
        VtWebParam param = new VtWebParam();

        param.setCreditCardBins(new String[] {"1234", "5678"});
        param.setCreditCardUse3dSecure(true);
        param.setEnabledPayments(new PaymentMethod[] {
                PaymentMethod.BANK_TRANSFER,
                PaymentMethod.CREDIT_CARD
        });

        param.setErrorRedirectUrl("http://example.com/redirect/error");
        param.setFinishRedirectUrl("http://example.com/redirect/finish");
        param.setUnfinishRedirectUrl("http://example.com/redirect/unfinish");

        param.setCreditCardOptions(CreditCardOptions.buildOptions()
            .setBank(CreditCard.Bank.BCA)
            .setChannel(CreditCard.Channel.MIGS));

        String jsonBody = JsonUtil.toJson(param);
        assertTrue(jsonBody.contains("\"enabled_payments\":[\"bank_transfer\",\"credit_card\"]"));
        assertTrue(jsonBody.contains("\"credit_card_bins\":[\"1234\",\"5678\"]"));
        assertTrue(jsonBody.contains("\"finish_redirect_url\":\"http://example.com/redirect/finish\""));
        assertTrue(jsonBody.contains("\"unfinish_redirect_url\":\"http://example.com/redirect/unfinish\""));
        assertTrue(jsonBody.contains("\"error_redirect_url\":\"http://example.com/redirect/error\""));
        assertTrue(jsonBody.contains("\"credit_card_3d_secure\":true"));
        assertTrue(jsonBody.contains("\"credit_card\":{\"channel\":\"migs\",\"bank\":\"bca\"}"));
    }

    @Test
    public void testVtWebOnlyChannelParameter() {
        VtWebParam param = new VtWebParam();
        param.setCreditCardOptions(CreditCardOptions.buildOptions()
            .setChannel(CreditCard.Channel.MIGS)
        );

        String jsonBody = JsonUtil.toJson(param);
        assertTrue(jsonBody.contains("\"credit_card\":{\"channel\":\"migs\"}}"));
    }
}
