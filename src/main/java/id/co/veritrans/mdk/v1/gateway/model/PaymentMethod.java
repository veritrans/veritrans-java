package id.co.veritrans.mdk.v1.gateway.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Veritrans payment method enum
 */
public enum PaymentMethod {
    CREDIT_CARD("credit_card"),
    MANDIRI_CLICKPAY("mandiri_clickpay"),
    CIMB_CLICKS("cimb_clicks"),
    BANK_TRANSFER("bank_transfer"),
    BRI_EPAY("bri_epay"),
    BCA_KLIKPAY("bca_klikpay");

    private final String name;

    PaymentMethod(final String paymentMethod) {
        this.name = paymentMethod;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
