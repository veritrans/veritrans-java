package id.co.veritrans.mdk.v1.gateway.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Order status response
 */
public class OrderStatus {

    private String statusCode;
    private String transactionId;
    private String orderId;
    @JsonProperty("payment_type")
    private String paymentMethod;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+07")
    private Date transactionTime;
    private TransactionStatus transactionStatus;
    private String approvalCode;
    private BigDecimal grossAmount;
    private FraudStatus fraudStatus;
    @JsonProperty("masked_card")
    private String maskedCardNumber;

    /**
     * Get transaction identifier
     *
     * @return Transaction identifier
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Set transaction identifier
     *
     * @param transactionId Transaction identifier
     */
    public void setTransactionId(final String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Get transaction order identifier
     *
     * @return Transaction order identifier
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Set transaction order identifier
     *
     * @param orderId Transaction order identifier
     */
    public void setOrderId(final String orderId) {
        this.orderId = orderId;
    }

    /**
     * Get transaction total gross amount
     *
     * @return Transaction total gross amount
     */
    public BigDecimal getGrossAmount() {
        return grossAmount;
    }

    /**
     * Set transaction total gross amount
     *
     * @param grossAmount Transaction total gross amount
     */
    public void setGrossAmount(final BigDecimal grossAmount) {
        this.grossAmount = grossAmount;
    }

    /**
     * Get transaction payment method
     *
     * @return Transaction payment method
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Set transaction payment method
     *
     * @param paymentMethod Transaction payment method
     */
    public void setPaymentMethod(final String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Get transaction time
     *
     * @return Transaction time
     */
    public Date getTransactionTime() {
        return transactionTime;
    }

    /**
     * Set transaction time
     *
     * @param transactionTime Transaction time
     */
    public void setTransactionTime(final Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    /**
     * Get transaction status
     *
     * @return {@link id.co.veritrans.mdk.v1.gateway.model.TransactionStatus Transaction status}
     */
    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    /**
     * Set transaction status
     *
     * @param transactionStatus {@link id.co.veritrans.mdk.v1.gateway.model.TransactionStatus Transaction status}
     */
    public void setTransactionStatus(final TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    /**
     * Get transaction status code
     *
     * @return <a href="http://docs.veritrans.co.id/sandbox/status_code.html">Transaction status code</a>
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * Set transaction status code
     *
     * @param statusCode <a href="http://docs.veritrans.co.id/sandbox/status_code.html">Transaction status code</a>
     */
    public void setStatusCode(final String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * Get transaction approval code
     *
     * @return Transaction approval code
     */
    public String getApprovalCode() {
        return approvalCode;
    }

    /**
     * Set transaction approval code
     *
     * @param approvalCode Transaction approval code
     */
    public void setApprovalCode(final String approvalCode) {
        this.approvalCode = approvalCode;
    }

    /**
     * Get Transaction fraud status
     *
     * @return {@link id.co.veritrans.mdk.v1.gateway.model.FraudStatus Transaction fraud status}
     */
    public FraudStatus getFraudStatus() {
        return fraudStatus;
    }

    /**
     * Set transaction fraud status
     *
     * @param fraudStatus {@link id.co.veritrans.mdk.v1.gateway.model.FraudStatus Transaction fraud status}
     */
    public void setFraudStatus(final FraudStatus fraudStatus) {
        this.fraudStatus = fraudStatus;
    }

    /**
     * Get transaction masked card (for credit card)
     *
     * @return Transaction masked card (for credit card)
     */
    public String getMaskedCardNumber() {
        return maskedCardNumber;
    }

    /**
     * Set transaction masked card (for credit card)
     *
     * @param maskedCardNumber Transaction masked card (for credit card)
     */
    public void setMaskedCardNumber(final String maskedCardNumber) {
        this.maskedCardNumber = maskedCardNumber;
    }
}
