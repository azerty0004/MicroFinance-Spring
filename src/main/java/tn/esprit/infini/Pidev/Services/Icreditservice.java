package tn.esprit.infini.Pidev.Services;
import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.entities.*;

import java.util.Date;
import java.util.List;
@Service
public interface Icreditservice {
    List<Credit> retrieveAllCredits();
    Credit addCredit(Credit c);

    Credit updateCredit (Credit c);

    Credit retrieveCredit (Long id);

    void deleteCredit( Long id);
    List<Credit> findCreditsByAttributes(Long id, Double amount, Date dateofapplication,Date dateofobtaining, Date dateoffinish, Double interestrate, Integer duration,Statut statut, Guarantor guarantor, TypeCredit typeCredit, Transaction transaction,Insurance insurance);
    List<Credit> getCreditByiduser(Long userid);
     User  getuserByidcredit(Long creditid);
    Float newCredit(Credit credit);
    Integer TauxtypeCredit(Credit credit);
    Credit  addCreditToTransaction(Credit credit,Transaction transaction);
    double CalculMensualitéfixe(Credit credit);
    List<Double> CalculMensualitévariable(Credit credit);
    List<Double> listetauxinterets(Credit credit);
    void ValidateCredit(Credit c);
     float calculateFicoScore(Credit credit);

    double InterestRateCalculator(Credit credit);


    }



