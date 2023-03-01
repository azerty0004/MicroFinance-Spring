package tn.esprit.infini.Pidev.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int cin;
    private int phoneNumber;
    private Date birthdate;
    private Date creationDate;
    private int nombreTentatives;
    private String login;
    private String password;
    private Date lastBanDate;
    @OneToOne
    private Account account;


}
