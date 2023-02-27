package tn.esprit.infini.Pidev.Services;
import tn.esprit.infini.Pidev.entities.Credit;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface Icreditservice {
    List<Credit> retrieveAllcredits();

    Credit addCredit(Credit c);

    Credit updateCredit (Credit c);

    Credit retrieveCredit (Long id);

    void deleteCredit( Long id);

}



