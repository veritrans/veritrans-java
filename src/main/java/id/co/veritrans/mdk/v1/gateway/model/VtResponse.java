package id.co.veritrans.mdk.v1.gateway.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import id.co.veritrans.mdk.v1.exception.JsonDeserializeException;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.CreditCard;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

/**
 * Veritrans transaction response
 */
public class VtResponse {

    private String transactionId;
    private String orderId;
    private BigDecimal grossAmount;
    @JsonProperty("payment_type")
    private PaymentMethod paymentMethod;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss", timezone="GMT+07")
    private Date transactionTime;
    private TransactionStatus transactionStatus;
    private FraudStatus fraudStatus;
    @JsonProperty("masked_card")
    private String maskedCardNumber;
    private String statusCode;
    private String statusMessage;
    private String approvalCode;
    private String permataVaNumber;
    private String signatureKey;
    @JsonProperty("token_id")
    private String cardToken;
    @JsonProperty("saved_token_id")
    private String savedCardToken;
    @JsonProperty("saved_token_id_expired_at")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss", timezone="GMT+07")
    private Date savedCardTokenExpiredAt;
    private Boolean secureToken;
    private CreditCard.Bank bank;
    private String billerCode;
    private String billKey;
    private String xlTunaiOrderId;
    private String biiVaNumber;
    private String redirectUrl;
    private String[] validationMessages;
    private String redirectUrl;

    /**
     * Veritrans transaction response constructor
     */
    public VtResponse() {
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
    public String getSignatureKey() {
        return signatureKey;
    }

    /**
     * Set signature key
     *
     * @param signatureKey Signature key
     */
    public void setSignatureKey(String signatureKey) {
        this.signatureKey = signatureKey;
    }

    public String getCardToken() {
        return cardToken;
    }

    public void setCardToken(final String cardToken) {
        this.cardToken = cardToken;
    }

    public String getSavedCardToken() {
        return savedCardToken;
    }

    public void setSavedCardToken(final String savedCardToken) {
        this.savedCardToken = savedCardToken;
    }

    public Date getSavedCardTokenExpiredAt() {
        return savedCardTokenExpiredAt;
    }

    public void setSavedCardTokenExpiredAt(final Date savedCardTokenExpiredAt) {
        this.savedCardTokenExpiredAt = savedCardTokenExpiredAt;
    }

    public Boolean getSecureToken() {
        return secureToken;
    }

    public void setSecureToken(final Boolean secureToken) {
        this.secureToken = secureToken;
    }

    public CreditCard.Bank getBank() {
        return bank;
    }

    public void setBank(final CreditCard.Bank bank) {
        this.bank = bank;
    }

    public String getBillerCode() {
        return billerCode;
    }

    public void setBillerCode(final String billerCode) {
        this.billerCode = billerCode;
    }

    public String getBillKey() {
        return billKey;
    }

    public void setBillKey(final String billKey) {
        this.billKey = billKey;
    }

    public String getXlTunaiOrderId() {
        return xlTunaiOrderId;
    }

    public void setXlTunaiOrderId(final String xlTunaiOrderId) {
        this.xlTunaiOrderId = xlTunaiOrderId;
    }

    public String getBiiVaNumber() {
        return biiVaNumber;
    }

    public void setBiiVaNumber(final String biiVaNumber) {
        this.biiVaNumber = biiVaNumber;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(final String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String[] getValidationMessages() {
        return validationMessages;
    }

    public void setValidationMessages(final String[] validationMessages) {
        this.validationMessages = validationMessages;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final VtResponse that = (VtResponse) o;

        if (approvalCode != null ? !approvalCode.equals(that.approvalCode) : that.approvalCode != null) return false;
        if (bank != that.bank) return false;
        if (biiVaNumber != null ? !biiVaNumber.equals(that.biiVaNumber) : that.biiVaNumber != null) return false;
        if (billKey != null ? !billKey.equals(that.billKey) : that.billKey != null) return false;
        if (billerCode != null ? !billerCode.equals(that.billerCode) : that.billerCode != null) return false;
        if (cardToken != null ? !cardToken.equals(that.cardToken) : that.cardToken != null) return false;
        if (fraudStatus != that.fraudStatus) return false;
        if (grossAmount != null ? !grossAmount.equals(that.grossAmount) : that.grossAmount != null) return false;
        if (maskedCardNumber != null ? !maskedCardNumber.equals(that.maskedCardNumber) : that.maskedCardNumber != null)
            return false;
        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        if (paymentMethod != that.paymentMethod) return false;
        if (permataVaNumber != null ? !permataVaNumber.equals(that.permataVaNumber) : that.permataVaNumber != null)
            return false;
        if (redirectUrl != null ? !redirectUrl.equals(that.redirectUrl) : that.redirectUrl != null) return false;
        if (savedCardToken != null ? !savedCardToken.equals(that.savedCardToken) : that.savedCardToken != null)
            return false;
        if (savedCardTokenExpiredAt != null ? !savedCardTokenExpiredAt.equals(that.savedCardTokenExpiredAt) : that.savedCardTokenExpiredAt != null)
            return false;
        if (secureToken != null ? !secureToken.equals(that.secureToken) : that.secureToken != null) return false;
        if (signatureKey != null ? !signatureKey.equals(that.signatureKey) : that.signatureKey != null) return false;
        if (statusCode != null ? !statusCode.equals(that.statusCode) : that.statusCode != null) return false;
        if (statusMessage != null ? !statusMessage.equals(that.statusMessage) : that.statusMessage != null)
            return false;
        if (transactionId != null ? !transactionId.equals(that.transactionId) : that.transactionId != null)
            return false;
        if (transactionStatus != that.transactionStatus) return false;
        if (transactionTime != null ? !transactionTime.equals(that.transactionTime) : that.transactionTime != null)
            return false;
        if (!Arrays.equals(validationMessages, that.validationMessages)) return false;
        if (xlTunaiOrderId != null ? !xlTunaiOrderId.equals(that.xlTunaiOrderId) : that.xlTunaiOrderId != null)
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
        result = 31 * result + (permataVaNumber != null ? permataVaNumber.hashCode() : 0);
        result = 31 * result + (signatureKey != null ? signatureKey.hashCode() : 0);
        result = 31 * result + (cardToken != null ? cardToken.hashCode() : 0);
        result = 31 * result + (savedCardToken != null ? savedCardToken.hashCode() : 0);
        result = 31 * result + (savedCardTokenExpiredAt != null ? savedCardTokenExpiredAt.hashCode() : 0);
        result = 31 * result + (secureToken != null ? secureToken.hashCode() : 0);
        result = 31 * result + (bank != null ? bank.hashCode() : 0);
        result = 31 * result + (billerCode != null ? billerCode.hashCode() : 0);
        result = 31 * result + (billKey != null ? billKey.hashCode() : 0);
        result = 31 * result + (xlTunaiOrderId != null ? xlTunaiOrderId.hashCode() : 0);
        result = 31 * result + (biiVaNumber != null ? biiVaNumber.hashCode() : 0);
        result = 31 * result + (redirectUrl != null ? redirectUrl.hashCode() : 0);
        result = 31 * result + (validationMessages != null ? Arrays.hashCode(validationMessages) : 0);
        return result;
    }
}
