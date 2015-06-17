package id.co.veritrans.mdk.v1.gateway.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by andes on 6/17/15.
 */
public enum PaymentMethod {
    CREDIT_CARD("credit_card"),
    BANK_TRANSFER("bank_transfer"),
    CIMB_CLICKS("cimb_clicks"),
    MANDIRI_CLICKPAY("mandiri_clickpay"),
    BRI_EPAY("bri_epay"),
    TELKOMSEL_CASH("telkomsel_cash"),
    XL_TUNAI("xl_tunai"),
    BBM_MONEY("bbm_money"),
    ECHANNEL("echannel"),
    CSTORE("cstore"),
    KLIK_BCA("bca_klikbca"),
    BCA_KLIKPAY("bca_klikpay");

    private final String name;

    PaymentMethod(final String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
