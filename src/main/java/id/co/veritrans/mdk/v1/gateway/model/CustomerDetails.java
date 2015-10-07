package id.co.veritrans.mdk.v1.gateway.model;

/**
 * Transaction customer detail
 */
public class CustomerDetails {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Address billingAddress;
    private Address shippingAddress;

    /**
     * Customer detail constructor
     */
    public CustomerDetails() {
    }

    /**
     * Customer detail constructor
     *
     * @param firstName       Customer first name
     * @param lastName        Customer last name
     * @param email           Customer email
     * @param phone           Customer phone number
     * @param billingAddress  Customer {@link id.co.veritrans.mdk.v1.gateway.model.Address billing address}
     * @param shippingAddress Customer {@link id.co.veritrans.mdk.v1.gateway.model.Address shipping address}
     */
    public CustomerDetails(final String firstName, final String lastName, final String email, final String phone, final Address billingAddress, final Address shippingAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
    }

    /**
     * Get customer first name
     *
     * @return Customer first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set customer first name
     *
     * @param firstName Customer first name
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get customer last name
     *
     * @return Customer last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set customer last name
     *
     * @param lastName Customer last name
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get customer email
     *
     * @return Customer email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set customer email
     *
     * @param email Customer email
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Get customer phone number
     *
     * @return Customer phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set customer phone number
     *
     * @param phone Customer phone number
     */
    public void setPhone(final String phone) {
        this.phone = phone;
    }

    /**
     * Get customer billing address
     *
     * @return Customer {@link id.co.veritrans.mdk.v1.gateway.model.Address billing address}
     */
    public Address getBillingAddress() {
        return billingAddress;
    }

    /**
     * Set customer billing address
     *
     * @param billingAddress Customer {@link id.co.veritrans.mdk.v1.gateway.model.Address billing address}
     */
    public void setBillingAddress(final Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    /**
     * Get customer shipping address
     *
     * @return Customer {@link id.co.veritrans.mdk.v1.gateway.model.Address shipping address}
     */
    public Address getShippingAddress() {
        return shippingAddress;
    }

    /**
     * Set customer shipping address
     *
     * @param shippingAddress Customer {@link id.co.veritrans.mdk.v1.gateway.model.Address shipping address}
     */
    public void setShippingAddress(final Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final CustomerDetails that = (CustomerDetails) o;

        if (billingAddress != null ? !billingAddress.equals(that.billingAddress) : that.billingAddress != null)
            return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (shippingAddress != null ? !shippingAddress.equals(that.shippingAddress) : that.shippingAddress != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (billingAddress != null ? billingAddress.hashCode() : 0);
        result = 31 * result + (shippingAddress != null ? shippingAddress.hashCode() : 0);
        return result;
    }
}
