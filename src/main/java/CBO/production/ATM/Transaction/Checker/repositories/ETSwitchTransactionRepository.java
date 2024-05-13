package CBO.production.ATM.Transaction.Checker.repositories;

import CBO.production.ATM.Transaction.Checker.models.ETSwitchTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ETSwitchTransactionRepository extends JpaRepository<ETSwitchTransaction, Long> {

}
