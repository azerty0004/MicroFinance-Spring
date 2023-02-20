
package tn.esprit.infini.Pidev.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table( name = "Invest")
public class Invest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private Date dateofapplication;
    private Date dateofobtaining;
    private Date dateoffinish;
    private double interestrate;
    private Integer mounths;


    @Enumerated(EnumType.STRING)
    private Statut statut;
       public Invest () {}
    public Invest(Double amount,Double interestrate,Date dateofapplication,Date dateofobtaining,Date dateoffinish ) {

        this.amount = amount;
        this.mounths = mounths;
        this.dateofapplication = dateofapplication;
        this.dateoffinish = dateoffinish;
        this.interestrate = interestrate;
        this.dateofobtaining = dateofobtaining;

    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double montant) {
        this.amount = amount;
    }
    public Date getDateofobtaining() {
        return dateofobtaining;
    }
    public Date getDateofapplication() {
        return dateofapplication;
    }
    public Date getDateoffinish() {
        return dateoffinish;
    }

    public void setDateofapplication(Date dateofapplication) {
        this.dateofapplication = dateofapplication;
    }
    public void setDateofobtaining(Date dateofobtaining) {
        this.dateofobtaining = dateofobtaining;
    }
    public void setDateoffinish(Date dateoffinish) {
        this.dateoffinish = dateoffinish;
    }
    public Double getInterestrate() {
        return interestrate;
    }
    public void setInterestrate(Double interestrate) {
        this.interestrate = interestrate;
    }
}


