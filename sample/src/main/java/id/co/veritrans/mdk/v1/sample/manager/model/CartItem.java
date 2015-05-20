package id.co.veritrans.mdk.v1.sample.manager.model;

import id.co.veritrans.mdk.v1.sample.db.model.Product;

/**
 * Created by gde on 5/20/15.
 */
public class CartItem {

    private final int amount;
    private final Product product;
    private final long totalPrice;

    public CartItem(final Product product, final int amount) {
        this.product = product;
        this.amount = amount;
        this.totalPrice = amount * product.getPriceIdr().longValue();
    }

    public Product getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }

    public long calcTotalPrice() {
        return totalPrice;
    }
}
