package tn.esprit.infini.Pidev.Services;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.And;
import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.Repository.Creditrepository;
import tn.esprit.infini.Pidev.Repository.TransactionRepository;
import tn.esprit.infini.Pidev.Repository.UserRepository;
import tn.esprit.infini.Pidev.entities.*;
import tn.esprit.infini.Pidev.exceptions.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class Creditservice implements Icreditservice {

    private Creditrepository creditrepository;
    private TransactionRepository transactionRepository;
    private UserRepository userRepository;

    @Override
    public List<Credit> retrieveAllCredits() {
        return (List<Credit>) creditrepository.findAll();

    }

    @Override
    public Credit addCredit(Credit c) {
        return creditrepository.save(c);
    }

    @Override
    public Credit updateCredit(Credit c) {
        return creditrepository.save(c);
    }

    @Override
    public Credit retrieveCredit(Long id) {

        Optional<Credit> credit = creditrepository.findById(id);

        if (credit.isPresent()) {
            return creditrepository.findById(id).get();
        } else {
            throw new ResourceNotFoundException("Credit not found with id " + id);
        }
    }


    @Override
    public void deleteCredit(Long id) {
        Optional<Credit> credit = creditrepository.findById(id);
        if (credit.isPresent()) {
            creditrepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Credit not found with id " + id);


        }

    }

    @Override
    public List<Credit> findCreditsByAttributes(Long id, Double amount, Date dateofapplication, Date dateofobtaining, Date dateoffinish, Double interestrate, Integer duration, Statut statut, Guarantor guarantor, TypeCredit typeCredit, Insurance insurance) {
        return creditrepository.findCreditsByAttributes(id, amount, dateofapplication,
                dateofobtaining, dateoffinish, interestrate, duration, statut, guarantor,typeCredit,insurance);
    }

    @Override
    public List<Credit> getCreditByiduser(Long userid) {
        return creditrepository.getCreditByiduser(userid);

    }





    @Override
    public Float newCredit(Long idCredit) {
        float s = 0;
        int  numberOfCreditsIretardé= 0;
        int numberOfCreditsremboursé=0;

        Long userid = Long.valueOf(userRepository.findUserByCreditId(idCredit).getId());
        if (getCreditByiduser(userid).isEmpty()) {
            return s;}
         else {
            for (Credit credit:getCreditByiduser(userid)
                 ) {  if (credit.getStatut() == Statut.EN_RETARDISSEMENT) {
                numberOfCreditsIretardé++;
            }
                if (credit.getStatut() == Statut.REMBOURSE) {
                    numberOfCreditsremboursé++;
                }


              // s=numberOfCreditsremboursé- numberOfCreditsIretardé;
                s=getCreditByiduser(userid).size();
            }

            return s;

        }


    }



    @Override
    public Integer TauxtypeCredit(Credit c) {

        if ((c.getTypeCredit()) == TypeCredit.CREDITConsommation) {

            return 1 ;
        } else if ((c.getTypeCredit()) == TypeCredit.CREDITInvestissement) {

            return 2;
        } else if ((c.getTypeCredit()) == TypeCredit.CREDITEtudiant) {

            return  3;
        } else {
            return  4;
        }

    }


    @Override
    public float calculateFicoScore(Credit c) {
        float ficoscore = 0;
        ficoscore += (c.getAmount() * 0.3) + (c.getDuration() * 0.15) + (newCredit(c.getId()) *0.1) + (TauxtypeCredit(c) * 0.1);

        return ficoscore;
    }

    @Override
    public double InterestRateCalculator(Credit credit) {
        double TMM = 0.802 ;
        double interestrate = 0;
        float score = calculateFicoScore(credit);
        if ((score >= 580 && score <= 669)) {
            interestrate = TMM + 2.3;
            credit.setInterestrate(interestrate);
            creditrepository.save(credit);

            return interestrate;
        } else if ((score >= 670 && score <= 739)) {
            interestrate=TMM+1.5;
            credit.setInterestrate(interestrate);
            creditrepository.save(credit);

            return interestrate;
        } else if ((score >= 740 && score <= 799)) {
            interestrate=TMM+1.1;
            credit.setInterestrate(interestrate);
            creditrepository.save(credit);

            return interestrate;
        } else if ((score >= 580 && score <= 669)) {
            interestrate=TMM+0.6;
            credit.setInterestrate(interestrate);
            creditrepository.save(credit);

            return interestrate;

        }
        else {
            interestrate=TMM+0.3;
            credit.setInterestrate(interestrate);
            creditrepository.save(credit);


            return interestrate; }
        }





    @Override
    public double CalculMensualitéfixe(Credit c) {
        double mensualite = 0;
        double a = 0;
        double b = 0;
           a = (c.getAmount() * (c.getInterestrate() / 12));

            b = 1-Math.pow(1 + (c.getInterestrate() / 12), -c.getDuration());
            mensualite = a / b;

        return mensualite; }

    @Override
    public List<Double> CalculMensualitévariable(Credit c) {
        double montantrestant=c.getAmount();
        double amortissement = (c.getAmount() / c.getDuration());
        double interestrateformounth=0;
        double mensualite=0;
        List<Double> listmensualité=new ArrayList<>();
        for (int i = 1; i <= c.getDuration(); i++) {
                interestrateformounth = montantrestant * (c.getInterestrate() / 12);
                mensualite = amortissement + interestrateformounth;
                montantrestant = montantrestant - mensualite;
                listmensualité.add(mensualite);
            }
        return listmensualité;
    }

    @Override
    public List<Double> listetauxinterets(Credit c) {
        double montantrestant=c.getAmount();
        double amortissement = (c.getAmount() / c.getDuration());
        double interestrateformounth=0;
        double mensualite=0;
        List<Double> listtauxinterets=new ArrayList<>();

        for (double i = 1; i <= c.getDuration(); i++) {
            interestrateformounth = montantrestant * (c.getInterestrate() / 12);
            if (interestrateformounth > 0) {
                listtauxinterets.add(interestrateformounth);}
            else listtauxinterets.add((double) 0);
            mensualite = amortissement + interestrateformounth;
            montantrestant = montantrestant - mensualite;
        }

        return listtauxinterets;

    }

    @Override
    public void ValidateCredit(Credit c) {
        float score = 0;
        float TMM = (float) 8.02;
        c.setStatut(Statut.EN_ATTENTE);

        score = calculateFicoScore(c);

        if (Optional.ofNullable(c.getGuarantor()).isPresent()) {

            if (score < 580) {
                c.setStatut(Statut.Non_Approuvé);
                creditrepository.save(c);
            } else if ((score >= 580 && score <= 669) /*bank.amount*/ )  {
                c.setStatut(Statut.Approuvé);
                creditrepository.save(c);
                // c.setInterestrate(TMM+22.7/100);x
            } else if ((score >= 670 && score <= 739)) {
                c.setStatut(Statut.Approuvé);
                creditrepository.save(c);
                // c.setInterestrate(TMM+7.432/100);
            } else if ((score >= 740 && score <= 799)) {
                c.setStatut(Statut.Approuvé);
                creditrepository.save(c);
                // c.setInterestrate(TMM+ 3.828/100);
            } else c.setStatut(Statut.Approuvé);
            creditrepository.save(c);
           // c.setInterestrate(TMM + 2 / 100);
            } else c.setStatut(Statut.Non_Approuvé);
            creditrepository.save(c);
        }

    }


