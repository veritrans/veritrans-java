package id.co.veritrans.mdk.gateway.model.paymentmethod;

/**
 * Created by gde on 5/4/15.
 */
public class MandiriClickpay {

    private String cardNumber;
    private String input1;
    private String input2;
    private String input3;
    private String token;

    public MandiriClickpay() {
    }

    public MandiriClickpay(final String cardNumber, final String input1, final String input2, final String input3, final String token) {
        this.cardNumber = cardNumber;
        this.input1 = input1;
        this.input2 = input2;
        this.input3 = input3;
        this.token = token;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(final String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getInput1() {
        return input1;
    }

    public void setInput1(final String input1) {
        this.input1 = input1;
    }

    public String getInput2() {
        return input2;
    }

    public void setInput2(final String input2) {
        this.input2 = input2;
    }

    public String getInput3() {
        return input3;
    }

    public void setInput3(final String input3) {
        this.input3 = input3;
    }

    public String getToken() {
        return token;
    }

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
