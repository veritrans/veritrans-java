package id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * VT-Direct credit card full pan data model
 */
public class CreditCardFullPan {

    private String cardNumber;
    private String cardCvv;
    @JsonProperty("card_exp_month")
    private String cardExpiredMonth;
    @JsonProperty("card_exp_year")
    private String cardExpiredYear;
    private boolean secure;

    /**
     * Credit card full pan constructor
     *
     * @param cardNumber       Credit card number
     * @param cardCvv          Credit card cvv number
     * @param cardExpiredMonth Credit card expired month
     * @param cardExpiredYear  Credit card expired year
     */
    public CreditCardFullPan(String cardNumber, String cardCvv, String cardExpiredMonth, String cardExpiredYear) {
        this.cardNumber = cardNumber;
        this.cardCvv = cardCvv;
        this.cardExpiredMonth = cardExpiredMonth;
        this.cardExpiredYear = cardExpiredYear;
    }

    /**
     * Credit card full pan constructor
     *
     * @param cardNumber       Credit card number
     * @param cardCvv          Credit card cvv number
     * @param cardExpiredMonth Credit card expired month
     * @param cardExpiredYear  Credit card expired year
     * @param secure           Credit card enable 3D secure
     */
    public CreditCardFullPan(String cardNumber, String cardCvv, String cardExpiredMonth, String cardExpiredYear, boolean secure) {
        this.cardNumber = cardNumber;
        this.cardCvv = cardCvv;
        this.cardExpiredMonth = cardExpiredMonth;
        this.cardExpiredYear = cardExpiredYear;
        this.secure = secure;
    }

    /**
     * Get credit card number
     *
     * @return Credit card number
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Set credit card number
     *
     * @param cardNumber Credit card number
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Get credit card cvv number
     *
     * @return Credit card cvv number
     */
    public String getCardCvv() {
        return cardCvv;
    }

    /**
     * Set credit card cvv number
     *
     * @param cardCvv Credit card cvv number
     */
    public void setCardCvv(String cardCvv) {
        this.cardCvv = cardCvv;
    }

    /**
     * Get credit card expired month
     *
     * @return Credit card expired month
     */
    public String getCardExpiredMonth() {
        return cardExpiredMonth;
    }

    /**
     * Set credit card expired month
     *
     * @param cardExpiredMonth Credit card expired month
     */
    public void setCardExpiredMonth(String cardExpiredMonth) {
        this.cardExpiredMonth = cardExpiredMonth;
    }

    /**
     * Get credit card expired year
     *
     * @return Credit card expired year
     */
    public String getCardExpiredYear() {
        return cardExpiredYear;
    }

    /**
     * Set credit card expired year
     *
     * @param cardExpiredYear Credit card expired year
     */
    public void setCardExpiredYear(String cardExpiredYear) {
        this.cardExpiredYear = cardExpiredYear;
    }

    /**
     * Is credit card using 3D Secure?
     *
     * @return Credit card enable 3D secure
     */
    public boolean isSecure() {
        return secure;
    }

    /**
     * Set credit card enable 3D secure
     *
     * @param secure Credit card enable 3D secure
     */
    public void setSecure(boolean secure) {
        this.secure = secure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreditCardFullPan that = (CreditCardFullPan) o;

        if (secure != that.secure) return false;
        if (cardCvv != null ? !cardCvv.equals(that.cardCvv) : that.cardCvv != null) return false;
        if (cardExpiredMonth != null ? !cardExpiredMonth.equals(that.cardExpiredMonth) : that.cardExpiredMonth != null)
            return false;
        if (cardExpiredYear != null ? !cardExpiredYear.equals(that.cardExpiredYear) : that.cardExpiredYear != null)
            return false;
        if (cardNumber != null ? !cardNumber.equals(that.cardNumber) : that.cardNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cardNumber != null ? cardNumber.hashCode() : 0;
        result = 31 * result + (cardCvv != null ? cardCvv.hashCode() : 0);
        result = 31 * result + (cardExpiredMonth != null ? cardExpiredMonth.hashCode() : 0);
        result = 31 * result + (cardExpiredYear != null ? cardExpiredYear.hashCode() : 0);
        result = 31 * result + (secure ? 1 : 0);
        return result;
    }
}
