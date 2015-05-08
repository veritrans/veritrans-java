package id.co.veritrans.mdk.v1.exception;

/**
 * Created by gde on 5/8/15.
 */
public class RestClientException extends VtException {

    public RestClientException() {
    }

    public RestClientException(final String message) {
        super(message);
    }

    public RestClientException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public RestClientException(final Throwable cause) {
        super(cause);
    }

    public RestClientException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
