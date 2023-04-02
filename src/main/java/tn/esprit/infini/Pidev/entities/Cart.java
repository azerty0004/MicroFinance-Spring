package tn.esprit.infini.Pidev.entities;



import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import jakarta.persistence.*;


import java.util.Set;

import jakarta.persistence.*;


import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "Cart")


public class Cart implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idCart")
        private int idCart;
        private int nbreMounths;

         @OneToMany(mappedBy = "cart")
         @JsonIgnore
         public Set<Pack> pack;


}



