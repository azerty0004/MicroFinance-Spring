package tn.esprit.infini.Pidev.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

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




}
