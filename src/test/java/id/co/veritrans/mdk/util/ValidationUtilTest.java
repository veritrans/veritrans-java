package id.co.veritrans.mdk.util;

import id.co.veritrans.mdk.VtGatewayConfig;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.testng.Assert.assertTrue;

/**
 * Created by andes on 5/7/15.
 */
public class ValidationUtilTest {

    @Test
    public void testBuildExceptionMessage() {
        VtGatewayConfig vtGatewayConfig = new VtGatewayConfig();
        vtGatewayConfig.setClientKey("a");

        Set<ConstraintViolation<VtGatewayConfig>> constraintViolations = ValidationUtil.getValidator().validate(vtGatewayConfig);
        String errorMessage = ValidationUtil.buildExceptionMessage(constraintViolations.toArray());

        assertTrue(errorMessage.contains("environmentType may not be null"));
        assertTrue(errorMessage.contains("serverKey may not be null"));
    }
}
