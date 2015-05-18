package id.co.veritrans.mdk.v1.sample.util;

import javax.servlet.http.HttpSession;

/**
 * Created by gde on 5/18/15.
 */
public class SessionUtil {

    public static <T> T getAttribute(final HttpSession session, final String key, final T defaultValue) {
        final T ret = (T) session.getAttribute(key);
        if (ret == null) {
            return setAttribute(session, key, defaultValue);
        }
        return ret;
    }

    public static <T> T setAttribute(final HttpSession session, final String key, final T value) {
        session.setAttribute(key, value);
        return value;
    }
}
