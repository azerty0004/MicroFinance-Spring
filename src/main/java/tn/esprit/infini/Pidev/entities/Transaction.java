package tn.esprit.infini.Pidev.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;




import jakarta.persistence.*;


import jakarta.persistence.*;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    @Column(name = "amount")
    private  Long amount;
    @Column(name="stripeId")
    private String stripeId;
    @Column(name = "paymentMethod")
    private String paymentMethod;
    @Column(name = "invests")
    @OneToMany(mappedBy = "transaction")
    private Set<Invest> invests;
    @Column(name = "credits")
    @OneToMany(mappedBy = "transaction")
    private Set<Credit> credits;
      @OneToMany(mappedBy = "transaction")
    private Set<Pack> packs;

    @Column
    private String status;




    public Transaction(TypeTransaction typeTransaction, long idUser, long idObject, Date date, Long amount, String stripeId, String paymentMethod,String status) {
        this.typeTransaction = typeTransaction;
        this.idUser = idUser;
        this.idobject = idobject;
        this.date = date;
        this.amount = amount;
        this.stripeId=stripeId;
        this.paymentMethod=paymentMethod;
        this.status=status;

    }


}
