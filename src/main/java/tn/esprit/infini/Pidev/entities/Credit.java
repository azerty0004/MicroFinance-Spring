package tn.esprit.infini.Pidev.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double montant;

    @Enumerated(EnumType.STRING)
    private Statut statut;
}
