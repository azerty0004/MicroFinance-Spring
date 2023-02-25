package tn.esprit.infini.Pidev.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.entities.Invest;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class Investservice implements Iinvestservice {
    @Override
    public List<Invest> retrieveAllInvests() {
        return null;
    }

    @Override
    public Invest addInvest(Invest i) {
        return null;
    }

    @Override
    public Invest updateInvest(Invest i) {
        return null;
    }

    @Override
    public Invest retrieveInvest(Long id) {
        return null;
    }

    @Override
    public void deleteInvest(Long id) {

    }


}
