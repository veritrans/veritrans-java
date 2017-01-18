package id.co.veritrans.mdk.v1.gateway.model.vtdirect;

import id.co.veritrans.mdk.v1.gateway.model.CustomExpiry;
import id.co.veritrans.mdk.v1.gateway.model.CustomerDetails;
import id.co.veritrans.mdk.v1.gateway.model.TransactionDetails;
import id.co.veritrans.mdk.v1.gateway.model.TransactionItem;
import id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.BankTransfer;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Bank transfer request
 */
public class BankTransferRequest extends VtDirectChargeRequest {

    @Valid
    @NotNull
    private BankTransfer bankTransfer;
    private CustomExpiry customExpiry;
    /**
     * Bank transfer request constructor
     */
    public BankTransferRequest() {
    }

    /**
     * Bank transfer request constructor
     *
     * @param transactionDetails    {@link id.co.veritrans.mdk.v1.gateway.model.TransactionDetails Transaction details}
     * @param transactionItems      List of {@link id.co.veritrans.mdk.v1.gateway.model.TransactionItem transaction item}
     * @param customerDetails       {@link id.co.veritrans.mdk.v1.gateway.model.CustomerDetails Customer details}
     * @param bankTransfer          {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.BankTransfer Bank transfer} detail
     */
    public BankTransferRequest(final TransactionDetails transactionDetails, final List<TransactionItem> transactionItems, final CustomerDetails customerDetails, final BankTransfer bankTransfer) {
        super(transactionDetails, transactionItems, customerDetails);
        this.bankTransfer = bankTransfer;
    }

    /**
     * Bank transfer request constructor
     *
     * @param transactionDetails    {@link id.co.veritrans.mdk.v1.gateway.model.TransactionDetails Transaction details}
     * @param transactionItems      List of {@link id.co.veritrans.mdk.v1.gateway.model.TransactionItem transaction item}
     * @param customerDetails       {@link id.co.veritrans.mdk.v1.gateway.model.CustomerDetails Customer details}
     * @param bankTransfer          {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.BankTransfer Bank transfer} detail
     * @param customExpiry          {@link id.co.veritrans.mdk.v1.gateway.model.CustomExpiry Custom expiry}
     */
    public BankTransferRequest(final TransactionDetails transactionDetails, final List<TransactionItem> transactionItems, final CustomerDetails customerDetails, final BankTransfer bankTransfer, final CustomExpiry customExpiry) {
        super(transactionDetails, transactionItems, customerDetails);
        this.bankTransfer = bankTransfer;
        this.customExpiry = customExpiry;
    }

    /**
     * Get bank transfer payment method
     * @return Bank Transfer String (bank_transfer)
     */
    @Override
    public String getPaymentMethod() {
        return "bank_transfer";
    }

    /**
     * Get bank transfer detail
     * @return {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.BankTransfer Bank transfer} detail
     */
    public BankTransfer getBankTransfer() {
        return bankTransfer;
    }

    /**
     * Set bank transfer detail
     * @param bankTransfer {@link id.co.veritrans.mdk.v1.gateway.model.vtdirect.paymentmethod.BankTransfer Bank transfer} detail
     */
    public void setBankTransfer(final BankTransfer bankTransfer) {
        this.bankTransfer = bankTransfer;
    }

    public CustomExpiry getCustomExpiry() {
        return customExpiry;
    }

    public void setCustomExpiry(CustomExpiry customExpiry) {
        this.customExpiry = customExpiry;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        final BankTransferRequest that = (BankTransferRequest) o;

        if (bankTransfer != null ? !bankTransfer.equals(that.bankTransfer) : that.bankTransfer != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (bankTransfer != null ? bankTransfer.hashCode() : 0);
        return result;
    }
}
