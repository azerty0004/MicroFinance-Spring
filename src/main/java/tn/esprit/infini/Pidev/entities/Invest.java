
package tn.esprit.infini.Pidev.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "Invest")
public class Invest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private Date dateofapplication;
    private Date dateofobtaining;
    private Date dateoffinish;
    private double interestrate;
    private Integer mounths;


    @Enumerated(EnumType.STRING)
    private Statut statut;
    @OneToMany(mappedBy = "invest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Transaction> transactions=new ArrayList<>();



}



