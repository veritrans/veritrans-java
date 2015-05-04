package id.co.veritrans.mdk.gateway.model.paymentmethod;

/**
 * Created by gde on 5/4/15.
 */
public class BankTransfer {

    private String description;

    public BankTransfer() {
    }

    public BankTransfer(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final BankTransfer that = (BankTransfer) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return description != null ? description.hashCode() : 0;
    }
}
