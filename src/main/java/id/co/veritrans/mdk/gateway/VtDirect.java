package id.co.veritrans.mdk.gateway;

import id.co.veritrans.mdk.VtGateway;
import id.co.veritrans.mdk.gateway.model.VtDirectChargeParam;
import id.co.veritrans.mdk.gateway.model.VtDirectResponse;

/**
 * Created by gde on 4/30/15.
 */
public interface VtDirect {

    /**
     * Get the owner of this VtDirect instance.
     * @return
     */
    VtGateway getVtGateway();

    /**
     * Send a payment charge requests to Veritrans.
     * @param vtDirectChargeParam
     * @return
     */
    VtDirectResponse charge(VtDirectChargeParam vtDirectChargeParam);

    /**
     * Capture a previously authorized credit card charge request.
     * @param transactionId
     * @param amount
     * @return
     */
    VtDirectResponse capture(String transactionId, Long amount);

    /**
     * Get the status of a transaction using the orderId from the charge request.
     * @param orderId
     * @return
     */
    VtDirectResponse status(String orderId);

    /**
     * Approve a charge transaction which has <b>challenge</b> ChargeResult.
     * @param orderId
     * @return
     */
    VtDirectResponse approve(String orderId);
}
