package id.co.veritrans.mdk.v1.gateway.model.vtweb;

import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.CreditCard;

/**
 * Set the credit card option such as the channel (eg. MIGS).
 */
public class CreditCardOptions {
    private CreditCard.Channel channel;
    private CreditCard.Bank bank;

    public static CreditCardOptions buildOptions() {
        return new CreditCardOptions();
    }

    public CreditCard.Channel getChannel() {
        return channel;
    }

    public CreditCardOptions setChannel(CreditCard.Channel channel) {
        this.channel = channel;
        return this;
    }

    public CreditCard.Bank getBank() {
        return bank;
    }

    public CreditCardOptions setBank(CreditCard.Bank bank) {
        this.bank = bank;
        return this;
    }
}
