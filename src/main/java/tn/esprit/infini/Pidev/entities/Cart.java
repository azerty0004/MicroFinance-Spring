package tn.esprit.infini.Pidev.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.io.Serializable;
@Entity
@Getter
@Setter
@Table( name = "Cart")


public class Cart implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idCart")
        private int idCart;
        private String productName;
        private String productDescription;
        private int quantity;
        private double price;
        private double mouthlyamount;
        private int nbreMounths;
        @Enumerated(EnumType.STRING)
        private TypePack typePAck;

         @OneToMany(mappedBy = "cart")
          private Set<Pack> pack;


}



