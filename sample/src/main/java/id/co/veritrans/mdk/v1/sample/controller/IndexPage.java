package id.co.veritrans.mdk.v1.sample.controller;

import id.co.veritrans.mdk.v1.gateway.VtDirect;
import id.co.veritrans.mdk.v1.sample.manager.VtPaymentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by gde on 5/14/15.
 */
@Controller
public class IndexPage {

    @Autowired
    private VtPaymentManager vtPaymentManager;

    @RequestMapping(value="/index", method= RequestMethod.GET)
    public String index() {
        VtDirect vtDirect = vtPaymentManager.getVtGatewayFactory().vtDirect();
        //yay we can make payment!

        return "index"; //return nama view nya, liat: src/main/resources/index.html amd layout.html
    }
}
