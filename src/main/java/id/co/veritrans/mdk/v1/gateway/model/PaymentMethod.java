package id.co.veritrans.mdk.v1.gateway.model;

/**
 * Created by gde on 5/4/15.
 */
public enum PaymentMethod {
    CREDIT_CARD("credit_card"),
    MANDIRI_CLICKPAY("mandiri_clickpay"),
    CIMB_CLICKS("cimb_clicks"),
    BANK_TRANSFER("bank_transfer"),
    BRI_EPAY("bri_epay");

    private final String paymentMethod;

    PaymentMethod(final String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
