package tn.esprit.infini.Pidev.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import tn.esprit.infini.Pidev.entities.Guarantor;
import tn.esprit.infini.Pidev.repositories.GuarantorRepository;

import java.util.List;

@Service
@AllArgsConstructor

public class GuarantorService implements IGuarantorService {

    GuarantorRepository guarantorRepository;

    @Override
    public List<Guarantor> retrieveAllGuarantor() {

        return (List<Guarantor>) guarantorRepository.findAll();    }

    @Override
    public Guarantor addGuarantor(Guarantor g) {

        return guarantorRepository.save(g);
    }

    @Override
    public Guarantor updateGuarantor(Guarantor g) {

        return guarantorRepository.save(g);
    }

    @Override
    public Guarantor retrieveGuarantor(Integer idGurantor) {

        return guarantorRepository.findById(idGurantor).get();
    }

    @Override
    public void deleteGuarantor(Integer idGurantor) {
        guarantorRepository.deleteById(idGurantor);

    }
}
