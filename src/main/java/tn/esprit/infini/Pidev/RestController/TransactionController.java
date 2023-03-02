package tn.esprit.infini.Pidev.RestController;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.infini.Pidev.Services.ITransaction;
import tn.esprit.infini.Pidev.entities.Transaction;

import java.util.List;

@RestController
@AllArgsConstructor
public class TransactionController {
    private ITransaction iTransaction;

    @GetMapping("/getTransactions")
    List<Transaction> afficher() {
        return iTransaction.retrieveAllTransactions();
    }

    @PostMapping("/addTransaction")
    Transaction ajouter(@RequestBody Transaction transaction) {
        return iTransaction.addTransaction(transaction);
    }

    @GetMapping("/getTransactionById/{id}")
    Transaction afficherAvecId(@PathVariable int idTransaction){
        return iTransaction.retrieveTransaction(idTransaction);
    }

    @PutMapping("/updateTransaction")
    public Transaction updatePack(@RequestBody Transaction transaction) {
        return iTransaction.updateTransaction(transaction);
    }

    @DeleteMapping("/deleteTransaction/{idTransaction}")
    void  deletePack(@PathVariable ("idTransaction") Integer idTransaction)
    {
        iTransaction.deleteTransaction(idTransaction);
    }

}
