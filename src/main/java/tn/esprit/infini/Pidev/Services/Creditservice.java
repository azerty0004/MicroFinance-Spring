package tn.esprit.infini.Pidev.Services;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.Repository.Creditrepository;
import tn.esprit.infini.Pidev.entities.Credit;

import java.util.List;

@Service
@AllArgsConstructor
public class Creditservice implements Icreditservice {
    Creditrepository creditrepository;
    @Override
    public List<Credit> retrieveAllcredits() {
        return (List<Credit>) creditrepository.findAll();

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
