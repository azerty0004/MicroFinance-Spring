package tn.esprit.infini.Pidev.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "Pack")
    public class Pack implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPack")
    private int idPack;
    private String name;
    private String description;
    private double price ;
    private int likes;
    private int dislikes;
    @Enumerated(EnumType.STRING)
    private TypePack typePack;

    @ManyToOne
    @JsonIgnore
    Cart cart;

    @ManyToOne
    @JsonIgnore
    Transaction transaction;

    @OneToMany( mappedBy="pack")
    private Set<Insurance> Insurances;

}






