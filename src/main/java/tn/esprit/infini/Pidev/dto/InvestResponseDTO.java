package tn.esprit.infini.Pidev.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.infini.Pidev.entities.Statut;
import tn.esprit.infini.Pidev.entities.TypeCredit;
import tn.esprit.infini.Pidev.entities.TypeRemboursement;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvestResponseDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private LocalDate dateofapplication;
    private LocalDate dateofobtaining;
    private LocalDate dateoffinish;
    private double interestrate=0.06;
    private Integer mounths;
    @Enumerated(EnumType.STRING)
    private Statut statut = Statut.EN_ATTENTE;
}

