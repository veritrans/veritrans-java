package id.co.veritrans.mdk.gateway.model.vtdirect.paymentmethod;

import id.co.veritrans.mdk.v1.gateway.model.CustomerDetails;
import id.co.veritrans.mdk.v1.helper.ValidationUtil;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.testng.Assert.*;

/**
 * Created by andes on 5/7/15.
 */
public class CustomerDetailsTest {

    public static CustomerDetails buildCustomerDetail() {
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setFirstName("Eko Kurniawan");
        customerDetails.setEmail("eko.kurniawan@gmail.com");
        customerDetails.setPhone("+6281-2233-4455");

        return customerDetails;
    }

    @Test
    public void testCustomerDetailsNormal() {
        CustomerDetails customerDetails = buildCustomerDetail();
        customerDetails.setLastName("Khannedy");

        assertEquals(customerDetails.getFirstName(), "Eko Kurniawan");
        assertEquals(customerDetails.getLastName(), "Khannedy");
        assertEquals(customerDetails.getEmail(), "eko.kurniawan@gmail.com");
        assertEquals(customerDetails.getPhone(), "+6281-2233-4455");

        Set<ConstraintViolation<CustomerDetails>> constraintViolations = ValidationUtil.getValidator().validate(customerDetails);
        assertTrue(constraintViolations.isEmpty());
    }

    @Test
    public void testCustomerDetailsError() {
        CustomerDetails customerDetails = new CustomerDetails();

        Set<ConstraintViolation<CustomerDetails>> constraintViolations = ValidationUtil.getValidator().validate(customerDetails);
        assertFalse(constraintViolations.isEmpty());

        String errorMessage = ValidationUtil.buildExceptionMessage(constraintViolations.toArray(new ConstraintViolation[0]));
        assertTrue(errorMessage.contains("firstName"));
        assertTrue(errorMessage.contains("email"));
        assertTrue(errorMessage.contains("phone"));

        /* Test phone number validation */
        customerDetails = buildCustomerDetail();
        customerDetails.setPhone("+6231");

        constraintViolations = ValidationUtil.getValidator().validate(customerDetails);
        assertFalse(constraintViolations.isEmpty());

        errorMessage = ValidationUtil.buildExceptionMessage(constraintViolations.toArray(new ConstraintViolation[0]));
        assertTrue(errorMessage.contains("phone"));
    }
}
