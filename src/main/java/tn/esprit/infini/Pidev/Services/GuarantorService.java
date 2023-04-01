package tn.esprit.infini.Pidev.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.Repository.GuarantorRepository;
import tn.esprit.infini.Pidev.entities.Guarantor;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class GuarantorService implements IGuarantorService{

    GuarantorRepository guarantorRepository;

    @Override
    public List<Guarantor> retrieveAllGuarantor() {
        return guarantorRepository.findAll();    }


    @Override
    public Guarantor addGuarantor(Guarantor g) throws Exception {

        if (!ValidCin(g.getCinGuarantor())) {
            throw new Exception("Invalid cin format. Must be composed of 8 digits.");
        }
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

    @Override
    public boolean VerifyGuarantor(Guarantor guarantor, double amount) {
        double requiredValue = amount / 5;
        if (guarantor.getSalary() >= requiredValue) {
            return true;
        }
    return false;
    }
        //...
    @Override
    public boolean ValidCin(int cin) {
        String cinString = String.valueOf(cin);
        return cinString.matches("\\d{8}");
    }

   



}




