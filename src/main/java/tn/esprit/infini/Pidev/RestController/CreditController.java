package tn.esprit.infini.Pidev.RestController;

import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import tn.esprit.infini.Pidev.Services.Icreditservice;
import tn.esprit.infini.Pidev.entities.Credit;
import tn.esprit.infini.Pidev.entities.Guarantor;
import tn.esprit.infini.Pidev.entities.Statut;

import java.util.Date;
import java.util.List;
@RestController
@AllArgsConstructor
public class CreditController {
    private Icreditservice icreditservice;

    @GetMapping("/getCredit")
    List<Credit> afficher() {
        return icreditservice.retrieveAllCredits();
    }

    @PostMapping("/addCredit")
    Credit ajouter(@RequestBody Credit credit) {
        return icreditservice.addCredit(credit);
    }

    @GetMapping("/getCreditById/{id}")
    Credit afficherAvecId(@PathVariable Long idCredit){
        return icreditservice.retrieveCredit(idCredit);
    }

    @PutMapping("/updateCredit")
    public Credit updateCredit(@RequestBody Credit credit) {
        return icreditservice.updateCredit(credit);
    }

    @DeleteMapping("/deleteCredit/{idCredit}")
    void deleteCredit(@PathVariable ("idCredit") Long idCredit)
    {
        icreditservice.deleteCredit(idCredit);
    }

    @GetMapping("/credits")
    public List<Credit> findBySearchParams(
            @RequestParam(value = "creditId", required = false) Long creditId,
            @RequestParam(value = "amount", required = false) Double amount,
            @RequestParam(value = "date", required = false) Date date,
            @RequestParam(value = "duration", required = false) Integer duration,
            @RequestParam(value = "statut", required = false) Statut statut,
            @RequestParam(value = "guarantor", required = false) Guarantor guarantor
    ) {
        return icreditservice.findBySearchParams(creditId, amount, date, duration, statut, guarantor);
    }
}



