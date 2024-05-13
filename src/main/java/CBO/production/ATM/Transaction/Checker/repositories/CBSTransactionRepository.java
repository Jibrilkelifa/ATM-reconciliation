package CBO.production.ATM.Transaction.Checker.repositories;

import CBO.production.ATM.Transaction.Checker.models.CBSTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CBSTransactionRepository extends JpaRepository<CBSTransaction,Long> {

}
