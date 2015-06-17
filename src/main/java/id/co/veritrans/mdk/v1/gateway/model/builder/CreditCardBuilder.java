package id.co.veritrans.mdk.v1.gateway.model.builder;

import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.CreditCard;

import java.util.List;

public class CreditCardBuilder {
    private String cardToken;
    private CreditCard.Bank acquirerBank;
    private Integer installmentTerm = null;
    private List<String> bins = null;
    private CreditCard.TransactionType transactionType;
    private Boolean saveCardToken = null;
    private String fraudSector = null;

    public CreditCardBuilder setCardToken(String cardToken) {
        this.cardToken = cardToken;
        return this;
    }

    public CreditCardBuilder setAcquirerBank(CreditCard.Bank acquirerBank) {
        this.acquirerBank = acquirerBank;
        return this;
    }

    public CreditCardBuilder setInstallmentTerm(Integer installmentTerm) {
        this.installmentTerm = installmentTerm;
        return this;
    }

    public CreditCardBuilder setBins(List<String> bins) {
        this.bins = bins;
        return this;
    }

    public CreditCardBuilder setTransactionType(CreditCard.TransactionType transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public CreditCardBuilder setSaveCardToken(Boolean saveCardToken) {
        this.saveCardToken = saveCardToken;
        return this;
    }

    public CreditCardBuilder setFraudSector(String fraudSector) {
        this.fraudSector = fraudSector;
        return this;
    }

    public CreditCard createCreditCard() {
        return new CreditCard(cardToken, acquirerBank, installmentTerm, bins, transactionType, saveCardToken, fraudSector);
    }
}