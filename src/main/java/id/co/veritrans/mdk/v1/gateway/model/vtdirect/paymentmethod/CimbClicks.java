package id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod;

/**
 * Cimb clicks detail
 */
public class CimbClicks {

    private String description;

    /**
     * Cimb clicks constructor
     */
    public CimbClicks() {
    }

    /**
     * Cimb clicks constructor
     * @param description Cimb clicks transaction description
     */
    public CimbClicks(final String description) {
        this.description = description;
    }

    /**
     * Get cimb clicks transaction description
     * @return Cimb clicks transaction description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set cimb clicks transaction description
     * @param description Cimb clicks transaction description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final CimbClicks that = (CimbClicks) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return description != null ? description.hashCode() : 0;
    }
}
