package id.co.veritrans.mdk.v1.gateway.model;

/**
 * Transaction item details
 */
public class TransactionItem {

    private String id;
    private String name;
    private Long price;
    private Integer quantity;

    /**
     * Transaction item constructor
     */
    public TransactionItem() {
    }

    /**
     * Transaction item constructor
     *
     * @param id       Item id
     * @param name     Item name
     * @param price    Item price
     * @param quantity Item quantity
     */
    public TransactionItem(final String id, final String name, final Long price, final Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Get item id
     *
     * @return Item id
     */
    public String getId() {
        return id;
    }

    /**
     * Set item id
     *
     * @param id Item id
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Get item name
     *
     * @return Item name
     */
    public String getName() {
        return name;
    }

    /**
     * Set item name
     *
     * @param name Item name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Get item price
     *
     * @return Item price
     */
    public Long getPrice() {
        return price;
    }

    /**
     * Set item price
     *
     * @param price Item price
     */
    public void setPrice(final Long price) {
        this.price = price;
    }

    /**
     * Get item quantity
     *
     * @return Item quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Set item quantity
     *
     * @param quantity Item quantity
     */
    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final TransactionItem that = (TransactionItem) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }
}
