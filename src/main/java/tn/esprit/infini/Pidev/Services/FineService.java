package tn.esprit.infini.Pidev.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.Repository.FineRepository;
import tn.esprit.infini.Pidev.entities.Fine;

import java.util.List;

@Service
@AllArgsConstructor
public class FineService implements  IFine {
    FineRepository fineRepository;
    @Override
    public Fine addFine(Fine fine) {

        return  fineRepository.save(fine);
    }

    @Override
    public List<Fine> retrieveAllFines() {

        return fineRepository.findAll();
    }

    @Override
    public Fine updateFine(Fine fine) {

        return fineRepository.save(fine);
    }

    @Override
    public Fine retrieveFine(Integer idFine) {
        return fineRepository.findById(idFine).get();
    }

    @Override
    public void deleteFine(Integer idFine) {
        fineRepository.deleteById(idFine);

    }
}
