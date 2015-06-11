package id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod;

/**
 * Klik BCA details
 */
public class KlikBca {

    private String userId;
    private String description;

    /**
     * KlikBca constructor
     */
    public KlikBca() {
    }

    /**
     * KlikBca constructor
     * @param userId        Klik bca user id
     * @param description   Klik bca transaction description
     */
    public KlikBca(String userId, String description) {
        this.userId = userId;
        this.description = description;
    }

    /**
     * Get klik bca user id
     * @return Klik bca user id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Set klik bca user id
     * @param userId Klik bca user id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Get klik bca transaction description
     * @return Klik bca transaction description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set klik bca transaction description
     * @param description Klik bca transaction description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KlikBca klikBca = (KlikBca) o;

        if (description != null ? !description.equals(klikBca.description) : klikBca.description != null) return false;
        if (userId != null ? !userId.equals(klikBca.userId) : klikBca.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
