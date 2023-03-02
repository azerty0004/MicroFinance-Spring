package tn.esprit.infini.Pidev.entities;

import javax.persistence.*;
import java.io.Serializable;


@Entity
    @Table( name = "pack")
    public class Pack implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="idPack")
        private int idPack ;
        private String name ;
        private String description ;
        @Enumerated(EnumType.STRING)
        private TypePack typePAck;

    @ManyToOne
    Cart cart;
    public int getIdPack() {
        return idPack;
    }

    public void setIdPack(int idPack) {
        this.idPack = idPack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypePack getTypePAck() {
        return typePAck;
    }

    public void setTypePAck(TypePack typePAck) {
        this.typePAck = typePAck;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }






