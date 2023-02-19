package tn.esprit.infini.Pidev.entities;

import java.io.Serializable;
import java.util.Date;

public class Fine  implements Serializable {
    private Long id;
    private Long idCustomer;
    private Double totalAmount;
    private Date dueDate;
    private Date startDate;
    private Boolean verified;
    private Double interest;
    private String picture;
    private Date declaredDate;
    private FineType fineType;

    public void setFineType(FineType fineType) {
        this.fineType = fineType;
    }


    @Override
    public String toString() {
        return "Fine{" +
                "id=" + id +
                ", idCustomer=" + idCustomer +
                ", totalAmount=" + totalAmount +
                ", dueDate=" + dueDate +
                ", startDate=" + startDate +
                ", verified=" + verified +
                ", interest=" + interest +
                ", declaredDate=" + declaredDate +
                ", fineType=" + fineType +
                '}';
    }

    public FineType getFineType() {
        return fineType;
    }

    public Fine(Long id, Long idCustomer, Double totalAmount, Date dueDate, Date startDate, Boolean verified, Double interest, Date declaredDate, FineType fineType) {
        this.id = id;
        this.idCustomer = idCustomer;
        this.totalAmount = totalAmount;
        this.dueDate = dueDate;
        this.startDate = startDate;
        this.verified = verified;
        this.interest = interest;
        this.declaredDate = declaredDate;
        this.fineType = fineType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setDeclaredDate(Date declaredDate) {
        this.declaredDate = declaredDate;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public Long getId() {
        return id;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getPicture() {
        return picture;
    }

    public Date getDeclaredDate() {
        return declaredDate;
    }

    public Boolean getVerified() {
        return verified;
    }

    public Double getInterest() {
        return interest;
    }



    public Fine(Long id, Long idCustomer, Double totalAmount, Date dueDate, Date startDate, Date declaredDate, Boolean verified, Double interest) {
        this.id = id;
        this.idCustomer = idCustomer;
        this.totalAmount = totalAmount;
        this.dueDate = dueDate;
        this.startDate = startDate;
        this.declaredDate = declaredDate;
        this.verified = verified;
        this.interest = interest;
    }


}
