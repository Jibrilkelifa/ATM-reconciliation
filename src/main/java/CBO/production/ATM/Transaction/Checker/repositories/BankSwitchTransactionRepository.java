package CBO.production.ATM.Transaction.Checker.repositories;

import CBO.production.ATM.Transaction.Checker.models.BankSwitchTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankSwitchTransactionRepository extends JpaRepository<BankSwitchTransaction,Long> {

}
