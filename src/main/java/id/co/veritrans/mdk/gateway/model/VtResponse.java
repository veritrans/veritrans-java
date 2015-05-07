package id.co.veritrans.mdk.gateway.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by gde on 5/5/15.
 */
public class VtResponse {

    private String transactionId;
    private String orderId;
    private BigDecimal grossAmount;
    private PaymentMethod paymentMethod;
    private Date transactionTime;
    private TransactionStatus transactionStatus;
    private FraudStatus fraudStatus;
    private String maskedCardNumber;
    private String statusCode;
    private String statusMessage;
    private String approvalCode;
    private String permataVaNumber;
    private String signature_key;

    public VtResponse() {
    }

    public VtResponse(final String transactionId, final String orderId, final BigDecimal grossAmount, final PaymentMethod paymentMethod, final Date transactionTime, final TransactionStatus transactionStatus, final FraudStatus fraudStatus, final String maskedCardNumber, final String statusCode, final String statusMessage, final String approvalCode) {
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

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(final String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(final String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(final BigDecimal grossAmount) {
        this.grossAmount = grossAmount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(final PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(final Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(final TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public FraudStatus getFraudStatus() {
        return fraudStatus;
    }

    public void setFraudStatus(final FraudStatus fraudStatus) {
        this.fraudStatus = fraudStatus;
    }

    public String getMaskedCardNumber() {
        return maskedCardNumber;
    }

    public void setMaskedCardNumber(final String maskedCardNumber) {
        this.maskedCardNumber = maskedCardNumber;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(final String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(final String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(final String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public String getPermataVaNumber() {
        return permataVaNumber;
    }

    public void setPermataVaNumber(String permataVaNumber) {
        this.permataVaNumber = permataVaNumber;
    }

    public String getSignature_key() {
        return signature_key;
    }

    public void setSignature_key(String signature_key) {
        this.signature_key = signature_key;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final VtResponse that = (VtResponse) o;

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
