package id.co.veritrans.mdk.v1.sample.db.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by gde on 5/18/15.
 */
@Entity
@Table(name = "transaction_item")
public class TransactionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "price_each_idr", nullable = false)
    private BigDecimal priceEachIdr;

    @Column(name = "total_price_idr", nullable = false)
    private BigDecimal totalPriceIdr;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(final Transaction transaction) {
        this.transaction = transaction;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(final Product product) {
        this.product = product;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(final Long amount) {
        this.amount = amount;
    }

    public BigDecimal getPriceEachIdr() {
        return priceEachIdr;
    }

    public void setPriceEachIdr(final BigDecimal priceEachIdr) {
        this.priceEachIdr = priceEachIdr;
    }

    public BigDecimal getTotalPriceIdr() {
        return totalPriceIdr;
    }

    public void setTotalPriceIdr(final BigDecimal totalPriceIdr) {
        this.totalPriceIdr = totalPriceIdr;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final TransactionItem that = (TransactionItem) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
