package tn.esprit.infini.Pidev.Services;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.Repository.AccountRepository;
import tn.esprit.infini.Pidev.Repository.InsuranceRepository;
import tn.esprit.infini.Pidev.Repository.PackRepository;
import tn.esprit.infini.Pidev.Repository.UserRepository;
import tn.esprit.infini.Pidev.entities.Account;
import tn.esprit.infini.Pidev.entities.Insurance;
import tn.esprit.infini.Pidev.entities.Pack;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;


@Service
@AllArgsConstructor

public class InsuranceService implements IInsuranceService {
    InsuranceRepository insuranceRepository;
    PackRepository packRepository;
    UserRepository userRepository;
    AccountRepository accountRepository;

    @Override
    public List<Insurance> retrieveAllinsurances() {
        return (List<Insurance>) insuranceRepository.retrieveinsurance();
    }
    @Override
    public List<Insurance> retrieveArchivedinsurance() {
        return (List<Insurance>) insuranceRepository.retrieveArchivedinsurance();
    }

    @Override
    public Insurance addInsurance(Insurance i) {
        i.setArchived(false);
        return insuranceRepository.save(i);
    }
  /*  @Override
public Insurance addInsurance(Insurance i) {
    double totalCost = calculateInsuranceCostWithDiscount(i);
    i.setDeductible(totalCost);
    return insuranceRepository.save(i);
} */


    @Override
    public Insurance updateInsurance(Insurance i) {
        return (Insurance) insuranceRepository.save(i);
    }

    @Override
    public Insurance retrieveInsurance(int idinsurance) {
        return insuranceRepository.findById(idinsurance).get();
    }

    @Override
    public Insurance assignInsuranceToPack(int idinsurance, int idPack) {
        Insurance insurance = insuranceRepository.findById(idinsurance).orElse(null);
        Pack pack = packRepository.findById(idPack).orElse(null);
        insurance.setPack(pack);
        pack.getInsurances().add(insurance);
        return insuranceRepository.save(insurance);
    }

    //fixedRate = 5000
   @Scheduled(cron = "0 0 0 * * *")
    public void archiveExpiredInsurances() {
        // Récupérer les assurances dont la date de fin est dépassée
        List<Insurance> expiredInsurances = insuranceRepository.findByEndinsuranceBefore(new Date());


        for (Insurance insurance : expiredInsurances) {
            System.out.println(insurance.getIdinsurance());
            // Mettre à jour la date de fin d'assurance avec la date actuelle
           // insurance.setEndinsurance(new Date());
            insurance.setArchived(true);
            // Enregistrer la modification dans la base de données
            insuranceRepository.save(insurance);
        }
    }



    /* public float calculateFicoScore(Insurance i) {
         float ficoscore= 0;
         ficoscore += (i.getInsuredAmount() * 5 / 100) +
                 (getMonthsBetweenDates(i.getStartinsurance(), i.getEndinsurance()) * 30 / 100)+
                 (insuranceRepository.numberofinsurancebyuser(i.getUser().getId())*20/100)+
                 (i.getLevelofrisk()*35/100);
         return ficoscore;
     } */
    @Override
    public double calculateInsuranceCost(Insurance insurance) {
        double insuredAmount = insurance.getInsuredAmount();
        double riskFactor = insurance.getLevelofrisk() / 100.0;
        LocalDate startDate = insurance.getStartinsurance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = insurance.getEndinsurance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long policyDuration = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        double deductiblePercent = insurance.getDeductible() / 100.0;
        double fixedFee = 350.0;

        double totalCost = insuredAmount * (1 + riskFactor) * policyDuration * (1 + deductiblePercent) + fixedFee;
        return totalCost;
    }


    public static int getMonthsBetweenDates(Date startDate, Date endDate) {
        LocalDate startLocalDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endLocalDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        long years = ChronoUnit.YEARS.between(startLocalDate, endLocalDate);
        long months = ChronoUnit.MONTHS.between(startLocalDate.plusYears(years), endLocalDate);

        return (int) (years * 12 + months);
    }

    @Override
    public double calculateInsuranceCostWithDiscount(Insurance insurance) {
        double totalCost = calculateInsuranceCost(insurance);
        List<Account> accounts = insuranceRepository.findAccountsByNumberOfInsurances();

        if (accounts.contains(insurance.getCredit().getTransaction().getAccounts()) && accounts.size() > 5 ) {  //&& accounts.size() > 5

            totalCost *= 0.9;
        }
        return totalCost;
    }
}