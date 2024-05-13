package CBO.production.ATM.Transaction.Checker.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "atm_transactions")
public class  ATMTransaction{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "retrieval_number")
    private String retrievalNumber;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "transaction_date_time")
    private LocalDateTime transactionDateTime;

    @Column(name = "source")
    private String source; // Can be "bank_switch", "etswitch", or "cbs"

}
