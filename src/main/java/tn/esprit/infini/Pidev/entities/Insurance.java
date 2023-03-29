package tn.esprit.infini.Pidev.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table( name = "insurance")
public class Insurance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idinsurance;
    private String insured;
    private Double insuredAmount;
    private Date startinsurance;
    private Date endinsurance;
    private String coverage;
    private Double deductible;
    private String claimsHistory;
    private Integer levelofrisk;
    private Boolean archived;
    @ManyToOne
    Pack pack;
    @OneToOne
    Credit credit;

}

