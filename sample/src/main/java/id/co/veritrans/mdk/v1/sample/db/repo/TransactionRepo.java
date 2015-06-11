package id.co.veritrans.mdk.v1.sample.db.repo;

import id.co.veritrans.mdk.v1.sample.db.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by gde on 5/18/15.
 */
@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {

}
