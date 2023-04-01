package tn.esprit.infini.Pidev.dto;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.*;
import jakarta.persistence.*;
import tn.esprit.infini.Pidev.entities.Statut;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvestRequestDTO {

    @NonNull
    private Double amount;
    @NonNull
    @FutureOrPresent
    private LocalDate dateofobtaining;
    private LocalDate dateoffinish;
    private Integer mounths;

    @PrePersist
    public void validateDates() {
        if (dateoffinish.isBefore(dateofobtaining) || dateofobtaining.isEqual(dateoffinish)) {
            throw new IllegalArgumentException("Date of obtaining cannot be before or equal to date of finish");
        }
    }


}





