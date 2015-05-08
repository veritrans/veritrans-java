package id.co.veritrans.mdk.v1.exception;

/**
 * Created by andes on 5/8/15.
 */
public class NetworkException extends VtException {

    public NetworkException() {
        super();
    }

    public NetworkException(String message) {
        super(message);
    }

    public NetworkException(String message, Throwable cause) {
        super(message, cause);
    }

    public NetworkException(Throwable cause) {
        super(cause);
    }

    protected NetworkException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
