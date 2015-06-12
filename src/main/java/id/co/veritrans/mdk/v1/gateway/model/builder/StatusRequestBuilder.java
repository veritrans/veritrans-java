package id.co.veritrans.mdk.v1.gateway.model.builder;

import id.co.veritrans.mdk.v1.gateway.model.StatusRequest;

/**
 * Builder of {@link id.co.veritrans.mdk.v1.gateway.model.StatusRequest StatusRequest} class
 */
public class StatusRequestBuilder {
    private String[] orderIds;
    private Integer page;
    private Integer rowPerPage;

    /**
     * Set list of order id
     *
     * @param orderIds
     * @return
     */
    public StatusRequestBuilder setOrderIds(String[] orderIds) {
        this.orderIds = orderIds;
        return this;
    }

    /**
     * Set number of page will be returned
     *
     * @param page
     * @return
     */
    public StatusRequestBuilder setPage(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * Set number of row will be returned on each page
     *
     * @param rowPerPage
     * @return
     */
    public StatusRequestBuilder setRowPerPage(Integer rowPerPage) {
        this.rowPerPage = rowPerPage;
        return this;
    }

    /**
     * Get {@link id.co.veritrans.mdk.v1.gateway.model.StatusRequest StatusRequest} object from this builder
     *
     * @return
     */
    public StatusRequest createStatusRequest() {
        return new StatusRequest(orderIds, page, rowPerPage);
    }
}