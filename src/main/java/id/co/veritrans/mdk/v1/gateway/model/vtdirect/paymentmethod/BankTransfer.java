package id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod;

/**
 * Bank transfer detail
 */
public class BankTransfer {

    private Bank bank;

    /**
     * Enum bank for bank transfer
     */
    public static enum Bank {
        PERMATA("permata");

        private final String name;

        Bank(final String name) {
            this.name = name;
        }
    }

    /**
     * Bank transfer constructor
     */
    public BankTransfer() {
    }

    /**
     * Bank transfer constructor
     * @param bank Bank name
     */
    public BankTransfer(final Bank bank) {
        this.bank = bank;
    }

    /**
     * Get bank name
     * @return Bank name
     */
    public Bank getBank() {
        return bank;
    }

    /**
     * Set bank name
     * @param bank Bank name
     */
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
