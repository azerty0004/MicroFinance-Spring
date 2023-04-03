package tn.esprit.infini.Pidev.entities;

import jakarta.persistence.*;
import lombok.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date creationDate;
    private float balance;
    @OneToOne
    private User user;
    @ManyToOne
    Transaction transaction;

}
