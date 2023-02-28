package tn.esprit.infini.Pidev.entities;


import java.util.Set;
import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table( name = "cart")
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

        public int getIdCart() {
                return idCart;
        }

        public void setIdCart(int idCart) {
                this.idCart = idCart;
        }

        public String getProductName() {
                return productName;
        }

        public void setProductName(String productName) {
                this.productName = productName;
        }

        public String getProductDescription() {
                return productDescription;
        }

        public void setProductDescription(String productDescription) {
                this.productDescription = productDescription;
        }

        public int getQuantity() {
                return quantity;
        }

        public void setQuantity(int quantity) {
                this.quantity = quantity;
        }

        public double getPrice() {
                return price;
        }

        public void setPrice(double price) {
                this.price = price;
        }

        public double getMouthlyamount() {
                return mouthlyamount;
        }

        public void setMouthlyamount(double mouthlyamount) {
                this.mouthlyamount = mouthlyamount;
        }

        public int getNbreMounths() {
                return nbreMounths;
        }

        public void setNbreMounths(int nbreMounths) {
                this.nbreMounths = nbreMounths;
        }

        public TypePack getTypePAck() {
                return typePAck;
        }

        public void setTypePAck(TypePack typePAck) {
                this.typePAck = typePAck;
        }

        public Set<Pack> getPack() {
                return pack;
        }

        public void setPack(Set<Pack> pack) {
                this.pack = pack;
        }
}



