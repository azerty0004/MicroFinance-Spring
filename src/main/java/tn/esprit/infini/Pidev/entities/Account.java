package tn.esprit.infini.Pidev.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date creationDate;
    private float balance;
    @OneToOne
    @JoinColumn(name = "user_id")

    private User user;
    @ManyToOne
    @JoinColumn(name = "transaction_id")

    private Transaction transaction;
}
