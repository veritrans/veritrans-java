package id.co.veritrans.mdk.v1;

import id.co.veritrans.mdk.v1.config.EnvironmentType;
import id.co.veritrans.mdk.v1.gateway.VtDirect;
import id.co.veritrans.mdk.v1.gateway.model.builder.CreditCardBuilder;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.CreditCardRequest;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.CreditCard;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VtDirectTest {

    private VtGatewayFactory factory;

    @BeforeClass
    public void prepare() {
        factory = new VtGatewayFactory(new VtGatewayConfigBuilder()
                .setServerKey("a")
                .setClientKey("b")
                .setEnvironmentType(EnvironmentType.SANDBOX)
                .createVtGatewayConfig());
    }

    @Test
    public void testChargeCreditCardNormal() {
        CreditCard card = new CreditCardBuilder().createCreditCard();
        card.setCardToken("token credit card");

        CreditCardRequest creditCardRequest = new CreditCardRequest();

        VtDirect vtDirect = factory.vtDirect();
    }
}
