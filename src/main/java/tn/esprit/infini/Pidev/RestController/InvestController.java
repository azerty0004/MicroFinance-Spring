package tn.esprit.infini.Pidev.RestController;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.infini.Pidev.Services.Iinvestservice;
import tn.esprit.infini.Pidev.entities.Invest;

import java.util.List;
@RestController
@AllArgsConstructor
public class InvestController {
    private Iinvestservice iinvestservice;

    @GetMapping("/getInvest")
    List<Invest> afficher() {
        return iinvestservice.retrieveAllInvests();
    }

    @PostMapping("/addInvest")
    Invest ajouter(@RequestBody Invest invest) {
        return iinvestservice.addInvest(invest);
    }

    @GetMapping("/getInvestById/{id}")
    Invest afficherAvecId(@PathVariable Long idInvest){
        return iinvestservice.retrieveInvest(idInvest);
    }

    @PutMapping("/updateInvest")
    public Invest updateInvest(@RequestBody Invest invest) {
        return iinvestservice.updateInvest(invest);
    }

    @DeleteMapping("/deleteInvest/{idInvest}")
    void deleteInvest(@PathVariable ("idInvest") Long idInvest)
    {
        iinvestservice.deleteInvest(idInvest);
    }
}

