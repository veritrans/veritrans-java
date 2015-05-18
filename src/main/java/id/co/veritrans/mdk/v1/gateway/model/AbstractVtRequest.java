package id.co.veritrans.mdk.v1.gateway.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Abstract VtRequest
 */
public abstract class AbstractVtRequest implements VtRequest {

    @Valid
    @NotNull
    private TransactionDetails transactionDetails;
    private List<TransactionItem> itemDetails;

    /**
     * AbstractVtRequest constructor
     */
    public AbstractVtRequest() {
    }

    /**
     * AbstractVtRequest constructor
     * @param transactionDetails    {@link id.co.veritrans.mdk.v1.gateway.model.TransactionDetails Transaction details}
     * @param itemDetails      List of {@link id.co.veritrans.mdk.v1.gateway.model.TransactionItem transaction item}
     */
    public AbstractVtRequest(final TransactionDetails transactionDetails, final List<TransactionItem> itemDetails) {
        this.transactionDetails = transactionDetails;
        this.itemDetails = itemDetails;
    }

    /**
     * Get transaction details
     * @return {@link id.co.veritrans.mdk.v1.gateway.model.TransactionDetails Transaction details}
     */
    public TransactionDetails getTransactionDetails() {
        return transactionDetails;
    }

    /**
     * Set transaction details
     * @param transactionDetails {@link id.co.veritrans.mdk.v1.gateway.model.TransactionDetails Transaction details}
     */
    public void setTransactionDetails(final TransactionDetails transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    /**
     * Get list of transaction item
     * @return List of {@link id.co.veritrans.mdk.v1.gateway.model.TransactionItem transaction item}
     */
    public List<TransactionItem> getItemDetails() {
        return itemDetails;
    }

    /**
     * Set list of transaction item
     * @param itemDetails List of {@link id.co.veritrans.mdk.v1.gateway.model.TransactionItem transaction item}
     */
    public void setItemDetails(final List<TransactionItem> itemDetails) {
        this.itemDetails = itemDetails;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final AbstractVtRequest vtRequest = (AbstractVtRequest) o;

        if (transactionDetails != null ? !transactionDetails.equals(vtRequest.transactionDetails) : vtRequest.transactionDetails != null)
            return false;
        if (itemDetails != null ? !itemDetails.equals(vtRequest.itemDetails) : vtRequest.itemDetails != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = transactionDetails != null ? transactionDetails.hashCode() : 0;
        result = 31 * result + (itemDetails != null ? itemDetails.hashCode() : 0);
        return result;
    }
}
