package tn.esprit.infini.Pidev.RestController;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tn.esprit.infini.Pidev.Services.Iinvestservice;
import tn.esprit.infini.Pidev.entities.Credit;
import tn.esprit.infini.Pidev.entities.Invest;
import tn.esprit.infini.Pidev.entities.Statut;
import tn.esprit.infini.Pidev.entities.User;

import java.util.Date;
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

    @GetMapping("/getInvestById/{idInvest}")
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
    @GetMapping("/getInvestByparams/")
    Specification<Invest> searchInvests(@RequestParam(required = false) Double minAmount,
                                        @RequestParam(required = false) Double maxAmount,
                                        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date minDateOfApplication,
                                        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date maxDateOfApplication,
                                        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date minDateOfObtaining,
                                        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date maxDateOfObtaining,
                                        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date minDateOfFinish,
                                        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date maxDateOfFinish,
                                        @RequestParam(required = false) Double minInterestRate,
                                        @RequestParam(required = false) Double maxInterestRate,
                                        @RequestParam(required = false) Integer minMonths,
                                        @RequestParam(required = false) Integer maxMonths,
                                        @RequestParam(required = false) Statut statut)





    { return iinvestservice.searchInvests(minAmount,maxAmount,minDateOfApplication,maxDateOfApplication,minDateOfObtaining,maxDateOfObtaining,minDateOfFinish,maxDateOfFinish,minInterestRate,maxInterestRate,minMonths,maxMonths,statut);


    }
    @GetMapping("/getinvestsbyiduser/userid")
    public List<Invest> getInvestByiduser(@PathVariable( "userid") Long userid) {
        return iinvestservice.getInvestByiduser(userid);
    }

}

