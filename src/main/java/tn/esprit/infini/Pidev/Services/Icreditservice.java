package tn.esprit.infini.Pidev.Services;
import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.entities.Credit;
import tn.esprit.infini.Pidev.entities.Statut;
import tn.esprit.infini.Pidev.entities.Guarantor;
import tn.esprit.infini.Pidev.entities.TypeCredit;

import java.util.Date;
import java.util.List;
@Service
public interface Icreditservice {
    List<Credit> retrieveAllCredits();
       Credit addCredit(Credit c);

    Credit updateCredit (Credit c);

    Credit retrieveCredit (Long id);

    void deleteCredit( Long id);
    List<Credit> findBySearchParams(Long creditId, Double amount, Date date, Integer duration, Statut statut, Guarantor guarantor);
}



