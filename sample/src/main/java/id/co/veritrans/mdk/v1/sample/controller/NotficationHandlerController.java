package id.co.veritrans.mdk.v1.sample.controller;

import id.co.veritrans.mdk.v1.exception.RestClientException;
import id.co.veritrans.mdk.v1.gateway.model.TransactionStatus;
import id.co.veritrans.mdk.v1.gateway.model.VtResponse;
import id.co.veritrans.mdk.v1.sample.controller.checkout.AbstractVtDirectController;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by gde on 5/18/15.
 */
@RestController
public class NotficationHandlerController extends AbstractVtDirectController {

    @RequestMapping(value = "/notification", method = RequestMethod.POST)
    private String handleNotification(@RequestBody Map<String, String> response) throws RestClientException, UnsupportedEncodingException {
        /* Get status for the callback notification to get latest transaction status originally from veritrans */
        VtResponse res = vtDirect.status(response.get("order_id"));

        if (res.getTransactionStatus() == TransactionStatus.SETTLED) {
            // Handle Settled transaction (ex. update DB)
        } else {
            // Handle for another transaction status
        }

        return res.getTransactionStatus().getName();
    }
}
