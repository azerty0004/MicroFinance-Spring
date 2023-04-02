package tn.esprit.infini.Pidev.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "TypeTransaction")
    private TypeTransaction typeTransaction;
    @Column(name = "idUser")
    private long idUser;
    @Column(name = "idObject")
    private long idObject;
    @Column(name = "date")
    private Date date;
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



    public Transaction(TypeTransaction typeTransaction, long idUser, long idObject, Date date, Long amount, String stripeId, String paymentMethod) {
        this.typeTransaction = typeTransaction;
        this.idUser = idUser;
        this.idObject = idObject;
        this.date = date;
        this.amount = amount;
        this.stripeId=stripeId;
        this.paymentMethod=paymentMethod;

    }
}
