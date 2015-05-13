package id.co.veritrans.mdk.v1.gateway.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Veritrans fraud status enum
 */
public enum FraudStatus {
    ACCEPTED("accept"),
    DENIED("deny"),
    CHALLENGE("challenge");

    private final String name;

    FraudStatus(final String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
