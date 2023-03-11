package tn.esprit.infini.Pidev.entities;

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
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TypeTransaction typeTransaction;
    private long idUser;
    private long idobject;
    private Date date;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transaction")
    private List<Invest> invests;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "transaction")
    private List<Credit> credits;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "transaction")
    private List<Account> accounts;




}
