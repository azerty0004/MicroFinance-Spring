package tn.esprit.infini.Pidev.RestController;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.infini.Pidev.Services.Creditservice;
import tn.esprit.infini.Pidev.Services.Icreditservice;
import tn.esprit.infini.Pidev.entities.*;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@RestController
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

    @GetMapping("/getCreditById/{idCredit}")
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

    @GetMapping("/search")
    public ResponseEntity<List<Credit>> searchCreditsByAttributes(
            @RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "amount", required = false) Double amount,
            @RequestParam(name = "dateofapplication", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateofapplication,
            @RequestParam(name = "dateofobtaining", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateofobtaining,
            @RequestParam(name = "dateoffinish", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateoffinish,
            @RequestParam(name = "interestrate", required = false) Double interestrate,
            @RequestParam(name = "duration", required = false) Integer duration,
            @RequestParam(name = "statut", required = false) Statut statut,
            @RequestParam(name = "guarantor", required = false) Guarantor guarantor,
            @RequestParam(name = "typeCredit", required = false) TypeCredit typeCredit,
            @RequestParam(name = "transaction", required = false) Transaction transaction,
            @RequestParam(name = "insurance", required = false) Insurance insurance) {
        List<Credit> credits = icreditservice.findCreditsByAttributes(id, amount, dateofapplication,
                dateofobtaining, dateoffinish, interestrate, duration, statut, guarantor,
                typeCredit, transaction, insurance);
        return ResponseEntity.ok(credits);
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




