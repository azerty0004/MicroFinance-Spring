package tn.esprit.infini.Pidev.entities;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.io.Serializable;


@Entity
@Getter
@Setter
    @Table( name = "Pack")
    public class Pack implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPack")
    private int idPack;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private TypePack typePAck;

    @ManyToOne
    Cart cart;


}






