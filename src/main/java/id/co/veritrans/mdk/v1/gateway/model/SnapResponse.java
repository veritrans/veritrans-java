package id.co.veritrans.mdk.v1.gateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * This Snap response is only for the token generation stage
 */
public class SnapResponse {
    @JsonProperty("token")
    private String token;

    @JsonProperty("error_messages")
    private List<String> errorMessages;

    @JsonProperty("status_code")
    private Integer statusCode;

    public SnapResponse() {
        this.errorMessages = new ArrayList<>();
    }

    /**
     * Snap token used for frontend integration
     * @return a String representing the token to use for checking out at front end
     */
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    /**
     * List of error messages if any
     * @return an array of error messages
     */
    public List<String> getErrorMessages() {
        return errorMessages;
    }

    /**
     * Transaction status code. Possible values: 200, 201, 202, 400, 404, 406, 500
     * @return an Integer representing status of the transaction
     */
    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SnapResponse that = (SnapResponse) o;

        if (token != null ? !token.equals(that.token) : that.token != null) return false;
        if (statusCode != null ? !statusCode.equals(that.statusCode) : that.statusCode != null) return false;
        return errorMessages.equals(that.errorMessages);
    }

    @Override
    public int hashCode() {
        int result = token != null ? token.hashCode() : 0;
        result = 31 * result + statusCode.hashCode();
        result = 31 * result + errorMessages.hashCode();
        return result;
    }
}
