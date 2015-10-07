package id.co.veritrans.mdk.v1.gateway.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import id.co.veritrans.mdk.v1.exception.JsonDeserializeException;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.CreditCard;
import id.co.veritrans.mdk.v1.helper.JsonUtil;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;

/**
 * Veritrans API response. Every response returned by Veritrans Payment API is represented by this class.
 * Generally one must check the <b>statusCode</b> property to reliably determine the result of a request, do not ever
 * rely on the HTTP status code.
 */
public class VtResponse extends OrderStatus {

    private String statusMessage;
    private String permataVaNumber;
    private String signatureKey;
    @JsonProperty("token_id")
    private String cardToken;
    @JsonProperty("saved_token_id")
    private String savedCardToken;
    @JsonProperty("saved_token_id_expired_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+07")
    private Date savedCardTokenExpiredAt;
    private Boolean secureToken;
    private CreditCard.Bank bank;
    private String billerCode;
    private String billKey;
    private String xlTunaiOrderId;
    private String biiVaNumber;
    private String redirectUrl;
    private String eci;
    private String[] validationMessages;
    private Integer page;
    private Integer totalPage;
    private Integer totalRecord;
    @JsonProperty("transactions")
    private OrderStatus[] listTransactionStatus;
    private String customField1;
    private String customField2;
    private String customField3;


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
        return JsonUtil.fromJson(json, VtResponse.class);
    }

    /**
     * Construct VtResponse instance from a JSON Raw Input Stream bytes.
     *
     * @param inputStream source JSON Raw Input Stream bytes.
     * @return VtResponse instance constructed from JSON Raw Input Stream bytes.
     * @throws id.co.veritrans.mdk.v1.exception.JsonDeserializeException when a failure is occured during deserializing the JSON.
     */
    public static id.co.veritrans.mdk.v1.gateway.model.VtResponse deserializeJson(InputStream inputStream) throws JsonDeserializeException {
        return JsonUtil.fromJson(inputStream, VtResponse.class);
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

    /**
     * Get card token
     *
     * @return Card token
     */
    public String getCardToken() {
        return cardToken;
    }

    /**
     * Set card token
     *
     * @param cardToken card token
     */
    public void setCardToken(final String cardToken) {
        this.cardToken = cardToken;
    }

    /**
     * Get saved card token
     *
     * @return Saved card token
     */
    public String getSavedCardToken() {
        return savedCardToken;
    }

    /**
     * Set saved card token
     *
     * @param savedCardToken Saved card token
     */
    public void setSavedCardToken(final String savedCardToken) {
        this.savedCardToken = savedCardToken;
    }

    /**
     * Get expired of saved card token
     *
     * @return Expired of saved card token
     */
    public Date getSavedCardTokenExpiredAt() {
        return savedCardTokenExpiredAt;
    }

    /**
     * Set expired of saved card token
     *
     * @param savedCardTokenExpiredAt Expired of saved card token
     */
    public void setSavedCardTokenExpiredAt(final Date savedCardTokenExpiredAt) {
        this.savedCardTokenExpiredAt = savedCardTokenExpiredAt;
    }

    /**
     * Get secure token
     *
     * @return Secure token
     */
    public Boolean getSecureToken() {
        return secureToken;
    }

    /**
     * Set secure token
     *
     * @param secureToken Secure token
     */
    public void setSecureToken(final Boolean secureToken) {
        this.secureToken = secureToken;
    }

    /**
     * Get acquirer bank
     *
     * @return Acquirer bank
     */
    public CreditCard.Bank getBank() {
        return bank;
    }

    /**
     * Set acquirer bank
     *
     * @param bank Acquirer bank
     */
    public void setBank(final CreditCard.Bank bank) {
        this.bank = bank;
    }

    /**
     * Get biller code
     *
     * @return Biller code
     */
    public String getBillerCode() {
        return billerCode;
    }

    /**
     * Set biller code
     *
     * @param billerCode Biller code
     */
    public void setBillerCode(final String billerCode) {
        this.billerCode = billerCode;
    }

    /**
     * Get bill key
     *
     * @return Bill key
     */
    public String getBillKey() {
        return billKey;
    }

    /**
     * Set bill key
     *
     * @param billKey Bill key
     */
    public void setBillKey(final String billKey) {
        this.billKey = billKey;
    }

    /**
     * Get XL tunai order id
     *
     * @return XL tunai order id
     */
    public String getXlTunaiOrderId() {
        return xlTunaiOrderId;
    }

    /**
     * Set XL tunai order id
     *
     * @param xlTunaiOrderId XL tunai order id
     */
    public void setXlTunaiOrderId(final String xlTunaiOrderId) {
        this.xlTunaiOrderId = xlTunaiOrderId;
    }

    /**
     * Get BII virtual account number
     *
     * @return BII virtual account number
     */
    public String getBiiVaNumber() {
        return biiVaNumber;
    }

    /**
     * Set BII virtual account number
     *
     * @param biiVaNumber BII virtual account number
     */
    public void setBiiVaNumber(final String biiVaNumber) {
        this.biiVaNumber = biiVaNumber;
    }

    /**
     * Get redirection URL
     *
     * @return Redirection URL
     */
    public String getRedirectUrl() {
        return redirectUrl;
    }

    /**
     * Set redirection URL
     *
     * @param redirectUrl Redirection URL
     */
    public void setRedirectUrl(final String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    /**
     * Get card transaction ECI value
     *
     * @return Card transaction ECI value
     */
    public String getEci() {
        return eci;
    }

    /**
     * Set card transaction ECI value
     *
     * @param eci Card transaction ECI value
     */
    public void setEci(final String eci) {
        this.eci = eci;
    }

    /**
     * Get list of validation message
     *
     * @return List of validation message
     */
    public String[] getValidationMessages() {
        return validationMessages;
    }

    /**
     * Set list of validation message
     *
     * @param validationMessages List of validation message
     */
    public void setValidationMessages(final String[] validationMessages) {
        this.validationMessages = validationMessages;
    }

    /**
     * Get page number of get status transaction
     *
     * @return Page number of get status transaction
     */
    public Integer getPage() {
        return page;
    }

    /**
     * Set page number of get status transaction
     *
     * @param page Page number of get status transaction
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * Get total page of get status transaction
     *
     * @return Total page of get status transaction
     */
    public Integer getTotalPage() {
        return totalPage;
    }

    /**
     * Set total page of get status transaction
     *
     * @param totalPage Total page of get status transaction
     */
    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    /**
     * Get total record of get status transaction
     *
     * @return Total record of get status transaction
     */
    public Integer getTotalRecord() {
        return totalRecord;
    }

    /**
     * Set total record of get status transaction
     *
     * @param totalRecord Total record of get status transaction
     */
    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

    /**
     * Get list of order transaction status
     *
     * @return List of order transaction status
     */
    public OrderStatus[] getListTransactionStatus() {
        return listTransactionStatus;
    }

    /**
     * Set list of order transaction status
     *
     * @param listTransactionStatus List of order transaction status
     */
    public void setListTransactionStatus(OrderStatus[] listTransactionStatus) {
        this.listTransactionStatus = listTransactionStatus;
    }

    /**
     * Get custom field 1 parameter
     *
     * @return Custom field 1 parameter
     */
    public String getCustomField1() {
        return customField1;
    }

    /**
     * Set custom field 1 parameter
     *
     * @param customField1 Custom field 1 parameter
     */
    public void setCustomField1(String customField1) {
        this.customField1 = customField1;
    }

    /**
     * Get custom field 2 parameter
     *
     * @return Custom field 2 parameter
     */
    public String getCustomField2() {
        return customField2;
    }

    /**
     * Set custom field 2 parameter
     *
     * @param customField2 Custom field 2 parameter
     */
    public void setCustomField2(String customField2) {
        this.customField2 = customField2;
    }

    /**
     * Get custom field 3 parameter
     *
     * @return Custom field 3 parameter
     */
    public String getCustomField3() {
        return customField3;
    }

    /**
     * Set custom field 3 parameter
     *
     * @param customField3 Custom field 3 parameter
     */
    public void setCustomField3(String customField3) {
        this.customField3 = customField3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VtResponse that = (VtResponse) o;

        if (bank != that.bank) return false;
        if (biiVaNumber != null ? !biiVaNumber.equals(that.biiVaNumber) : that.biiVaNumber != null) return false;
        if (billKey != null ? !billKey.equals(that.billKey) : that.billKey != null) return false;
        if (billerCode != null ? !billerCode.equals(that.billerCode) : that.billerCode != null) return false;
        if (cardToken != null ? !cardToken.equals(that.cardToken) : that.cardToken != null) return false;
        if (customField1 != null ? !customField1.equals(that.customField1) : that.customField1 != null) return false;
        if (customField2 != null ? !customField2.equals(that.customField2) : that.customField2 != null) return false;
        if (customField3 != null ? !customField3.equals(that.customField3) : that.customField3 != null) return false;
        if (eci != null ? !eci.equals(that.eci) : that.eci != null) return false;
        if (!Arrays.equals(listTransactionStatus, that.listTransactionStatus)) return false;
        if (page != null ? !page.equals(that.page) : that.page != null) return false;
        if (permataVaNumber != null ? !permataVaNumber.equals(that.permataVaNumber) : that.permataVaNumber != null)
            return false;
        if (redirectUrl != null ? !redirectUrl.equals(that.redirectUrl) : that.redirectUrl != null) return false;
        if (savedCardToken != null ? !savedCardToken.equals(that.savedCardToken) : that.savedCardToken != null)
            return false;
        if (savedCardTokenExpiredAt != null ? !savedCardTokenExpiredAt.equals(that.savedCardTokenExpiredAt) : that.savedCardTokenExpiredAt != null)
            return false;
        if (secureToken != null ? !secureToken.equals(that.secureToken) : that.secureToken != null) return false;
        if (signatureKey != null ? !signatureKey.equals(that.signatureKey) : that.signatureKey != null) return false;
        if (statusMessage != null ? !statusMessage.equals(that.statusMessage) : that.statusMessage != null)
            return false;
        if (totalPage != null ? !totalPage.equals(that.totalPage) : that.totalPage != null) return false;
        if (totalRecord != null ? !totalRecord.equals(that.totalRecord) : that.totalRecord != null) return false;
        if (!Arrays.equals(validationMessages, that.validationMessages)) return false;
        if (xlTunaiOrderId != null ? !xlTunaiOrderId.equals(that.xlTunaiOrderId) : that.xlTunaiOrderId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = statusMessage != null ? statusMessage.hashCode() : 0;
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
        result = 31 * result + (eci != null ? eci.hashCode() : 0);
        result = 31 * result + (validationMessages != null ? Arrays.hashCode(validationMessages) : 0);
        result = 31 * result + (page != null ? page.hashCode() : 0);
        result = 31 * result + (totalPage != null ? totalPage.hashCode() : 0);
        result = 31 * result + (totalRecord != null ? totalRecord.hashCode() : 0);
        result = 31 * result + (listTransactionStatus != null ? Arrays.hashCode(listTransactionStatus) : 0);
        result = 31 * result + (customField1 != null ? customField1.hashCode() : 0);
        result = 31 * result + (customField2 != null ? customField2.hashCode() : 0);
        result = 31 * result + (customField3 != null ? customField3.hashCode() : 0);
        return result;
    }
}
