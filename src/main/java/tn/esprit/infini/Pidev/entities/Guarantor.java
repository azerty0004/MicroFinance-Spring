package tn.esprit.infini.Pidev.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "Guarantor")
public class Guarantor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idGuarantor")
    private int idGuarantor ;
    private String firstNameGuarantor ;
    private String lastNameGuarantor ;
    private int cinGuarantor ;
    private String salary ;
    private String job ;


    public int getIdGuarantor() {
        return idGuarantor;
    }

    public void setIdGuarantor(int idGuarantor) {
        this.idGuarantor = idGuarantor;
    }

    public String getFirstNameGuarantor() {
        return firstNameGuarantor;
    }

    public void setFirstNameGuarantor(String firstNameGuarantor) {
        this.firstNameGuarantor = firstNameGuarantor;
    }

    public String getLastNameGuarantor() {
        return lastNameGuarantor;
    }

    public void setLastNameGuarantor(String lastNameGuarantor) {
        this.lastNameGuarantor = lastNameGuarantor;
    }

    public int getCinGuarantor() {
        return cinGuarantor;
    }

    public void setCinGuarantor(int cinGuarantor) {
        this.cinGuarantor = cinGuarantor;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
