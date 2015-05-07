package id.co.veritrans.mdk.util;

import id.co.veritrans.mdk.VtGatewayConfig;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.testng.Assert.assertTrue;

/**
 * Created by andes on 5/7/15.
 */
public class ExceptionUtilTest {

    @Test
    public void testBuildExceptionMessage() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        VtGatewayConfig vtGatewayConfig = new VtGatewayConfig();
        vtGatewayConfig.setClientKey("a");

        Set<ConstraintViolation<VtGatewayConfig>> constraintViolations = validator.validate(vtGatewayConfig);
        String errorMessage = ExceptionUtil.buildExceptionMessage(constraintViolations.toArray());

        assertTrue(errorMessage.contains("environmentType may not be null"));
        assertTrue(errorMessage.contains("serverKey may not be null"));
    }
}
