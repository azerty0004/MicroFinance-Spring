package tn.esprit.infini.Pidev.Services;


import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.entities.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public interface Icreditservice {
    List<Credit> retrieveAllCredits();
    Credit addCredit(Credit c);

    Credit updateCredit (Credit c);

    Credit retrieveCredit (Long id);

    void deleteCredit( Long id);
    List<Credit> findCreditsByAttributes(Long id, Double amount, LocalDate dateOfApplication, LocalDate dateofobtaining, LocalDate dateoffinish, Double interestrate, Integer duration, Statut statut, Guarantor guarantor, TypeCredit typeCredit, Insurance insurance);
    List<Credit> getCreditByiduser(Long userid);
    Float newCredit(Long idcredit);
    Integer TauxtypeCredit(Credit credit);
    double CalculMensualitéfixe(Credit credit);
    List<Double> CalculMensualitévariable(Credit credit);
    List<Double> listetauxinterets(Credit credit);
    void ValidateCredit(Credit c) throws IOException;
     float calculateFicoScore(Credit credit);

    double InterestRateCalculator(Credit credit) throws IOException;

    String getmm() throws Exception;
    Double averageInterestRate(List<Credit> credits);
     Integer totalNumberOfLoans(List<Credit> credits);
    Double totalAmountOfLoans(List<Credit> credits);
    Map<Statut, Double> percentageOfCreditsByStatus(List<Credit> credits);
    Map<TypeRemboursement, Double> averageRepaymentRateByType(List<Credit> credits);
}





