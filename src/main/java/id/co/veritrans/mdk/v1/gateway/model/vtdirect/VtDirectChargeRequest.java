package id.co.veritrans.mdk.v1.gateway.model.vtdirect;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.co.veritrans.mdk.v1.gateway.model.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Abstract of VtDirectChargeRequest
 */
public abstract class VtDirectChargeRequest extends AbstractVtRequest {

    /**
     * VtDirectChargeRequest default constructor.
     */
    public VtDirectChargeRequest() {
    }

    /**
     * AbstractVtRequest constructor.
     *
     * @param transactionDetails {@link id.co.veritrans.mdk.v1.gateway.model.TransactionDetails Transaction details}.
     * @param customerDetails       Transaction {@link id.co.veritrans.mdk.v1.gateway.model.CustomerDetails customer details}.
     */
    public VtDirectChargeRequest(final TransactionDetails transactionDetails, final CustomerDetails customerDetails) {
        super(transactionDetails);
        setCustomerDetails(customerDetails);
    }

    /**
     * AbstractVtRequest constructor
     * @param transactionDetails    {@link id.co.veritrans.mdk.v1.gateway.model.TransactionDetails Transaction details}.
     * @param itemDetails      List of {@link id.co.veritrans.mdk.v1.gateway.model.TransactionItem transaction item}.
     * @param customerDetails       Transaction {@link id.co.veritrans.mdk.v1.gateway.model.CustomerDetails customer details}.
     */
    public VtDirectChargeRequest(final TransactionDetails transactionDetails, final List<TransactionItem> itemDetails, final CustomerDetails customerDetails) {
        super(transactionDetails, itemDetails, customerDetails);
    }

    /**
     * Get transaction payment method
     * @return the payment method String representation.
     */
    @JsonProperty("payment_type")
    public abstract String getPaymentMethod();

    @Valid
    @NotNull
    @Override
    public CustomerDetails getCustomerDetails() {
        return super.getCustomerDetails();
    }
}
