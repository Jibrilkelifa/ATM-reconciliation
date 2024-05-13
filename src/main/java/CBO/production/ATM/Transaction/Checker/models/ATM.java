package CBO.production.ATM.Transaction.Checker.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "atms")
@Entity
public class ATM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String atmId;

    @Column(nullable = false)
    private String location;

}
