package tn.esprit.infini.Pidev.Services;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.Repository.Creditrepository;
import tn.esprit.infini.Pidev.entities.Credit;
import tn.esprit.infini.Pidev.entities.Statut;
import tn.esprit.infini.Pidev.entities.Guarantor;
import tn.esprit.infini.Pidev.entities.Insurance;
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
    @Override
    public List<Credit> findBySearchParams(Long creditId, Double amount, Date date, Integer duration, Statut statut, Guarantor guarantor,Insurance insurance) {
        return creditrepository.findBySearchParams(creditId, amount, date, duration, statut, guarantor,insurance);
    }


}
