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
    private List<TransactionItem> transactionItems;

    /**
     * AbstractVtRequest constructor
     */
    public AbstractVtRequest() {
    }

    /**
     * AbstractVtRequest constructor
     * @param transactionDetails    {@link id.co.veritrans.mdk.v1.gateway.model.TransactionDetails Transaction details}
     * @param transactionItems      List of {@link id.co.veritrans.mdk.v1.gateway.model.TransactionItem transaction item}
     */
    public AbstractVtRequest(final TransactionDetails transactionDetails, final List<TransactionItem> transactionItems) {
        this.transactionDetails = transactionDetails;
        this.transactionItems = transactionItems;
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
    public List<TransactionItem> getTransactionItems() {
        return transactionItems;
    }

    /**
     * Set list of transaction item
     * @param transactionItems List of {@link id.co.veritrans.mdk.v1.gateway.model.TransactionItem transaction item}
     */
    public void setTransactionItems(final List<TransactionItem> transactionItems) {
        this.transactionItems = transactionItems;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final AbstractVtRequest vtRequest = (AbstractVtRequest) o;

        if (transactionDetails != null ? !transactionDetails.equals(vtRequest.transactionDetails) : vtRequest.transactionDetails != null)
            return false;
        if (transactionItems != null ? !transactionItems.equals(vtRequest.transactionItems) : vtRequest.transactionItems != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = transactionDetails != null ? transactionDetails.hashCode() : 0;
        result = 31 * result + (transactionItems != null ? transactionItems.hashCode() : 0);
        return result;
    }
}
