package id.co.veritrans.mdk.v1.gateway.model;

/**
 * Customer address
 */
public class Address {

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String postalCode;
    private String phone;
    private String countryCode;

    /**
     * Customer address constructor
     */
    public Address() {
    }

    /**
     * Customer address constructor
     *
     * @param firstName   Customer address first name
     * @param lastName    Customer address last name
     * @param address     Customer address details
     * @param city        Customer address city
     * @param postalCode  Customer address postal code
     * @param phone       Customer address phone number
     * @param countryCode Customer address country code
     */
    public Address(final String firstName, final String lastName, final String address, final String city, final String postalCode, final String phone, final String countryCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.phone = phone;
        this.countryCode = countryCode;
    }

    /**
     * Get customer address first name
     *
     * @return Customer address first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set customer address first name
     *
     * @param firstName Customer address first name
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * Set customer address last name
     *
     * @return Customer address last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set customer address last name
     *
     * @param lastName Customer address last name
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get customer address details
     *
     * @return Customer address details
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set customer address details
     *
     * @param address Customer address details
     */
    public void setAddress(final String address) {
        this.address = address;
    }

    /**
     * Get customer address city
     *
     * @return Customer address city
     */
    public String getCity() {
        return city;
    }

    /**
     * Set customer address city
     *
     * @param city Customer address city
     */
    public void setCity(final String city) {
        this.city = city;
    }

    /**
     * Get customer address postal code
     *
     * @return Customer address postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Set customer address postal code
     *
     * @param postalCode Customer address postal code
     */
    public void setPostalCode(final String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Get customer address phone
     *
     * @return Customer address phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set customer address phone
     *
     * @param phone Customer address phone
     */
    public void setPhone(final String phone) {
        this.phone = phone;
    }

    /**
     * Get customer address country code
     *
     * @return Customer address country code
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Set customer address country code
     *
     * @param countryCode Customer address country code
     */
    public void setCountryCode(final String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Address that = (Address) o;

        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (countryCode != null ? !countryCode.equals(that.countryCode) : that.countryCode != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (postalCode != null ? !postalCode.equals(that.postalCode) : that.postalCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (countryCode != null ? countryCode.hashCode() : 0);
        return result;
    }
}
