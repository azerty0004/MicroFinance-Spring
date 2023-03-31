package tn.esprit.infini.Pidev.dto;

import lombok.*;
import tn.esprit.infini.Pidev.entities.Statut;
import tn.esprit.infini.Pidev.entities.TypeCredit;
import tn.esprit.infini.Pidev.entities.TypeRemboursement;

import javax.persistence.PrePersist;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDate;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvestRequestDTO {

    @NonNull
    private Double amount;
    private LocalDate dateOfApplication = LocalDate.now();
    @NonNull
    @FutureOrPresent
    private LocalDate dateofobtaining;
    private LocalDate dateoffinish;
    private Integer mounths;
    private Statut statut = Statut.EN_ATTENTE;


    @PrePersist
    public void validateDates() {
        if (dateoffinish.isBefore(dateofobtaining) || dateofobtaining.isEqual(dateoffinish)) {
            throw new IllegalArgumentException("Date of obtaining cannot be before or equal to date of finish");
        }
    }


}





