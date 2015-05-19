package id.co.veritrans.mdk.v1.sample.db.model;

import javax.persistence.*;

/**
 * Created by gde on 5/18/15.
 */
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "short_name", nullable = false)
    private String shortName;

    @Column(name = "long_name", nullable = true)
    private String longName;

    @Column(name = "thumbnail_file_path", nullable = false)
    private String thumbnailFilePath;

    @Column(name = "price_idr", nullable = false)
    private Long priceIdr;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(final String shortName) {
        this.shortName = shortName;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(final String longName) {
        this.longName = longName;
    }

    public String getThumbnailFilePath() {
        return thumbnailFilePath;
    }

    public void setThumbnailFilePath(final String thumbnailFilePath) {
        this.thumbnailFilePath = thumbnailFilePath;
    }

    public Long getPriceIdr() {
        return priceIdr;
    }

    public void setPriceIdr(final Long priceIdr) {
        this.priceIdr = priceIdr;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Product product = (Product) o;

        if (id != null ? !id.equals(product.id) : product.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
