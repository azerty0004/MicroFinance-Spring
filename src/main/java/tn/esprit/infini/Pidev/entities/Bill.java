package tn.esprit.infini.Pidev.entities;


import java.io.Serializable;
import java.util.Date;


public class Bill  implements Serializable {

    private Long id;
    private Long idCustomer;
    private Double totalAmount;
    private Date dueDate;
    private Boolean verified=Boolean.FALSE;
    private Date startDate;
    private  Date declaredDate;
    private Double interest;

    public BillType getBillType() {
        return billType;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", idCustomer=" + idCustomer +
                ", totalAmount=" + totalAmount +
                ", dueDate=" + dueDate +
                ", verified=" + verified +
                ", startDate=" + startDate +
                ", declaredDate=" + declaredDate +
                ", interest=" + interest +
                ", billType=" + billType +
                '}';
    }

    public void setBillType(BillType billType) {
        this.billType = billType;
    }

    public Bill(Long id, Long idCustomer, Double totalAmount, Date dueDate, Boolean verified, Date startDate, Date declaredDate, Double interest, BillType billType) {
        this.id = id;
        this.idCustomer = idCustomer;
        this.totalAmount = totalAmount;
        this.dueDate = dueDate;
        this.verified = verified;
        this.startDate = startDate;
        this.declaredDate = declaredDate;
        this.interest = interest;
        this.billType = billType;
    }

    private BillType billType;

    public Bill(Long id, Long idCustomer, Double totalAmount, Date dueDate, Boolean verified, Date startDate, Date declaredDate, Double interest, BillType billType, String picture) {
        this.id = id;
        this.idCustomer = idCustomer;
        this.totalAmount = totalAmount;
        this.dueDate = dueDate;
        this.verified = verified;
        this.startDate = startDate;
        this.declaredDate = declaredDate;
        this.interest = interest;
        this.billType = billType;
        this.picture = picture;
    }

    public Bill(Long id, Long idCustomer, Double totalAmount, Date dueDate, Boolean verified, Date startDate, Date declaredDate, Double interest) {
        this.id = id;
        this.idCustomer = idCustomer;
        this.totalAmount = totalAmount;
        this.dueDate = dueDate;
        this.verified = verified;
        this.startDate = startDate;
        this.declaredDate = declaredDate;
        this.interest = interest;
    }

    private String picture;


    public Date getDeclaredDate() {
        return declaredDate;
    }

    public Double getInterest() {
        return interest;
    }

    public String getPicture() {
        return picture;
    }



    public void setDeclaredDate(Date declaredDate) {
        this.declaredDate = declaredDate;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }



    public Bill(Long id, Long idCustomer, Double totalAmount, Date dueDate, Boolean verified, Date startDate, Date declaredDate, Double interest, String picture) {
        this.id = id;
        this.idCustomer = idCustomer;
        this.totalAmount = totalAmount;
        this.dueDate = dueDate;
        this.verified = verified;
        this.startDate = startDate;
        this.declaredDate = declaredDate;
        this.interest = interest;
        this.picture = picture;
    }


    public Boolean getVerified() {
        return verified;
    }



    public Bill(Long id, Long idCustomer, Double totalAmount, Date dueDate, Boolean verified, Date startDate) {
        this.id = id;
        this.idCustomer = idCustomer;
        this.totalAmount = totalAmount;
        this.dueDate = dueDate;
        this.verified = verified;
        this.startDate = startDate;
    }
    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }


    public Long getId() {
        return id;
    }

    public Long getIdCustomer() {
        return idCustomer;
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

    public Double getTotalAmount() {
        return totalAmount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Bill(Long id) {
        this.id = id;
    }

}
