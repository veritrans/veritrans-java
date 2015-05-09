package id.co.veritrans.mdk.v1.gateway.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by gde on 5/5/15.
 */
public enum TransactionStatus {
    AUTHORIZED("authorize"),
    CAPTURED("capture"),
    SETTLED("settlement"),
    PENDING("pending"),
    CANCELLED("cancel"),
    DENIED("deny");

    private final String name;

    TransactionStatus(final String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
