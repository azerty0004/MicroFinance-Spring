package tn.esprit.infini.Pidev.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table( name = "Transaction")
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TypeTransaction typeTransaction;
    private long idUser;
    private long idobject;
    private Date date;
    @ManyToOne(fetch = FetchType.LAZY)
    private Invest invest;
    @ManyToOne(fetch = FetchType.LAZY)
    private Credit credit;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "transaction")
    private List<Account> accounts;
    private Statut statut;
    private Double amount;


}
