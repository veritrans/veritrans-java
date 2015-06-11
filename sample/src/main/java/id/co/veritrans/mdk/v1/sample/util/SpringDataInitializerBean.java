package id.co.veritrans.mdk.v1.sample.util;

import id.co.veritrans.mdk.v1.sample.db.model.Product;
import id.co.veritrans.mdk.v1.sample.db.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * Created by gde on 5/18/15.
 */
@Component
public class SpringDataInitializerBean {

    @Autowired
    private ProductRepo productRepo;

    @PostConstruct
    public void setup() {
        final Product p1 = new Product();
        p1.setShortName("Apple");
        p1.setPriceIdr(new Long(10000));
        p1.setThumbnailFilePath("images/products/apple.jpg");

        final Product p2 = new Product();
        p2.setShortName("Orange");
        p2.setPriceIdr(new Long(20000));
        p2.setThumbnailFilePath("images/products/orange.jpg");

        productRepo.save(Arrays.asList(p1, p2));
    }
}
