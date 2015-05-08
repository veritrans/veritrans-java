package id.co.veritrans.mdk.v1.exception;

/**
 * Created by andes on 5/7/15.
 */
public class VtException extends Exception {

    public VtException() {
        super();
    }

    public VtException(String message) {
        super(message);
    }

    public VtException(String message, Throwable cause) {
        super(message, cause);
    }

    public VtException(Throwable cause) {
        super(cause);
    }

    protected VtException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
