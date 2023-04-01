package tn.esprit.infini.Pidev.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
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
    private LocalDate createdAt;
    @Enumerated(EnumType.STRING)
    private TypePack typePack;

    @ManyToOne
    @JsonIgnore
    public Cart cart;

    @ManyToOne
    @JsonIgnore
    Transaction transaction;

    @OneToMany (mappedBy = "pack")
    Set<Reaction> reaction;

    @OneToMany(mappedBy = "pack")
    Set<Insurance> insurance;


}






