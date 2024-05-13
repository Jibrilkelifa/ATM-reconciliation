package CBO.production.ATM.Transaction.Checker.models;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bank_switch_transaction")
public class BankSwitchTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "atm_id", referencedColumnName = "id")
    private ATM atm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @Column(name = "retrieval_number")
    private String retrievalNumber;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "transaction_date_time")
    private LocalDateTime transactionDateTime;

}
