package id.co.veritrans.mdk.gateway.model.paymentmethod;

import java.util.List;

/**
 * Created by gde on 5/4/15.
 */
public class CreditCard {

    private String tokenId;
    private Bank acquirerBank;
    private Integer installmentTerm;
    private List<String> bins;
    private TransactionType transactionType;
    private Boolean saveTokenId;

    public static enum Bank {
        BNI("bni"),
        MANDIRI("mandiri"),
        CIMB("cimb");

        private final String name;

        Bank(final String name) {
            this.name = name;
        }
    }

    public static enum TransactionType {
        AUTHORIZE("authorize");

        private final String name;

        TransactionType(final String name) {
            this.name = name;
        }
    }

    public CreditCard() {
    }

    public CreditCard(final String tokenId, final Bank acquirerBank, final Integer installmentTerm, final List<String> bins, final TransactionType transactionType, final Boolean saveTokenId) {
        this.tokenId = tokenId;
        this.acquirerBank = acquirerBank;
        this.installmentTerm = installmentTerm;
        this.bins = bins;
        this.transactionType = transactionType;
        this.saveTokenId = saveTokenId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(final String tokenId) {
        this.tokenId = tokenId;
    }

    public Bank getAcquirerBank() {
        return acquirerBank;
    }

    public void setAcquirerBank(final Bank acquirerBank) {
        this.acquirerBank = acquirerBank;
    }

    public Integer getInstallmentTerm() {
        return installmentTerm;
    }

    public void setInstallmentTerm(final Integer installmentTerm) {
        this.installmentTerm = installmentTerm;
    }

    public List<String> getBins() {
        return bins;
    }

    public void setBins(final List<String> bins) {
        this.bins = bins;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(final TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Boolean getSaveTokenId() {
        return saveTokenId;
    }

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
