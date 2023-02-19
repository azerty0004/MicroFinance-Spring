package tn.esprit.infini.Pidev.entities;

import javax.persistence.*;
import java.io.Serializable;


@Entity
    @Table( name = "pack")
    public class pack implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="idPack")
        private int idPack ;
        private String name ;
        private String description ;
        @Enumerated(EnumType.STRING)
        private TypePack typePAck;

    @ManyToOne
    cart cart;
    }






