package id.co.veritrans.mdk.v1.gateway.model.vtdirect;

import id.co.veritrans.mdk.v1.gateway.model.CustomerDetails;
import id.co.veritrans.mdk.v1.gateway.model.TransactionDetails;
import id.co.veritrans.mdk.v1.gateway.model.TransactionItem;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.BcaKlikpay;

import java.util.List;

/**
 * Veritrans VT-Direct BCA Klikpay request
 */
public class BcaKlikpayRequest extends VtDirectChargeRequest {

    private BcaKlikpay bcaKlikpay;

    /**
     * Get Bca klikpay payment method
     * @return Bca Klikpay String ("bca_klikpay)
     */
    @Override
    public String getPaymentMethod() {
        return "bca_klikpay";
    }

    /**
     * Bca klikpay request constructor
     */
    public BcaKlikpayRequest() {
    }

    /**
     * Bca klikpay request constructor
     * @param transactionDetails    {@link id.co.veritrans.mdk.v1.gateway.model.TransactionDetails Transaction details}
     * @param customerDetails       {@link id.co.veritrans.mdk.v1.gateway.model.CustomerDetails Customer details}
     * @param bcaKlikpay            {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.BcaKlikpay Bca klikpay} details
     */
    public BcaKlikpayRequest(TransactionDetails transactionDetails, CustomerDetails customerDetails, BcaKlikpay bcaKlikpay) {
        super(transactionDetails, customerDetails);
        this.bcaKlikpay = bcaKlikpay;
    }

    /**
     * Bca klikpay request constructor
     * @param transactionDetails    {@link id.co.veritrans.mdk.v1.gateway.model.TransactionDetails Transaction details}
     * @param transactionItems      List of {@link id.co.veritrans.mdk.v1.gateway.model.TransactionItem transaction item}
     * @param customerDetails       {@link id.co.veritrans.mdk.v1.gateway.model.CustomerDetails Customer details}
     * @param bcaKlikpay            {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.BcaKlikpay Bca klikpay} details
     */
    public BcaKlikpayRequest(TransactionDetails transactionDetails, List<TransactionItem> transactionItems, CustomerDetails customerDetails, BcaKlikpay bcaKlikpay) {
        super(transactionDetails, transactionItems, customerDetails);
        this.bcaKlikpay = bcaKlikpay;
    }

    /**
     * Get bca klikpay details
     * @return {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.BcaKlikpay Bca klikpay} details
     */
    public BcaKlikpay getBcaKlikpay() {
        return bcaKlikpay;
    }

    /**
     * Set bca klikpay details
     * @param bcaKlikpay {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.BcaKlikpay Bca klikpay} details
     */
    public void setBcaKlikpay(BcaKlikpay bcaKlikpay) {
        this.bcaKlikpay = bcaKlikpay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        BcaKlikpayRequest that = (BcaKlikpayRequest) o;

        if (bcaKlikpay != null ? !bcaKlikpay.equals(that.bcaKlikpay) : that.bcaKlikpay != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (bcaKlikpay != null ? bcaKlikpay.hashCode() : 0);
        return result;
    }
}
