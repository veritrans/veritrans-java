package id.co.veritrans.mdk.v1.exception;

/**
 * Created by andes on 5/8/15.
 */
public class VtConnectionException extends VtException {

    public VtConnectionException() {
        super();
    }

    public VtConnectionException(String message) {
        super(message);
    }

    public VtConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public VtConnectionException(Throwable cause) {
        super(cause);
    }

    protected VtConnectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
