package id.co.veritrans.mdk.v1.gateway.model;

import id.co.veritrans.mdk.v1.exception.JsonDeserializeException;

import java.io.InputStream;
import java.util.Date;

/**
 * Veritrans transaction response
 */
public class VtResponse {

    private String transactionId;
    private String orderId;
    private Long grossAmount;
    private PaymentMethod paymentMethod;
    private Date transactionTime;
    private TransactionStatus transactionStatus;
    private id.co.veritrans.mdk.v1.gateway.model.FraudStatus fraudStatus;
    private String maskedCardNumber;
    private String statusCode;
    private String statusMessage;
    private String approvalCode;
    private String permataVaNumber;
    private String signature_key;

    /**
     * Veritrans transaction response constructor
     */
    public VtResponse() {
    }

    /**
     * Veritrans transaction response constructor
     *
     * @param transactionId     Transaction identifier
     * @param orderId           Transaction order identifier
     * @param grossAmount       Transaction total gross amount
     * @param paymentMethod     Transaction payment method
     * @param transactionTime   Transaction time
     * @param transactionStatus {@link id.co.veritrans.mdk.v1.gateway.model.TransactionStatus Transaction status}
     * @param fraudStatus       {@link id.co.veritrans.mdk.v1.gateway.model.FraudStatus Transaction fraud status}
     * @param maskedCardNumber  Transaction masked card (for credit card)
     * @param statusCode        <a href="http://docs.veritrans.co.id/sandbox/status_code.html">Transaction status code</a>
     * @param statusMessage     Transaction status message
     * @param approvalCode      Transaction approval code
     */
    public VtResponse(final String transactionId, final String orderId, final Long grossAmount, final PaymentMethod paymentMethod, final Date transactionTime, final TransactionStatus transactionStatus, final id.co.veritrans.mdk.v1.gateway.model.FraudStatus fraudStatus, final String maskedCardNumber, final String statusCode, final String statusMessage, final String approvalCode) {
        this.transactionId = transactionId;
        this.orderId = orderId;
        this.grossAmount = grossAmount;
        this.paymentMethod = paymentMethod;
        this.transactionTime = transactionTime;
        this.transactionStatus = transactionStatus;
        this.fraudStatus = fraudStatus;
        this.maskedCardNumber = maskedCardNumber;
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.approvalCode = approvalCode;
    }

    /**
     * Construct VtResponse instance from a JSON String.
     *
     * @param json source JSON String.
     * @return VtResponse instance constructed from JSON.
     * @throws id.co.veritrans.mdk.v1.exception.JsonDeserializeException when a failure is occured during deserializing the JSON.
     */
    public static id.co.veritrans.mdk.v1.gateway.model.VtResponse deserializeJson(String json) throws JsonDeserializeException {
        return null;
    }

    /**
     * Construct VtResponse instance from a JSON Raw Input Stream bytes.
     *
     * @param inputStream source JSON Raw Input Stream bytes.
     * @return VtResponse instance constructed from JSON Raw Input Stream bytes.
     * @throws id.co.veritrans.mdk.v1.exception.JsonDeserializeException when a failure is occured during deserializing the JSON.
     */
    public static id.co.veritrans.mdk.v1.gateway.model.VtResponse deserializeJson(InputStream inputStream) throws JsonDeserializeException {
        return null;
    }

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
    public Long getGrossAmount() {
        return grossAmount;
    }

    /**
     * Set transaction total gross amount
     *
     * @param grossAmount Transaction total gross amount
     */
    public void setGrossAmount(final Long grossAmount) {
        this.grossAmount = grossAmount;
    }

    /**
     * Get transaction payment method
     *
     * @return Transaction payment method
     */
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Set transaction payment method
     *
     * @param paymentMethod Transaction payment method
     */
    public void setPaymentMethod(final PaymentMethod paymentMethod) {
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
     * Get Transaction fraud status
     *
     * @return {@link id.co.veritrans.mdk.v1.gateway.model.FraudStatus Transaction fraud status}
     */
    public id.co.veritrans.mdk.v1.gateway.model.FraudStatus getFraudStatus() {
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
     * Get transaction status message
     *
     * @return Transaction status message
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * Set transaction status message
     *
     * @param statusMessage Transaction status message
     */
    public void setStatusMessage(final String statusMessage) {
        this.statusMessage = statusMessage;
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
     * Get permata virtual account number
     *
     * @return Permata virtual account number
     */
    public String getPermataVaNumber() {
        return permataVaNumber;
    }

    /**
     * Set permata virtual account number
     *
     * @param permataVaNumber Permata virtual account number
     */
    public void setPermataVaNumber(String permataVaNumber) {
        this.permataVaNumber = permataVaNumber;
    }

    /**
     * Get signature key
     *
     * @return Signature key
     */
    public String getSignature_key() {
        return signature_key;
    }

    /**
     * Set signature key
     *
     * @param signature_key Signature key
     */
    public void setSignature_key(String signature_key) {
        this.signature_key = signature_key;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final id.co.veritrans.mdk.v1.gateway.model.VtResponse that = (id.co.veritrans.mdk.v1.gateway.model.VtResponse) o;

        if (approvalCode != null ? !approvalCode.equals(that.approvalCode) : that.approvalCode != null) return false;
        if (fraudStatus != null ? !fraudStatus.equals(that.fraudStatus) : that.fraudStatus != null) return false;
        if (grossAmount != null ? !grossAmount.equals(that.grossAmount) : that.grossAmount != null) return false;
        if (maskedCardNumber != null ? !maskedCardNumber.equals(that.maskedCardNumber) : that.maskedCardNumber != null)
            return false;
        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        if (paymentMethod != that.paymentMethod) return false;
        if (statusCode != null ? !statusCode.equals(that.statusCode) : that.statusCode != null) return false;
        if (statusMessage != null ? !statusMessage.equals(that.statusMessage) : that.statusMessage != null)
            return false;
        if (transactionId != null ? !transactionId.equals(that.transactionId) : that.transactionId != null)
            return false;
        if (transactionStatus != null ? !transactionStatus.equals(that.transactionStatus) : that.transactionStatus != null)
            return false;
        if (transactionTime != null ? !transactionTime.equals(that.transactionTime) : that.transactionTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = transactionId != null ? transactionId.hashCode() : 0;
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        result = 31 * result + (grossAmount != null ? grossAmount.hashCode() : 0);
        result = 31 * result + (paymentMethod != null ? paymentMethod.hashCode() : 0);
        result = 31 * result + (transactionTime != null ? transactionTime.hashCode() : 0);
        result = 31 * result + (transactionStatus != null ? transactionStatus.hashCode() : 0);
        result = 31 * result + (fraudStatus != null ? fraudStatus.hashCode() : 0);
        result = 31 * result + (maskedCardNumber != null ? maskedCardNumber.hashCode() : 0);
        result = 31 * result + (statusCode != null ? statusCode.hashCode() : 0);
        result = 31 * result + (statusMessage != null ? statusMessage.hashCode() : 0);
        result = 31 * result + (approvalCode != null ? approvalCode.hashCode() : 0);
        return result;
    }
}
