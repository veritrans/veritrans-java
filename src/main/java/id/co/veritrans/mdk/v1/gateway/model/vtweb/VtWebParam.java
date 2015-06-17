package id.co.veritrans.mdk.v1.gateway.model.vtweb;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.co.veritrans.mdk.v1.gateway.model.PaymentMethod;

import java.util.Arrays;

/**
 * Created by gde on 5/20/15.
 */
public class VtWebParam {

    private PaymentMethod[] enabledPayments;
    @JsonProperty("credit_card_3d_secure")
    private Boolean creditCardUse3dSecure;
    private String[] creditCardBins;
    @JsonProperty("payment_options")
    private PaymentOption paymentOption;
    private String finishRedirectUrl;
    private String unfinishRedirectUrl;
    private String errorRedirectUrl;

    public VtWebParam() {
    }

    public VtWebParam(final PaymentMethod[] enabledPayments, final Boolean creditCardUse3dSecure, final String[] creditCardBins, final PaymentOption paymentOption, final String finishRedirectUrl, final String unfinishRedirectUrl, final String errorRedirectUrl) {
        this.enabledPayments = enabledPayments;
        this.creditCardUse3dSecure = creditCardUse3dSecure;
        this.creditCardBins = creditCardBins;
        this.paymentOption = paymentOption;
        this.finishRedirectUrl = finishRedirectUrl;
        this.unfinishRedirectUrl = unfinishRedirectUrl;
        this.errorRedirectUrl = errorRedirectUrl;
    }

    public PaymentMethod[] getEnabledPayments() {
        return enabledPayments;
    }

    public void setEnabledPayments(final PaymentMethod[] enabledPayments) {
        this.enabledPayments = enabledPayments;
    }

    public Boolean getCreditCardUse3dSecure() {
        return creditCardUse3dSecure;
    }

    public void setCreditCardUse3dSecure(final Boolean creditCardUse3dSecure) {
        this.creditCardUse3dSecure = creditCardUse3dSecure;
    }

    public String[] getCreditCardBins() {
        return creditCardBins;
    }

    public void setCreditCardBins(final String[] creditCardBins) {
        this.creditCardBins = creditCardBins;
    }

    public PaymentOption getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(final PaymentOption paymentOption) {
        this.paymentOption = paymentOption;
    }

    public String getFinishRedirectUrl() {
        return finishRedirectUrl;
    }

    public void setFinishRedirectUrl(final String finishRedirectUrl) {
        this.finishRedirectUrl = finishRedirectUrl;
    }

    public String getUnfinishRedirectUrl() {
        return unfinishRedirectUrl;
    }

    public void setUnfinishRedirectUrl(final String unfinishRedirectUrl) {
        this.unfinishRedirectUrl = unfinishRedirectUrl;
    }

    public String getErrorRedirectUrl() {
        return errorRedirectUrl;
    }

    public void setErrorRedirectUrl(final String errorRedirectUrl) {
        this.errorRedirectUrl = errorRedirectUrl;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final VtWebParam that = (VtWebParam) o;

        if (!Arrays.equals(creditCardBins, that.creditCardBins)) return false;
        if (creditCardUse3dSecure != null ? !creditCardUse3dSecure.equals(that.creditCardUse3dSecure) : that.creditCardUse3dSecure != null)
            return false;
        if (!Arrays.equals(enabledPayments, that.enabledPayments)) return false;
        if (errorRedirectUrl != null ? !errorRedirectUrl.equals(that.errorRedirectUrl) : that.errorRedirectUrl != null)
            return false;
        if (finishRedirectUrl != null ? !finishRedirectUrl.equals(that.finishRedirectUrl) : that.finishRedirectUrl != null)
            return false;
        if (paymentOption != null ? !paymentOption.equals(that.paymentOption) : that.paymentOption != null)
            return false;
        if (unfinishRedirectUrl != null ? !unfinishRedirectUrl.equals(that.unfinishRedirectUrl) : that.unfinishRedirectUrl != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = enabledPayments != null ? Arrays.hashCode(enabledPayments) : 0;
        result = 31 * result + (creditCardUse3dSecure != null ? creditCardUse3dSecure.hashCode() : 0);
        result = 31 * result + (creditCardBins != null ? Arrays.hashCode(creditCardBins) : 0);
        result = 31 * result + (paymentOption != null ? paymentOption.hashCode() : 0);
        result = 31 * result + (finishRedirectUrl != null ? finishRedirectUrl.hashCode() : 0);
        result = 31 * result + (unfinishRedirectUrl != null ? unfinishRedirectUrl.hashCode() : 0);
        result = 31 * result + (errorRedirectUrl != null ? errorRedirectUrl.hashCode() : 0);
        return result;
    }
}
