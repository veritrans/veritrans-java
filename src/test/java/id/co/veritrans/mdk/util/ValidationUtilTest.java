package id.co.veritrans.mdk.util;

import id.co.veritrans.mdk.v1.VtGatewayConfig;
import id.co.veritrans.mdk.v1.VtGatewayConfigBuilder;
import id.co.veritrans.mdk.v1.helper.ValidationUtil;
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
        VtGatewayConfig vtGatewayConfig = new VtGatewayConfigBuilder().setClientKey("a").createVtGatewayConfig();

        Set<ConstraintViolation<VtGatewayConfig>> constraintViolations = ValidationUtil.getValidator().validate(vtGatewayConfig);
        String errorMessage = ValidationUtil.buildExceptionMessage(constraintViolations.toArray(new ConstraintViolation[0]));

        assertTrue(errorMessage.contains("environmentType: may not be null"));
        assertTrue(errorMessage.contains("serverKey: may not be null"));
    }
}
