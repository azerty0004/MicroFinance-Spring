package tn.esprit.infini.Pidev.Services;

import tn.esprit.infini.Pidev.entities.Insurance;

import java.util.List;

public interface IInsuranceService {
    List<Insurance> retrieveAllinsurances();
    Insurance addInsurance(Insurance i);

    Insurance updateInsurance (Insurance i);

    Insurance retrieveInsurance (Long idinsurance);

    void deleteInsurance( Long idinsurance);
}
