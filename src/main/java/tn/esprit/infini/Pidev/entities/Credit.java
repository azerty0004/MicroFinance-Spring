package tn.esprit.infini.Pidev.entities;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Getter
@Setter
@Table( name = "Credit")

public class Credit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private Date dateofapplication;
    private Date dateofobtaining;
    private Date dateoffinish;
    private Double interestrate;
    private Integer duration;
    @Enumerated(EnumType.STRING)
    private Statut statut;

    @Enumerated(EnumType.STRING)
    private TypeCredit typeCredit;
    @ManyToOne
    Transaction transaction;
    @OneToOne
    Insurance insurance;

    @OneToOne
    Guarantor guarantor;





}

