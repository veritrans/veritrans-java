package id.co.veritrans.mdk.v1.sample.manager;

import id.co.veritrans.mdk.v1.sample.db.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * Created by gde on 5/20/15.
 */
@Component
public class SessionManagerFactory {

    @Autowired
    private ProductRepo productRepo;

    public SessionManager get(final HttpSession httpSession) {
        return new SessionManager(httpSession, productRepo);
    }
}
