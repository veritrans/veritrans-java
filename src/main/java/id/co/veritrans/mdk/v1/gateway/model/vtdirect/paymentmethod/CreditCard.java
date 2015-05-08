package id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * VT-Direct credit card data model
 */
public class CreditCard {

    @NotNull
    private String tokenId;
    @NotNull
    @JsonIgnore
    private Bank acquirerBank;
    private Integer installmentTerm;
    private List<String> bins;
    @JsonIgnore
    private TransactionType transactionType;
    private Boolean saveTokenId;

    /* Handle enum serialization */
    @JsonProperty("transaction_type")
    private String _getTransactionType() {
        if (transactionType == null){
            return null;
        }
        return transactionType.name;
    }

    @JsonProperty("acquirer_bank")
    private String _getAcquirerBank() {
        if (acquirerBank == null) {
            return null;
        }
        return acquirerBank.name;
    }
    /* ************************* */

    /**
     * Credit card bank enum
     */
    public static enum Bank {
        BNI("bni"),
        MANDIRI("mandiri"),
        CIMB("cimb");

        private final String name;

        Bank(final String name) {
            this.name = name;
        }
    }

    /**
     * Credit card transaction type
     */
    public static enum TransactionType {
        AUTHORIZE("authorize");

        private final String name;

        TransactionType(final String name) {
            this.name = name;
        }
    }

    /**
     * Credit card constructor
     */
    public CreditCard() {
    }

    /**
     * Credit card constructor
     *
     * @param tokenId         Veritrans card token identifier
     * @param acquirerBank    Veritrans credit card {@link CreditCard.Bank acquiring bank}
     * @param installmentTerm Transaction installment term (month)
     * @param bins            Merchant bin promo
     * @param transactionType {@link CreditCard.TransactionType Transaction type} for current transaction
     * @param saveTokenId     Save token id for one/two click feature
     */
    public CreditCard(final String tokenId, final Bank acquirerBank, final Integer installmentTerm, final List<String> bins, final TransactionType transactionType, final Boolean saveTokenId) {
        this.tokenId = tokenId;
        this.acquirerBank = acquirerBank;
        this.installmentTerm = installmentTerm;
        this.bins = bins;
        this.transactionType = transactionType;
        this.saveTokenId = saveTokenId;
    }

    /**
     * Get credit card token ID
     *
     * @return Credit card token identifier
     */
    public String getTokenId() {
        return tokenId;
    }

    /**
     * Set credit card token ID
     *
     * @param tokenId Credit card token identifier
     */
    public void setTokenId(final String tokenId) {
        this.tokenId = tokenId;
    }

    /**
     * Get veritrans credit card acquirer bank
     *
     * @return Veritrans credit card {@link CreditCard.Bank acquiring bank}
     */
    public Bank getAcquirerBank() {
        return acquirerBank;
    }

    /**
     * Set veritrans credit card acquirer bank
     *
     * @param acquirerBank Veritrans credit card {@link CreditCard.Bank acquiring bank}
     */
    public void setAcquirerBank(final Bank acquirerBank) {
        this.acquirerBank = acquirerBank;
    }

    /**
     * Get transaction installment term
     *
     * @return Transaction installment term (month)
     */
    public Integer getInstallmentTerm() {
        return installmentTerm;
    }

    /**
     * Set transaction installment term
     *
     * @param installmentTerm Transaction installment term (month)
     */
    public void setInstallmentTerm(final Integer installmentTerm) {
        this.installmentTerm = installmentTerm;
    }

    /**
     * Get list of merchant bin promo
     *
     * @return List of merchant bin promo
     */
    public List<String> getBins() {
        return bins;
    }

    /**
     * Set list of merchant bin promo
     *
     * @param bins List of merchant bin promo
     */
    public void setBins(final List<String> bins) {
        this.bins = bins;
    }

    /**
     * Get transaction type for current transaction
     *
     * @return {@link CreditCard.TransactionType Transaction type} for current transaction
     */
    public TransactionType getTransactionType() {
        return transactionType;
    }

    /**
     * Set transaction type for current transaction
     *
     * @param transactionType {@link CreditCard.TransactionType Transaction type} for current transaction
     */
    public void setTransactionType(final TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * Get save token id for one/two click feature config
     *
     * @return Save token id config
     */
    public Boolean getSaveTokenId() {
        return saveTokenId;
    }

    /**
     * Set save token id for one/two click feature config
     *
     * @param saveTokenId Save token id config
     */
    public void setSaveTokenId(final Boolean saveTokenId) {
        this.saveTokenId = saveTokenId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final CreditCard that = (CreditCard) o;

        if (acquirerBank != that.acquirerBank) return false;
        if (bins != null ? !bins.equals(that.bins) : that.bins != null) return false;
        if (installmentTerm != null ? !installmentTerm.equals(that.installmentTerm) : that.installmentTerm != null)
            return false;
        if (saveTokenId != null ? !saveTokenId.equals(that.saveTokenId) : that.saveTokenId != null) return false;
        if (tokenId != null ? !tokenId.equals(that.tokenId) : that.tokenId != null) return false;
        if (transactionType != that.transactionType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tokenId != null ? tokenId.hashCode() : 0;
        result = 31 * result + (acquirerBank != null ? acquirerBank.hashCode() : 0);
        result = 31 * result + (installmentTerm != null ? installmentTerm.hashCode() : 0);
        result = 31 * result + (bins != null ? bins.hashCode() : 0);
        result = 31 * result + (transactionType != null ? transactionType.hashCode() : 0);
        result = 31 * result + (saveTokenId != null ? saveTokenId.hashCode() : 0);
        return result;
    }
}
