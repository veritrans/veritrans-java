package id.co.veritrans.mdk.v1.gateway.model.vtdirect;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.co.veritrans.mdk.v1.gateway.model.CustomerDetails;
import id.co.veritrans.mdk.v1.gateway.model.TransactionDetails;
import id.co.veritrans.mdk.v1.gateway.model.TransactionItem;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.KlikBca;

import java.util.List;

/**
 * Veritrans VT-Direct Klik BCA request
 */
public class KlikBcaRequest extends VtDirectChargeRequest {

    @JsonProperty(value = "bca_klikbca")
    private KlikBca klikBca;

    /**
     * KlikBcaRequest constructor
     */
    public KlikBcaRequest() {
    }

    /**
     * KlikBcaRequest constructor
     *
     * @param transactionDetails {@link id.co.veritrans.mdk.v1.gateway.model.TransactionDetails Transaction details}
     * @param customerDetails    Transaction {@link id.co.veritrans.mdk.v1.gateway.model.CustomerDetails customer details}
     * @param klikBca            {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.KlikBca Klik bca} details
     */
    public KlikBcaRequest(TransactionDetails transactionDetails, CustomerDetails customerDetails, KlikBca klikBca) {
        super(transactionDetails, customerDetails);
        this.klikBca = klikBca;
    }

    /**
     * KlikBcaRequest constructor
     *
     * @param transactionDetails {@link id.co.veritrans.mdk.v1.gateway.model.TransactionDetails Transaction details}
     * @param transactionItems   List of {@link id.co.veritrans.mdk.v1.gateway.model.TransactionItem transaction item}
     * @param customerDetails    Transaction {@link id.co.veritrans.mdk.v1.gateway.model.CustomerDetails customer details}
     * @param klikBca            {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.KlikBca Klik bca} details
     */
    public KlikBcaRequest(TransactionDetails transactionDetails, List<TransactionItem> transactionItems, CustomerDetails customerDetails, KlikBca klikBca) {
        super(transactionDetails, transactionItems, customerDetails);
        this.klikBca = klikBca;
    }

    /**
     * Get klik BCA payment method
     * @return Klik Bca String (klik_bca)
     */
    @Override
    public String getPaymentMethod() {
        return "bca_klikbca";
    }

    /**
     * Get klik bca details
     * @return {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.KlikBca Klik bca} details
     */
    public KlikBca getKlikBca() {
        return klikBca;
    }

    /**
     * Set klik bca details
     * @param klikBca {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.KlikBca Klik bca} details
     */
    public void setKlikBca(KlikBca klikBca) {
        this.klikBca = klikBca;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        KlikBcaRequest that = (KlikBcaRequest) o;

        if (klikBca != null ? !klikBca.equals(that.klikBca) : that.klikBca != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (klikBca != null ? klikBca.hashCode() : 0);
        return result;
    }
}
