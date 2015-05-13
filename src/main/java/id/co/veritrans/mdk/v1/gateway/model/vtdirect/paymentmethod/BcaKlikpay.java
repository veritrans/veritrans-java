package id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod;

/**
 * Bca Klikpay detail
 */
public class BcaKlikpay {

    private int type;
    private long miscFee;
    private String description;

    /**
     * Bca klikpay constructor
     */
    public BcaKlikpay() {
    }

    /**
     * Bca klikpay constructor
     * @param type          Bca klikpay type
     * @param miscFee       Bca klikpay misc fee
     * @param description   Bca klikpay transaction description
     */
    public BcaKlikpay(int type, long miscFee, String description) {
        this.type = type;
        this.miscFee = miscFee;
        this.description = description;
    }

    /**
     * Get bca klikpay type
     * @return Bca klikpay type
     */
    public int getType() {
        return type;
    }

    /**
     * Set bca klikpay type
     * @param type Bca klikpay type
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Get bca klikpay misc fee
     * @return Bca klikpay misc fee
     */
    public long getMiscFee() {
        return miscFee;
    }

    /**
     * Set bca klikpay misc fee
     * @param miscFee Bca klikpay misc fee
     */
    public void setMiscFee(long miscFee) {
        this.miscFee = miscFee;
    }

    /**
     * Get bca klikpay transaction description
     * @return Bca klikpay transaction description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set bca klikpay transaction description
     * @param description Bca klikpay transaction description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BcaKlikpay that = (BcaKlikpay) o;

        if (miscFee != that.miscFee) return false;
        if (type != that.type) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type;
        result = 31 * result + (int) (miscFee ^ (miscFee >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
