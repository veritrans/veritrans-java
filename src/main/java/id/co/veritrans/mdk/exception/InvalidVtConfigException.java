package id.co.veritrans.mdk.exception;

/**
 * Created by andes on 5/7/15.
 */
public class InvalidVtConfigException extends VtException {
    public InvalidVtConfigException() {
        super();
    }

    public InvalidVtConfigException(String message) {
        super(message);
    }

    public InvalidVtConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidVtConfigException(Throwable cause) {
        super(cause);
    }

    protected InvalidVtConfigException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
