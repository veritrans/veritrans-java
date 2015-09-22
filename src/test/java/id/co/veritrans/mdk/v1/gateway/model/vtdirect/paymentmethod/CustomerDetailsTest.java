package id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod;

import id.co.veritrans.mdk.v1.gateway.model.CustomerDetails;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

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

    }

}
