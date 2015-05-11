package id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod;

import javax.validation.constraints.Size;

/**
 * Mandiri clickpay model
 */
public class MandiriClickpay {

    private String cardNumber;
    @Size(min = 10, max = 10)
    private String input1;
    private String input2;
    @Size(min = 5, max = 5)
    private String input3;
    private String token;

    /**
     * Mandiri clickpay constructor
     */
    public MandiriClickpay() {
    }

    /**
     * Mandiri clickpay constructor
     *
     * @param cardNumber Mandiri clickpay card number
     * @param input1     Mandiri clickpay input1 (Last 10 digit of mandiri debit card number)
     * @param input2     Mandiri clickpay input2 (Transaction value)
     * @param input3     Mandiri clickpay input3 (Request number)
     * @param token      Mandiri token response from
     */
    public MandiriClickpay(final String cardNumber, final String input1, final String input2, final String input3, final String token) {
        this.cardNumber = cardNumber;
        this.input1 = input1;
        this.input2 = input2;
        this.input3 = input3;
        this.token = token;
    }

    /**
     * Get mandiri clickpay card number
     *
     * @return Mandiri clickpay card number
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Set mandiri clickpay card number
     *
     * @param cardNumber Mandiri clickpay card number
     */
    public void setCardNumber(final String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Get mandiri clickpay input1 (Last 10 digit of mandiri debit card number)
     *
     * @return Mandiri clickpay input1 (Last 10 digit of mandiri debit card number)
     */
    public String getInput1() {
        return input1;
    }

    /**
     * Set mandiri clickpay input1 (Last 10 digit of mandiri debit card number)
     *
     * @param input1 Mandiri clickpay input1 (Last 10 digit of mandiri debit card number)
     */
    public void setInput1(final String input1) {
        this.input1 = input1;
    }

    /**
     * Get mandiri clickpay input2 (Transaction value)
     *
     * @return Mandiri clickpay input2 (Transaction value)
     */
    public String getInput2() {
        return input2;
    }

    /**
     * Set mandiri clickpay input2 (Transaction value)
     *
     * @param input2 Mandiri clickpay input2 (Transaction value)
     */
    public void setInput2(final String input2) {
        this.input2 = input2;
    }

    /**
     * Get mandiri clickpay input3 (Request number)
     *
     * @return Mandiri clickpay input3 (Request number)
     */
    public String getInput3() {
        return input3;
    }

    /**
     * Set mandiri clickpay input3 (Request number)
     *
     * @param input3 Mandiri clickpay input3 (Request number)
     */
    public void setInput3(final String input3) {
        this.input3 = input3;
    }

    /**
     * Get mandiri token response from
     *
     * @return Mandiri token response from
     */
    public String getToken() {
        return token;
    }

    /**
     * Set mandiri token response from
     *
     * @param token Mandiri token response from
     */
    public void setToken(final String token) {
        this.token = token;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final MandiriClickpay that = (MandiriClickpay) o;

        if (cardNumber != null ? !cardNumber.equals(that.cardNumber) : that.cardNumber != null) return false;
        if (input1 != null ? !input1.equals(that.input1) : that.input1 != null) return false;
        if (input2 != null ? !input2.equals(that.input2) : that.input2 != null) return false;
        if (input3 != null ? !input3.equals(that.input3) : that.input3 != null) return false;
        if (token != null ? !token.equals(that.token) : that.token != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cardNumber != null ? cardNumber.hashCode() : 0;
        result = 31 * result + (input1 != null ? input1.hashCode() : 0);
        result = 31 * result + (input2 != null ? input2.hashCode() : 0);
        result = 31 * result + (input3 != null ? input3.hashCode() : 0);
        result = 31 * result + (token != null ? token.hashCode() : 0);
        return result;
    }
}
