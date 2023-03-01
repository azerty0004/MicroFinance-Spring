package tn.esprit.infini.Pidev.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
@Entity
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TypeTransaction typeTransaction;
    private long idUser;
    private long idobject;

    public Transaction(Long id) {
        this.id = id;

    }

    public Transaction() {

    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", typeTransaction=" + typeTransaction +
                ", idUser=" + idUser +
                ", idobject=" + idobject +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public TypeTransaction getTypeTransaction() {
        return typeTransaction;
    }

    public long getIdUser() {
        return idUser;
    }

    public long getIdobject() {
        return idobject;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    private double amount;

    public Transaction(Long id, TypeTransaction typeTransaction, long idUser, long idobject, double amount, Date date) {
        this.id = id;
        this.typeTransaction = typeTransaction;
        this.idUser = idUser;
        this.idobject = idobject;
        this.amount = amount;
        this.date = date;
    }

    private Date date;






}
