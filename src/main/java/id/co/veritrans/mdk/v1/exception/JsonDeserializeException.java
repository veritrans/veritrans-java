package id.co.veritrans.mdk.v1.exception;

/**
 * Json deserialization exception
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
