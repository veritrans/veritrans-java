package id.co.veritrans.mdk.gateway.model.vtdirect.paymentmethod;

/**
 * Created by gde on 5/4/15.
 */
public class BankTransfer {

    private Bank bank;

    public static enum Bank {
        PERMATA("permata");

        private final String name;

        Bank(final String name) {
            this.name = name;
        }
    }

    public BankTransfer() {
    }

    public BankTransfer(final Bank bank) {
        this.bank = bank;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(final Bank bank) {
        this.bank = bank;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final BankTransfer that = (BankTransfer) o;

        if (bank != that.bank) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return bank != null ? bank.hashCode() : 0;
    }
}
