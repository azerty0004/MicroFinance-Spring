package tn.esprit.infini.Pidev.Services;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.entities.Credit;

import java.util.List;

@Service
@AllArgsConstructor
public class Creditservice implements Icreditservice {

    @Override
    public List<Credit> retrieveAllcredits() {
        return null;
    }

    @Override
    public Credit addCredit(Credit c) {
        return null;
    }

    @Override
    public Credit updateCredit(Credit c) {
        return null;
    }

    @Override
    public Credit retrieveCredit(Long id) {
        return null;
    }

    @Override
    public void deleteCredit(Long id) {

    }
}
