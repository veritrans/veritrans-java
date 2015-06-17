package id.co.veritrans.mdk.v1.gateway.model;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Get status parameter for querying transaction status
 */
public class GetStatusParameter {

    private static final String DATE_FORMAT = "yyyyMMddHHmmss";

    private String orderId;
    private String transactionId;
    private Long grossAmount;
    private TransactionStatus transactionStatus;
    private PaymentMethod paymentMethod;
    private Date fromDate;
    private Date toDate;
    private FraudStatus fraudStatus;
    private Integer page;
    private Integer rowPerPage;

    /**
     * Get status parameter constructor
     *
     * @param orderId           Transaction order id
     * @param transactionId     Transaction id from transaction response
     * @param grossAmount       Transaction gross amount
     * @param transactionStatus {@link id.co.veritrans.mdk.v1.gateway.model.TransactionStatus Transaction status}
     * @param paymentMethod     Transaction {@link id.co.veritrans.mdk.v1.gateway.model.PaymentMethod payment method}
     * @param fromDate          Transaction from date
     * @param toDate            Transaction to date
     * @param fraudStatus       Transaction fraud status
     * @param page              Number of page for get status result
     * @param rowPerPage        Number of transaction per page for result
     */
    public GetStatusParameter(String orderId, String transactionId, Long grossAmount, TransactionStatus transactionStatus, PaymentMethod paymentMethod, Date fromDate, Date toDate, FraudStatus fraudStatus, Integer page, Integer rowPerPage) {
        this.orderId = orderId;
        this.transactionId = transactionId;
        this.grossAmount = grossAmount;
        this.transactionStatus = transactionStatus;
        this.paymentMethod = paymentMethod;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.fraudStatus = fraudStatus;
        this.page = page;
        this.rowPerPage = rowPerPage;
    }

    /**
     * Get transaction order id
     *
     * @return Transaction order id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Get transaction id from transaction response
     *
     * @return Transaction id from transaction response
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Get transaction gross amount
     *
     * @return Transaction gross amount
     */
    public Long getGrossAmount() {
        return grossAmount;
    }

    /**
     * Get transaction status
     *
     * @return {@link id.co.veritrans.mdk.v1.gateway.model.TransactionStatus Transaction status}
     */
    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    /**
     * Get transaction {@link id.co.veritrans.mdk.v1.gateway.model.PaymentMethod payment method}
     *
     * @return Transaction {@link id.co.veritrans.mdk.v1.gateway.model.PaymentMethod payment method}
     */
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Get transaction from date
     *
     * @return Transaction from date
     */
    public Date getFromDate() {
        return fromDate;
    }

    /**
     * Get transaction to date
     *
     * @return Transaction to date
     */
    public Date getToDate() {
        return toDate;
    }

    /**
     * Get transaction fraud status
     *
     * @return Transaction {@link id.co.veritrans.mdk.v1.gateway.model.FraudStatus fraud status}
     */
    public FraudStatus getFraudStatus() {
        return fraudStatus;
    }

    /**
     * Get number of page for get status result
     *
     * @return Number of page for get status result
     */
    public Integer getPage() {
        return page;
    }

    /**
     * Get number of transaction per page for result
     *
     * @return Number of transaction per page for result
     */
    public Integer getRowPerPage() {
        return rowPerPage;
    }

    /**
     * Generate url params from this object
     * @return List of url params
     */
    public List<NameValuePair> toUrlParameter() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

        List<NameValuePair> urlParams = new LinkedList<NameValuePair>();
        if (orderId != null) urlParams.add(new BasicNameValuePair("order_id", orderId));
        if (transactionId != null) urlParams.add(new BasicNameValuePair("transaction_id", transactionId));
        if (grossAmount != null) urlParams.add(new BasicNameValuePair("gross_amount", grossAmount.toString()));
        if (transactionStatus != null) urlParams.add(new BasicNameValuePair("transaction_status", transactionStatus.getName()));
        if (paymentMethod != null) urlParams.add(new BasicNameValuePair("payment_type", paymentMethod.getName()));
        if (fromDate != null) urlParams.add(new BasicNameValuePair("from_date", dateFormat.format(fromDate)));
        if (toDate != null) urlParams.add(new BasicNameValuePair("to_date", dateFormat.format(toDate)));
        if (fraudStatus != null) urlParams.add(new BasicNameValuePair("fraud_status", fraudStatus.getName()));
        urlParams.add(new BasicNameValuePair("page", page.toString()));
        urlParams.add(new BasicNameValuePair("row_per_page", rowPerPage.toString()));

        return urlParams;
    }
}
