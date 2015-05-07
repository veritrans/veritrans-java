package id.co.veritrans.mdk.gateway.model.vtdirect.paymentmethod;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by andes on 5/7/15.
 */
public class CreditCardTest {

    private Set<ConstraintViolation<CreditCard>> constraintViolations;
    private Validator validator;

    public static CreditCard buildCreditCard() {
        CreditCard creditCard = new CreditCard();
        creditCard.setTokenId("abcdef");
        creditCard.setAcquirerBank(CreditCard.Bank.BNI);

        return creditCard;
    }

    @BeforeClass
    public void prepare() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void testCreditCard() {
        List<String> bins = new LinkedList<String>();
        bins.add("1234");
        bins.add("5678");

        CreditCard creditCard = buildCreditCard();
        creditCard.setBins(bins);
        creditCard.setInstallmentTerm(6);
        creditCard.setSaveTokenId(true);
        creditCard.setTransactionType(CreditCard.TransactionType.AUTHORIZE);

        assertEquals(creditCard.getTokenId(), "abcdef");
        assertEquals(creditCard.getAcquirerBank(), CreditCard.Bank.BNI);
        assertEquals(creditCard.getBins(), bins);
        assertEquals(creditCard.getInstallmentTerm(), Integer.valueOf(6));
        assertEquals(creditCard.getTransactionType(), CreditCard.TransactionType.AUTHORIZE);
        assertTrue(creditCard.getSaveTokenId());

        constraintViolations = validator.validate(creditCard);
        assertTrue(constraintViolations.isEmpty());
    }
}
