package tn.esprit.infini.Pidev.RestController;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import tn.esprit.infini.Pidev.Services.Icreditservice;
import tn.esprit.infini.Pidev.entities.*;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/credit")
public class CreditController {
    private Icreditservice icreditservice;


    @GetMapping("/getCredit")
    List<Credit> afficher() {
        return  icreditservice.retrieveAllCredits();
    }

    @PostMapping("/addCredit")
    Credit ajouter(@RequestBody Credit credit) {
        return icreditservice.addCredit(credit);
    }

    @GetMapping("/getCreditById/{id}")
    Credit afficherAvecId(@PathVariable Long idCredit) {
        return icreditservice.retrieveCredit(idCredit);
    }

    @PutMapping("/updateCredit")
    public Credit updateCredit(@RequestBody Credit credit) {
        return icreditservice.updateCredit(credit);
    }

    @DeleteMapping("/deleteCredit/{idCredit}")
    void deleteCredit(@PathVariable("idCredit") Long idCredit) {
        icreditservice.deleteCredit(idCredit);
    }

    @GetMapping("/creditsbysearchparams")
    public List<Credit> findCreditBySearchParams(
            @RequestParam(value = "creditId", required = false) Long creditId,
            @RequestParam(value = "amount", required = false) Double amount,
            @RequestParam(value = "date", required = false) Date date,
            @RequestParam(value = "duration", required = false) Integer duration,
            @RequestParam(value = "statut", required = false) Statut statut,
            @RequestParam(value = "guarantor", required = false) Guarantor guarantor,
            @RequestParam(value = "insurance", required = false) Insurance insurance,
            @RequestParam(value = "typecredit", required = false) TypeCredit typeCredit,
            @RequestParam(value = "idtransaction", required = false) Long idtransaction,
            @RequestParam(value = "idaccount", required = false) Long idaccount,
            @RequestParam(value = "iduser", required = false) Long iduser) {
        return icreditservice.findCreditBySearchParams(creditId, amount, date, duration, statut, guarantor, insurance, typeCredit, idtransaction, idaccount, iduser);
    }

    @GetMapping("/getcreditsbyiduser")
    public List<Credit> getCreditByiduser(@RequestParam(value = "userid", required = false) Long userid) {
        return icreditservice.getCreditByiduser(userid);
    }


    @PutMapping("/assignCreditTran/{creditId}/{transactionid}")
    public Credit addandassingCreditToTransaction(@PathVariable("creditId") Long creditId, @PathVariable("transactionid") Long transactionid) {
        return icreditservice.addandassingCreditToTransaction(creditId, transactionid);
    }
    @GetMapping("/getuserbyidcredit")
    public User  getuserByidcredit(@RequestParam(value = "creditId", required = false) Long creditId) {
                return icreditservice.getuserByidcredit(creditId);
        }


}




