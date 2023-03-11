package tn.esprit.infini.Pidev.Services;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.And;
import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.Repository.Creditrepository;
import tn.esprit.infini.Pidev.Repository.TransactionRepository;
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
    public List<Credit> findCreditsByAttributes(Long id, Double amount, Date dateofapplication, Date dateofobtaining, Date dateoffinish, Double interestrate, Integer duration, Statut statut, Guarantor guarantor, TypeCredit typeCredit, Transaction transaction, Insurance insurance) {
        return creditrepository.findCreditsByAttributes(id, amount, dateofapplication,
                dateofobtaining, dateoffinish, interestrate, duration, statut, guarantor,
                typeCredit, transaction, insurance);
    }

    @Override
    public List<Credit> getCreditByiduser(Long userid) {
        return creditrepository.getCreditByiduser(userid);

    }


    @Override
    public Credit addandassingCreditToTransaction(Credit credit, Long idTransaction) {
        Credit c = creditrepository.save(credit);
        Transaction transaction = transactionRepository.findById(idTransaction).orElse(null);
        credit.setTransaction(transaction);
        transaction.getCredits().add(credit);
        return creditrepository.save(credit);

    }


    @Override
    public Float newCredit(Credit c) {
        float s = 0;
        Long userid = Long.valueOf(getuserByidcredit(c.getId()).getId());
        if (getCreditByiduser(userid).isEmpty()) {
            return s;}
         else {
            s = getCreditByiduser(userid).size();

            return s;

        }


    }

    @Override
    public User getuserByidcredit(Long creditid) {
        return creditrepository.getuserByidcredit(creditid);

    }

    @Override
    public Float TauxtypeCredit(Credit c) {
        float t = 0;
        if ((c.getTypeCredit()) == TypeCredit.CREDITConsommation) {

            return t = 1;
        } else if ((c.getTypeCredit()) == TypeCredit.CREDITInvestissement) {

            return t = 2;
        } else if ((c.getTypeCredit()) == TypeCredit.CREDITEtudiant) {

            return t = 3;
        } else {
            return t = 4;
        }

    }


    @Override
    public float calculateFicoScore(Credit c) {
        float ficoscore = 0;
        ficoscore += (c.getAmount() * 30 / 100) + (c.getDuration() * 15 / 100) + (newCredit(c) * 10 / 100) + (TauxtypeCredit(c) * 10 / 100);

        return ficoscore;
    }

    @Override
    public double InterestRateCalculator(Credit credit) {
      double TMM=8.02/100;
      double interestrate=0;
      float  score= calculateFicoScore(credit);
        if ((score >= 580 && score <= 669)){
            interestrate=TMM+23/100;

            credit.setInterestrate(interestrate);
        }
        else if ((score >= 670 && score <= 739)){
            credit.setInterestrate(TMM+15/100);}
        else if ((score >= 740 && score <= 799)) {
            credit.setInterestrate(TMM + 11 / 100);
        }
        else  if ((score >= 580 && score <= 669)){
           credit.setInterestrate(TMM+6/100);
            }
        return interestrate;

    }

    @Override
    public double CalculMensualitéfixe(Credit c) {
        double mensualite = 0;
        double a = 0;
        double b = 0;
           a = (c.getAmount() * (InterestRateCalculator(c) / 12));

            b = Math.pow(1 + (InterestRateCalculator(c) / 12), -c.getDuration());
            mensualite = a / b;

        return mensualite; }

    @Override
    public List<Double> CalculMensualitévariable(Credit c) {
        double montantrestant=c.getAmount();
        double amortissement = (c.getAmount() / c.getDuration());
        double interestrateformounth=0;
        double mensualite=0;
        List<Double> listmensualité=new ArrayList<>();
        while (montantrestant != 0) {
                interestrateformounth = montantrestant * (InterestRateCalculator(c) / 12);
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
        while (montantrestant != 0) {
            interestrateformounth = montantrestant * (InterestRateCalculator(c) / 12);
            mensualite = amortissement + interestrateformounth;
            montantrestant = montantrestant - mensualite;
            listtauxinterets.add(interestrateformounth);
        }
        return listtauxinterets;

    }

    @Override
    public void ValidateCredit(Credit c) {
        float score = 0;
        float TMM =8/100;
        c.setStatut(Statut.EN_ATTENTE);

        score = calculateFicoScore(c);

        if (Optional.ofNullable(c.getGuarantor()).isPresent()) {

            if (score < 580) {
                c.setStatut(Statut.Non_Approuvé);
            } else if ((score >= 580 && score <= 669) /*bank.amount*/ )  {
                c.setStatut(Statut.Approuvé);
                // c.setInterestrate(TMM+22.7/100);x
            } else if ((score >= 670 && score <= 739)) {
                c.setStatut(Statut.Approuvé);
                // c.setInterestrate(TMM+7.432/100);
            } else if ((score >= 740 && score <= 799)) {
                c.setStatut(Statut.Approuvé);
                // c.setInterestrate(TMM+ 3.828/100);
            } else c.setStatut(Statut.Approuvé);
           // c.setInterestrate(TMM + 2 / 100);
            } else c.setStatut(Statut.Non_Approuvé);
        }

    }


