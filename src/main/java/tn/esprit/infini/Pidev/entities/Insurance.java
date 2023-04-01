package tn.esprit.infini.Pidev.entities;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table( name = "insurance")
public class Insurance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idinsurance;

    private String insured;
    private Date startinsurance;
    private Date endinsurance;
    private Double premium;
    private String coverage;
    private Double deductible;
    private String claimsHistory;
   // @ManyToOne
  //  Pack pack;
     @OneToOne
    Credit credit;



}

