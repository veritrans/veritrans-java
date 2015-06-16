package id.co.veritrans.mdk.v1.gateway.model;

import java.util.ArrayList;

/**
 * Get batch status request
 */
public class StatusRequest implements VtRequest {

    private ArrayList<String> orderIds;
    private Integer page;
    private Integer rowPerPage;

    /**
     * Constructor of {@link StatusRequest StatusRequest} class
     *
     * @param orderIds
     * @param page
     * @param rowPerPage
     */
    public StatusRequest(ArrayList<String> orderIds, Integer page, Integer rowPerPage) {
        this.orderIds = orderIds;
        this.page = page;
        this.rowPerPage = rowPerPage;
    }

    /**
     * Get list of order Id
     *
     * @return
     */
    public ArrayList<String> getOrderIds() {
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
