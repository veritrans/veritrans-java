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
    private CustomerDetails customerDetails;
    private String customField1;
    private String customField2;
    private String customField3;

    /**
     * AbstractVtRequest default constructor.
     */
    public AbstractVtRequest() {
    }

    /**
     * AbstractVtRequest constructor.
     *
     * @param transactionDetails    {@link id.co.veritrans.mdk.v1.gateway.model.TransactionDetails Transaction details}.
     */
    public AbstractVtRequest(final TransactionDetails transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    /**
     * AbstractVtRequest constructor.
     *
     * @param transactionDetails    {@link id.co.veritrans.mdk.v1.gateway.model.TransactionDetails Transaction details}.
     * @param itemDetails      List of {@link id.co.veritrans.mdk.v1.gateway.model.TransactionItem transaction item}.
     * @param customerDetails       Transaction {@link id.co.veritrans.mdk.v1.gateway.model.CustomerDetails customer details}.
     */
    public AbstractVtRequest(final TransactionDetails transactionDetails, final List<TransactionItem> itemDetails, final CustomerDetails customerDetails) {
        this.transactionDetails = transactionDetails;
        this.itemDetails = itemDetails;
        this.customerDetails = customerDetails;
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

    /**
     * Get transaction customer details
     * @return Transaction {@link id.co.veritrans.mdk.v1.gateway.model.CustomerDetails customer details}
     */
    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    /**
     * Set transaction customer details
     * @param customerDetails Transaction {@link id.co.veritrans.mdk.v1.gateway.model.CustomerDetails customer details}
     */
    public void setCustomerDetails(final CustomerDetails customerDetails) {
        this.customerDetails = customerDetails;
    }

    public String getCustomField1() {
        return customField1;
    }

    public void setCustomField1(final String customField1) {
        this.customField1 = customField1;
    }

    public String getCustomField2() {
        return customField2;
    }

    public void setCustomField2(final String customField2) {
        this.customField2 = customField2;
    }

    public String getCustomField3() {
        return customField3;
    }

    public void setCustomField3(final String customField3) {
        this.customField3 = customField3;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final AbstractVtRequest that = (AbstractVtRequest) o;

        if (customField1 != null ? !customField1.equals(that.customField1) : that.customField1 != null) return false;
        if (customField2 != null ? !customField2.equals(that.customField2) : that.customField2 != null) return false;
        if (customField3 != null ? !customField3.equals(that.customField3) : that.customField3 != null) return false;
        if (customerDetails != null ? !customerDetails.equals(that.customerDetails) : that.customerDetails != null)
            return false;
        if (itemDetails != null ? !itemDetails.equals(that.itemDetails) : that.itemDetails != null) return false;
        if (transactionDetails != null ? !transactionDetails.equals(that.transactionDetails) : that.transactionDetails != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = transactionDetails != null ? transactionDetails.hashCode() : 0;
        result = 31 * result + (itemDetails != null ? itemDetails.hashCode() : 0);
        result = 31 * result + (customerDetails != null ? customerDetails.hashCode() : 0);
        result = 31 * result + (customField1 != null ? customField1.hashCode() : 0);
        result = 31 * result + (customField2 != null ? customField2.hashCode() : 0);
        result = 31 * result + (customField3 != null ? customField3.hashCode() : 0);
        return result;
    }
}
