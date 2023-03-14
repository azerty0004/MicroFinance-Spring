package tn.esprit.infini.Pidev.RestController;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.infini.Pidev.Services.Creditservice;
import tn.esprit.infini.Pidev.Services.ITransaction;
import tn.esprit.infini.Pidev.Services.Icreditservice;
import tn.esprit.infini.Pidev.entities.*;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@RestController
public class CreditController {
    private Icreditservice icreditservice;
    private ITransaction iTransaction;


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
            @RequestParam(name = "insurance", required = false) Insurance insurance) {
        List<Credit> credits = icreditservice.findCreditsByAttributes(id, amount, dateofapplication,
                dateofobtaining, dateoffinish, interestrate, duration, statut, guarantor,
                typeCredit, insurance);
        return ResponseEntity.ok(credits);
    }

    @GetMapping("/getcreditsbyiduser/{userId}")
    public List<Credit> getCreditByiduser(@PathVariable( "userId") Long userId) {
        return icreditservice.getCreditByiduser(userId);
    }

    @GetMapping("/newcredit/{creditId}")
    public Float newCredit(@PathVariable( "creditId") Long creditId){
        return icreditservice.newCredit(creditId);
    }
        @GetMapping("/TauxtypeCredit")
    public Integer TauxtypeCredit(@RequestBody Credit c) {return icreditservice.TauxtypeCredit(c);}
    @GetMapping("/calculateFicoScore")
    public float calculateFicoScore(@RequestBody Credit c){return icreditservice.calculateFicoScore(c);}
    @GetMapping("/CalculMensualitévariable")
    public List<Double> CalculMensualitévariable(@RequestBody Credit c){return icreditservice.CalculMensualitévariable(c);}
    @GetMapping("/CalculMensualitéfixe")
    public double CalculMensualitéfixe(@RequestBody Credit c){return icreditservice.CalculMensualitéfixe(c);}
    @GetMapping("/InterestRateCalculator")

    public double InterestRateCalculator(@RequestBody Credit credit){return icreditservice.InterestRateCalculator(credit);}
    @GetMapping("/listetauxinterets")
     public List<Double> listetauxinterets(@RequestBody Credit c){return icreditservice.listetauxinterets(c);}
    @PutMapping("/validatecredit")
    public void ValidateCredit(@RequestBody Credit c){
        icreditservice.ValidateCredit(c);
    }
}




