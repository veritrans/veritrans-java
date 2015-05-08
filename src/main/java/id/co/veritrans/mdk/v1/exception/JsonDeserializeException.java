package id.co.veritrans.mdk.v1.exception;

/**
 * Created by gde on 5/7/15.
 */
public class JsonDeserializeException extends VtException {

    public JsonDeserializeException() {
    }

    public JsonDeserializeException(final String message) {
        super(message);
    }

    public JsonDeserializeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public JsonDeserializeException(final Throwable cause) {
        super(cause);
    }

    public JsonDeserializeException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
