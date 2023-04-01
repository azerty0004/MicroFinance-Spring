package tn.esprit.infini.Pidev.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.Repository.GuarantorRepository;
import tn.esprit.infini.Pidev.entities.Guarantor;

import java.util.List;
@Service
@AllArgsConstructor
public class GuarantorService implements IGuarantorService{

    GuarantorRepository guarantorRepository;

    @Override
    public List<Guarantor> retrieveAllGuarantor() {
        return guarantorRepository.findAll();    }


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
