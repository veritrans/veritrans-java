package id.co.veritrans.mdk.v1.gateway.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Veritrans transaction status enum
 */
public enum TransactionStatus {
    AUTHORIZED("authorize"),
    CAPTURED("capture"),
    SETTLED("settlement"),
    PENDING("pending"),
    CANCELLED("cancel"),
    DENIED("deny"),
    EXPIRED("expire"),
    FAILED("failure");

    private final String name;

    TransactionStatus(final String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
