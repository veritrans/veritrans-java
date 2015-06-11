package id.co.veritrans.mdk.v1.sample.controller.model;

import id.co.veritrans.mdk.v1.sample.db.model.Product;

/**
* Created by gde on 5/21/15.
*/
public class ViewCartItem {

    private Product product;
    private Integer count;
    private Long totalPrice;

    public ViewCartItem(final Product product, final Integer count) {
        this.product = product;
        this.count = count;
        this.totalPrice = new Long(count.longValue() * product.getPriceIdr().longValue());
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(final Product product) {
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(final Integer count) {
        this.count = count;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(final Long totalPrice) {
        this.totalPrice = totalPrice;
    }
}
