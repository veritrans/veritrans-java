package id.co.veritrans.mdk.v1.gateway.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Custom Expiry to set an expiry time of payment with pending status for every transaction.
 */
public class CustomExpiry {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss Z", timezone = "GMT+07")
    private Date orderTime;
    private Integer expiryDuration;
    private Unit unit;

    /**
     * Enum unit for expiryDuration unit
     */
    public enum Unit {
        MINUTE("minute"),
        HOUR("hour"),
        DAY("day");

        private final String name;
        Unit(final String name) {
            this.name = name;
        }
    }

    /**
     * Custom Expiry constructor
     */
    public CustomExpiry() {

    }

    /**
     * Custom Expiry constructor
     *
     * @param orderTime Time when the order is created in merchant website
     * @param expiryDuration Time duration the payment will remain valid
     * @param unit Unit for expiryDuration. Valid values are minute,hour, or day
     */
    public CustomExpiry(final Date orderTime, final Integer expiryDuration, final Unit unit) {
        this.orderTime = orderTime;
        this.expiryDuration = expiryDuration;
        this.unit = unit;
    }

    /**
     * Get order time when order is created in merchant website
     * @return order time
     */
    public Date getOrderTime() {
        return orderTime;
    }

    /**
     * Set order time when order is created in merchant website
     * @param orderTime new order time
     */
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * Get Time duration the payment will remain valid
     * @return expiry duration
     */
    public Integer getExpiryDuration() {
        return expiryDuration;
    }

    /**
     * Set expiry duration order
     * @param expiryDuration new expiry duration
     */
    public void setExpiryDuration(Integer expiryDuration) {
        this.expiryDuration = expiryDuration;
    }

    /**
     * Get unit duration for expiryDuration. Valid values are minute,hour, or day
     * @return unit
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * Set unit duration for expiryDuration
     * @param unit new unit duration
     */
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final CustomExpiry that = (CustomExpiry) o;

        if (orderTime != that.orderTime) return false;
        if(expiryDuration != that.expiryDuration) return false;
        if(unit != that.unit) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderTime != null ? orderTime.hashCode() : 0;
        result = result + (expiryDuration != null ? expiryDuration.hashCode() : 0);
        result = result + (unit != null ? unit.hashCode() : 0);
        return result;
    }


}
