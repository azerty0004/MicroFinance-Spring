
package tn.esprit.infini.Pidev.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
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
    @ManyToOne
    Transaction transaction;



}



