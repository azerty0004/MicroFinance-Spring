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
    import java.util.Set;

    @Entity
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
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

        @OneToMany(mappedBy = "credit", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        @JsonIgnore
        private List<Transaction> transactions=new ArrayList<>();
        @OneToOne
        Insurance insurance;
        @Enumerated(EnumType.STRING)
        private TypeRemboursement typeRemboursement;





    }

