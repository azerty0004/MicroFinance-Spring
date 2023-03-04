package tn.esprit.infini.Pidev.RestController;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.infini.Pidev.Services.IAccount;
import tn.esprit.infini.Pidev.entities.Account;
import tn.esprit.infini.Pidev.entities.User;

import java.util.List;

@RestController
@AllArgsConstructor
public class AccountController {
    private IAccount iAccount;
    @GetMapping("/getAccounts")
    List<Account> afficher() {return iAccount.retrieveAllAccounts();}
    @PostMapping("/addAccount")
    Account ajouter(@RequestBody Account account) {
        return iAccount.addAccount(account);
    }
    @GetMapping("/getAccountById/{id}")
    Account afficherAvecId(@PathVariable int idAccount){
        return iAccount.retrieveAccount(idAccount);
    }
    @PutMapping("/updateAccount")
    public Account updateUser(@RequestBody Account account) {
        return iAccount.updateAccount(account);
    }
}
