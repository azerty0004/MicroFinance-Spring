package tn.esprit.infini.Pidev.entities;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
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
    @OneToOne
    Credit credit;
   }
