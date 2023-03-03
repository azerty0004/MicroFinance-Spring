package tn.esprit.infini.Pidev.Services;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.Repository.Investrepository;
import tn.esprit.infini.Pidev.entities.Invest;

import java.util.List;
import java.util.Optional;
@Service
@Getter
@Setter
@AllArgsConstructor
public class Investservice implements Iinvestservice {
    Investrepository investrepostory;

    @Override
    public List<Invest> retrieveAllInvests() {
        return (List<Invest>) investrepostory.findAll() ;
    }

    @Override
    public List<Invest> retrieveByInterestRatelike(double i) {

        return (List<Invest>) investrepostory.findByInterestrateLike(i);
    }

    @Override
    public Invest addInvest(Invest i) {
        return investrepostory.save(i);
    }

    @Override
    public Invest updateInvest(Invest i) {
        return investrepostory.save(i);
    }

    @Override
    public Invest retrieveInvest(Long id) {
        return investrepostory.findById(id).get();
    }

    @Override
    public void deleteInvest(Long id) {
        investrepostory.deleteById(id);

    }


}
