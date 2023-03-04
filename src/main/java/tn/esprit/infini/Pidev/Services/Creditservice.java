package tn.esprit.infini.Pidev.Services;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.Repository.Creditrepository;
import tn.esprit.infini.Pidev.entities.*;
import tn.esprit.infini.Pidev.exceptions.ResourceNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class Creditservice implements Icreditservice {

    private Creditrepository creditrepository;
    @Override
    public List<Credit> retrieveAllCredits() {
        return  creditrepository.findAll();

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

        Optional<Credit> credit = creditrepository.findById(id);

        if (credit.isPresent()) {
            return  creditrepository.findById(id).get();
        } else {
            throw new ResourceNotFoundException("Credit not found with id " + id);
        }
    }


    @Override
    public void deleteCredit(Long id) {
        Optional<Credit> credit = creditrepository.findById(id);
        if (credit.isPresent()) {
             creditrepository.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("Credit not found with id " + id);


        }

    }
        @Override
    public List<Credit> findBySearchParams(Long creditId, Double amount, Date date, Integer duration, Statut statut, Guarantor guarantor, Insurance insurance, TypeCredit typecredit) {
        return creditrepository.findBySearchParams(creditId, amount, date, duration, statut, guarantor,insurance,typecredit);
    }

}
