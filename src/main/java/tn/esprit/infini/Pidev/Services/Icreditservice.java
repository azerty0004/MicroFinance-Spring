package tn.esprit.infini.Pidev.Services;
import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.entities.Credit;
import tn.esprit.infini.Pidev.entities.Statut;

import java.util.Date;
import java.util.List;
@Service
public interface Icreditservice {
    List<Credit> retrieveAllCredits();
    List<Credit> findByDateOfObtainingGreaterThan(
    Date c);
    List<Credit> findByStatuts(Statut statut);


    Credit addCredit(Credit c);

    Credit updateCredit (Credit c);

    Credit retrieveCredit (Long id);

    void deleteCredit( Long id);

}



