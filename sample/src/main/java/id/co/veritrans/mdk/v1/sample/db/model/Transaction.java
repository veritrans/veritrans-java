package id.co.veritrans.mdk.v1.sample.db.model;

import javax.persistence.*;

/**
 * Created by gde on 5/18/15.
 */
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "customer_first_name", length = 20, nullable = true)
    private String customerFirstName;

    @Column(name = "customer_last_name", length = 20, nullable = true)
    private String customerLastName;

    @Column(name = "customer_email", length = 45, nullable = true)
    private String customerEmail;

    @Column(name = "customer_phone", length = 19, nullable = true)
    private String customerPhone;

    @Column(name = "billing_first_name", length = 20, nullable = true)
    private String billingFirstName;

    @Column(name = "billing_last_name", length = 20, nullable = true)
    private String billingLastName;

    @Column(name = "billing_address", length = 200, nullable = true)
    private String billingAddress;

    @Column(name = "billing_city", length = 20, nullable = true)
    private String billingCity;

    @Column(name = "billing_postal_code", length = 10, nullable = true)
    private String billingPostalCode;

    @Column(name = "billing_phone", length = 19, nullable = true)
    private String billingPhone;

    @Column(name = "billing_country_code", length = 3, nullable = true)
    private String billingCountryCode;

    @Column(name = "shipping_first_name", length = 20, nullable = true)
    private String shippingFirstName;

    @Column(name = "shipping_last_name", length = 20, nullable = true)
    private String shippingLastName;

    @Column(name = "shipping_address", length = 200, nullable = true)
    private String shippingAddress;

    @Column(name = "shipping_city", length = 20, nullable = true)
    private String shippingCity;

    @Column(name = "shipping_postal_code", length = 10, nullable = true)
    private String shippingPostalCode;

    @Column(name = "shipping_phone", length = 19, nullable = true)
    private String shippingPhone;

    @Column(name = "shipping_country_code", length = 3, nullable = true)
    private String shippingCountryCode;

    @Column(name = "payment_order_id", length = 50, nullable = false)
    private String paymentOrderId;

    @Column(name = "payment_transaction_id", length = 100)
    private String paymentTransactionId;

    @Column(name = "total_price_idr", nullable = false)
    private Long totalPriceIdr;

    @Column(name = "payment_method", length = 20, nullable = false)
    private String paymentMethod;

    @Column(name = "payment_status", length = 20)
    private String paymentStatus;

    @Column(name = "payment_fds_status", length = 20)
    private String paymentFdsStatus;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(final String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(final String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(final String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(final String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getBillingFirstName() {
        return billingFirstName;
    }

    public void setBillingFirstName(final String billingFirstName) {
        this.billingFirstName = billingFirstName;
    }

    public String getBillingLastName() {
        return billingLastName;
    }

    public void setBillingLastName(final String billingLastName) {
        this.billingLastName = billingLastName;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(final String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(final String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingPostalCode() {
        return billingPostalCode;
    }

    public void setBillingPostalCode(final String billingPostalCode) {
        this.billingPostalCode = billingPostalCode;
    }

    public String getBillingPhone() {
        return billingPhone;
    }

    public void setBillingPhone(final String billingPhone) {
        this.billingPhone = billingPhone;
    }

    public String getBillingCountryCode() {
        return billingCountryCode;
    }

    public void setBillingCountryCode(final String billingCountryCode) {
        this.billingCountryCode = billingCountryCode;
    }

    public String getShippingFirstName() {
        return shippingFirstName;
    }

    public void setShippingFirstName(final String shippingFirstName) {
        this.shippingFirstName = shippingFirstName;
    }

    public String getShippingLastName() {
        return shippingLastName;
    }

    public void setShippingLastName(final String shippingLastName) {
        this.shippingLastName = shippingLastName;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(final String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public void setShippingCity(final String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getShippingPostalCode() {
        return shippingPostalCode;
    }

    public void setShippingPostalCode(final String shippingPostalCode) {
        this.shippingPostalCode = shippingPostalCode;
    }

    public String getShippingPhone() {
        return shippingPhone;
    }

    public void setShippingPhone(final String shippingPhone) {
        this.shippingPhone = shippingPhone;
    }

    public String getShippingCountryCode() {
        return shippingCountryCode;
    }

    public void setShippingCountryCode(final String shippingCountryCode) {
        this.shippingCountryCode = shippingCountryCode;
    }

    public String getPaymentOrderId() {
        return paymentOrderId;
    }

    public void setPaymentOrderId(final String paymentOrderId) {
        this.paymentOrderId = paymentOrderId;
    }

    public String getPaymentTransactionId() {
        return paymentTransactionId;
    }

    public void setPaymentTransactionId(final String paymentTransactionId) {
        this.paymentTransactionId = paymentTransactionId;
    }

    public Long getTotalPriceIdr() {
        return totalPriceIdr;
    }

    public void setTotalPriceIdr(final Long totalPriceIdr) {
        this.totalPriceIdr = totalPriceIdr;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(final String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(final String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentFdsStatus() {
        return paymentFdsStatus;
    }

    public void setPaymentFdsStatus(final String paymentFdsStatus) {
        this.paymentFdsStatus = paymentFdsStatus;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Transaction that = (Transaction) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
