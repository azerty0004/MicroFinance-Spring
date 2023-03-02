package tn.esprit.infini.Pidev.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.entities.Insurance;
import tn.esprit.infini.Pidev.Repository.InsuranceRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class InsuranceService implements IInsuranceService {
    InsuranceRepository insuranceRepository;

    @Override
    public List<Insurance> retrieveAllinsurances() {
        return (List<Insurance>) insuranceRepository.findAll();
    }

    @Override
    public Insurance addInsurance(Insurance i) {
        return insuranceRepository.save(i);

    }


    @Override
    public Insurance updateInsurance(Insurance i) {
        return (Insurance) insuranceRepository.save(i);
    }

    @Override
    public Insurance retrieveInsurance(Long idinsurance) {
        return insuranceRepository.findById(Math.toIntExact(idinsurance)).get();
    }

    @Override
    public void deleteInsurance(Long idinsurance)
    {
        insuranceRepository.deleteById(Math.toIntExact(idinsurance));
    }

}
