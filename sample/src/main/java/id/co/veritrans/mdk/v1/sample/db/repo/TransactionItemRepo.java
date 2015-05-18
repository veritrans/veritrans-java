package id.co.veritrans.mdk.v1.sample.db.repo;

import id.co.veritrans.mdk.v1.sample.db.model.TransactionItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by gde on 5/18/15.
 */
public interface TransactionItemRepo extends JpaRepository<TransactionItem, Long> {

}
