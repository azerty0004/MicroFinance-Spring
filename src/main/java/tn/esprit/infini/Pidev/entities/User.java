package tn.esprit.infini.Pidev.entities;

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
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private TypeUser type;
    private String email;
    private int cin;
    private int phoneNumber;
    private Date birthdate;
    private Date creationDate;
    private int nombreTentatives;
    private String login;
    private String password;
    private Date lastBanDate;
    //@OneToOne
    //private Account account;


}
