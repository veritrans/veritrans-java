package id.co.veritrans.mdk.v1.gateway.model;

/**
 * Get batch status request
 */
public class StatusRequest implements VtRequest {

    private String[] orderIds;
    private Integer page;
    private Integer rowPerPage;

    /**
     * Constructor of {@link StatusRequest StatusRequest} class
     *
     * @param orderIds
     * @param page
     * @param rowPerPage
     */
    public StatusRequest(String[] orderIds, Integer page, Integer rowPerPage) {
        this.orderIds = orderIds;
        this.page = page;
        this.rowPerPage = rowPerPage;
    }

    /**
     * Get list of order Id
     *
     * @return
     */
    public String[] getOrderIds() {
        return orderIds;
    }

    /**
     * Get number of page that will be returned
     *
     * @return
     */
    public Integer getPage() {
        return page;
    }

    /**
     * Get number of row for each page that will be returned
     *
     * @return
     */
    public Integer getRowPerPage() {
        return rowPerPage;
    }
}
