package tn.esprit.infini.Pidev.entities;


import java.util.Set;
import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table( name = "cart")
public class cart implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="idCart")
        private int idCart ;
        private String productName ;
        private String productDescription ;
        private int quantity;
        private double price ;
        private double mouthlyamount;
        private int nbreMounths ;
        @Enumerated(EnumType.STRING)
        private TypePack typePAck;

        @OneToMany (mappedBy = "cart")
        private Set <pack> pack;


}
