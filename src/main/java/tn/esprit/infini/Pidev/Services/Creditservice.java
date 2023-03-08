package tn.esprit.infini.Pidev.Services;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.Repository.Creditrepository;
import tn.esprit.infini.Pidev.Repository.TransactionRepository;
import tn.esprit.infini.Pidev.entities.*;
import tn.esprit.infini.Pidev.exceptions.ResourceNotFoundException;

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
        return (List<Credit>) creditrepository.findAll() ;

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
    public List<Credit> findCreditsByAttributes(Long id, Double amount, Date dateofapplication,Date dateofobtaining, Date dateoffinish, Double interestrate, Integer duration,Statut statut, Guarantor guarantor, TypeCredit typeCredit, Transaction transaction,Insurance insurance) {
        return creditrepository.findCreditsByAttributes(id, amount, dateofapplication,
                dateofobtaining, dateoffinish, interestrate, duration, statut, guarantor,
                typeCredit, transaction, insurance);
    }

    @Override
    public List<Credit> getCreditByiduser(Long userid) {
        return creditrepository.getCreditByiduser(userid);

    }


    @Override
    public Credit addandassingCreditToTransaction(Long idCredit, Long idTransaction) {
        Credit credit = creditrepository.findById(idCredit).orElse(null);
        Transaction transaction = transactionRepository.findById(idTransaction).orElse(null);
        credit.setTransaction(transaction);
        transaction.getCredits().add(credit);
        return creditrepository.save(credit);

    }




    @Override
    public Float newCredit(Credit c) {
    float s=0;
    Long userid= Long.valueOf(getuserByidcredit(c.getId()).getId());
    if (getCreditByiduser(userid).isEmpty()) {
            return s;
        }
    else {

            return s=getCreditByiduser(userid).size()*10/100;

        }


    }
    @Override
    public User  getuserByidcredit(Long creditid){
        return creditrepository.getuserByidcredit(creditid);

    }
    @Override
    public Float  TauxtypeCredit(Credit c){
        float t=0;
        if ((c.getTypeCredit())==TypeCredit.CREDITConsommation){

            return t=1 ;
        }
        else if((c.getTypeCredit())==TypeCredit.CREDITInvestissement){

            return t=2  ;
        }
        else if((c.getTypeCredit())==TypeCredit.CREDITEtudiant){

            return t=3  ;
        }
        else {
            return t=4;
        }

    }

    @Override
    public float calculateFicoScore(Credit c) {
        float ficoscore = 0;
        ficoscore += (c.getAmount() * 30 / 100) + (c.getDuration() * 15 / 100) +(newCredit(c))+(TauxtypeCredit(c)*10/100);

        return ficoscore;
    }
    @Override
    public float CalculMensualité(Credit c){
        float mensualite=0;



        return mensualite;
    }
}
