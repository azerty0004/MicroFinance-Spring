package tn.esprit.infini.Pidev.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Credit")
public class Credit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Double amount;

    @NotNull
    private LocalDate dateOfApplication = LocalDate.now();

    @NotNull
    @FutureOrPresent
    private LocalDate dateofobtaining;

    @NotNull
    @FutureOrPresent
    private LocalDate dateoffinish;

    private Double interestrate;

    private Integer duration;
       @Enumerated(EnumType.STRING)
    private Statut statut = Statut.EN_ATTENTE;

    @OneToOne
    @JsonIgnore
    private Guarantor guarantor;

    @Enumerated(EnumType.STRING)
    private TypeCredit typeCredit;

    @OneToMany(mappedBy = "credit", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Transaction> transactions = new ArrayList<>();

    @OneToOne
    @JsonIgnore
    private Insurance insurance;

    @Enumerated(EnumType.STRING)
    private TypeRemboursement typeRemboursement;

    @PrePersist
    public void validateDates() {
        if (dateofobtaining.isBefore(dateOfApplication) || dateofobtaining.isEqual(dateOfApplication)) {
            throw new IllegalArgumentException("Date of obtaining cannot be before or equal to date of application");
        }

        if (dateoffinish.isBefore(dateofobtaining)) {
            throw new IllegalArgumentException("Date of finish cannot be before date of obtaining");
        }
        long monthsBetween = ChronoUnit.MONTHS.between(dateoffinish.withDayOfMonth(1), dateofobtaining.withDayOfMonth(1));
        if (duration !=monthsBetween){
            throw new IllegalArgumentException("duration must be the difference between date of obtaining et date of finish");

        }
    }

     

}
