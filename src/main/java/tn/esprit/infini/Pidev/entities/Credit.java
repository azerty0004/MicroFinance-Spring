package tn.esprit.infini.Pidev.entities;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter

@Table( name = "Credit")

public class Credit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private Date dateofapplication;
    private Date dateofobtaining;
    private Date dateoffinish;
    private Double interestrate;
    private Integer duration;
    @Enumerated(EnumType.STRING)
    private Statut statut;
    @OneToOne
    Guarantor guarantor;
    @Enumerated(EnumType.STRING)
    private TypeCredit typeCredit;
    @ManyToOne
    Transaction transaction;
    @OneToOne
    Insurance insurance;
   // @OneToMany( mappedBy="credit")
    // private Set<Insurance> Insurance;




}

