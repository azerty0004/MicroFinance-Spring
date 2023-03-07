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
    List<Credit> findCreditBySearchParams(Long creditId, Double amount, Date date, Integer duration, Statut statut, Guarantor guarantor, Insurance insurance, TypeCredit typecredit,Long transactionid,Long accountid,Long userid);
     List<Credit> getCreditByiduser(Long userid);
     User  getuserByidcredit(Long creditid);
    Float newCredit(Credit credit);
    Float TauxtypeCredit(Credit credit);
   Credit  addandassingCreditToTransaction(Long idCredit, Long idTransaction);
    float CalculMensualité(Credit credit);


    public float calculateFicoScore(Credit credit);




    }



