package id.co.veritrans.mdk;

import id.co.veritrans.mdk.config.EnvironmentType;
import id.co.veritrans.mdk.exception.InvalidVtConfigException;
import id.co.veritrans.mdk.gateway.VtDirect;
import id.co.veritrans.mdk.gateway.model.vtdirect.CreditCardRequest;
import id.co.veritrans.mdk.gateway.model.vtdirect.paymentmethod.CreditCard;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VtDirectTest {

    private VtGatewayFactory factory;

    @BeforeClass
    public void prepare() {
        factory = new VtGatewayFactory();
        factory.setServerKey("a");
        factory.setClientKey("b");
        factory.setEnvironmentType(EnvironmentType.SANDBOX);
    }

    @Test
    public void testChargeCreditCardNormal() throws InvalidVtConfigException {
        CreditCard card = new CreditCard();
        card.setTokenId("token credit card");

        CreditCardRequest creditCardRequest = new CreditCardRequest();

        VtDirect vtDirect = factory.vtDirect();
    }
}
