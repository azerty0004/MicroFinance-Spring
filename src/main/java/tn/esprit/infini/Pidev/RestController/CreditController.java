package tn.esprit.infini.Pidev.RestController;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.infini.Pidev.Services.Icreditservice;
import tn.esprit.infini.Pidev.entities.Credit;

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
}

