package id.co.veritrans.mdk.v1.gateway.model.builder;

import id.co.veritrans.mdk.v1.gateway.model.FraudStatus;
import id.co.veritrans.mdk.v1.gateway.model.GetStatusParameter;
import id.co.veritrans.mdk.v1.gateway.model.PaymentMethod;
import id.co.veritrans.mdk.v1.gateway.model.TransactionStatus;

import java.util.Date;

/**
 * Get status parameter builder
 */
public class GetStatusParameterBuilder {
    private String orderId;
    private String transactionId;
    private Long grossAmount;
    private TransactionStatus transactionStatus;
    private PaymentMethod paymentMethod;
    private Date fromDate;
    private Date toDate;
    private FraudStatus fraudStatus;
    private Integer page = 1;
    private Integer rowPerPage = 10;

    /**
     * Set transaction order id
     *
     * @param orderId Transaction order id
     * @return
     */
    public GetStatusParameterBuilder setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    /**
     * Set transaction id from transaction response
     *
     * @param transactionId transaction id from transaction response
     * @return
     */
    public GetStatusParameterBuilder setTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    /**
     * Set transaction gross amount
     *
     * @param grossAmount transaction gross amount
     * @return
     */
    public GetStatusParameterBuilder setGrossAmount(Long grossAmount) {
        this.grossAmount = grossAmount;
        return this;
    }

    /**
     * Set {@link id.co.veritrans.mdk.v1.gateway.model.TransactionStatus Transaction status}
     *
     * @param transactionStatus {@link id.co.veritrans.mdk.v1.gateway.model.TransactionStatus Transaction status}
     * @return
     */
    public GetStatusParameterBuilder setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
        return this;
    }

    /**
     * Set transaction {@link id.co.veritrans.mdk.v1.gateway.model.PaymentMethod payment method}
     *
     * @param paymentMethod Transaction {@link id.co.veritrans.mdk.v1.gateway.model.PaymentMethod payment method}
     * @return
     */
    public GetStatusParameterBuilder setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    /**
     * Set transaction from date
     *
     * @param fromDate Transaction from date
     * @return
     */
    public GetStatusParameterBuilder setFromDate(Date fromDate) {
        this.fromDate = fromDate;
        return this;
    }

    /**
     * Set transaction to date
     *
     * @param toDate Transaction to date
     * @return
     */
    public GetStatusParameterBuilder setToDate(Date toDate) {
        this.toDate = toDate;
        return this;
    }

    /**
     * Set transaction fraud status
     *
     * @param fraudStatus Transaction {@link id.co.veritrans.mdk.v1.gateway.model.FraudStatus fraud status}
     * @return
     */
    public GetStatusParameterBuilder setFraudStatus(FraudStatus fraudStatus) {
        this.fraudStatus = fraudStatus;
        return this;
    }

    /**
     * Set number of page for get status result
     *
     * @param page number of page for get status result
     * @return
     */
    public GetStatusParameterBuilder setPage(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * Set number of transaction per page for result
     *
     * @param rowPerPage number of transaction per page for result
     * @return
     */
    public GetStatusParameterBuilder setRowPerPage(Integer rowPerPage) {
        this.rowPerPage = rowPerPage;
        return this;
    }

    /**
     * Build {@link id.co.veritrans.mdk.v1.gateway.model.GetStatusParameter GetStatusParameter} object
     *
     * @return {@link id.co.veritrans.mdk.v1.gateway.model.GetStatusParameter GetStatusParameter}
     */
    public GetStatusParameter createGetStatusParameter() {
        return new GetStatusParameter(orderId, transactionId, grossAmount, transactionStatus, paymentMethod, fromDate, toDate, fraudStatus, page, rowPerPage);
    }
}