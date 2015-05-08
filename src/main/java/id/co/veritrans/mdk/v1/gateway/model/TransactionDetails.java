package id.co.veritrans.mdk.v1.gateway.model;

/**
 * Merchant transaction details
 */
public class TransactionDetails {

    private String orderId;
    private Long grossAmount;

    /**
     * Transaction details constructor
     */
    public TransactionDetails() {
    }

    /**
     * Transaction details constructor
     * @param orderId       Transaction order id
     * @param grossAmount   Transaction total gross amount
     */
    public TransactionDetails(final String orderId, final Long grossAmount) {
        this.orderId = orderId;
        this.grossAmount = grossAmount;
    }

    /**
     * Get transaction order id
     * @return Transaction order id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Set transaction order id
     * @param orderId Transaction order id
     */
    public void setOrderId(final String orderId) {
        this.orderId = orderId;
    }

    /**
     * Get transaction total gross amount
     * @return Transaction total gross amount
     */
    public Long getGrossAmount() {
        return grossAmount;
    }

    /**
     * Set transaction total gross amount
     * @param grossAmount Transaction total gross amount
     */
    public void setGrossAmount(final Long grossAmount) {
        this.grossAmount = grossAmount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final TransactionDetails that = (TransactionDetails) o;

        if (grossAmount != null ? !grossAmount.equals(that.grossAmount) : that.grossAmount != null) return false;
        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (grossAmount != null ? grossAmount.hashCode() : 0);
        return result;
    }
}
