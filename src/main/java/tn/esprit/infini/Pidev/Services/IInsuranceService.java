package tn.esprit.infini.Pidev.Services;

import tn.esprit.infini.Pidev.entities.Insurance;

import java.util.List;

public interface IInsuranceService {
    List<Insurance>  retrieveAllinsurances();
    Insurance addInsurance(Insurance i);

    Insurance updateInsurance (Insurance i);

    Insurance retrieveInsurance (int idinsurance);

    Insurance assignInsuranceToPack(int idinsurance, int idPack);

    double calculateInsuranceCost(Insurance insurance);

    double calculateInsuranceCostWithDiscount(Insurance insurance);


    /*0Insurance assignInsuranceToPack(int idinsurance, int idPack); */
}
