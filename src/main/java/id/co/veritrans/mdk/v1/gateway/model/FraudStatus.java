package id.co.veritrans.mdk.v1.gateway.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by gde on 5/5/15.
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
