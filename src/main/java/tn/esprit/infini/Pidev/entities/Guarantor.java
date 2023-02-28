package tn.esprit.infini.Pidev.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "guarantor")
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
}
