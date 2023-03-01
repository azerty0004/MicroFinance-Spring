package tn.esprit.infini.Pidev.Services;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.Repository.Creditrepository;
import tn.esprit.infini.Pidev.entities.Credit;
import tn.esprit.infini.Pidev.entities.Statut;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class Creditservice implements Icreditservice {
    private Creditrepository creditrepository;
    @Override
    public List<Credit> retrieveAllCredits() {
        return  creditrepository.findAll();

    }

    @Override
    public List<Credit> findByDateOfObtainingGreaterThan(Date c) {
        return (List<Credit>) creditrepository.findByDateOfObtainingGreaterThan(c);
    }

    @Override
    public List<Credit> findByStatuts(Statut statut) {
        return (List<Credit>) creditrepository.findByStatuts(Statut.ACTIF);
    }

    @Override
    public Credit addCredit(Credit c) {
        return creditrepository.save(c);
    }

    @Override
    public Credit updateCredit(Credit c) {
        return creditrepository.save(c);
    }

    @Override
    public Credit retrieveCredit(Long id) {
        return creditrepository.findById(id).get();
    }

    @Override
    public void deleteCredit(Long id) {
        creditrepository.deleteById(id);

    }

}
