package tn.esprit.infini.Pidev.RestController;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import tn.esprit.infini.Pidev.Repository.TransactionRepository;
import tn.esprit.infini.Pidev.Services.ITransaction;
import tn.esprit.infini.Pidev.Services.Icreditservice;
import tn.esprit.infini.Pidev.entities.Credit;
import tn.esprit.infini.Pidev.entities.Transaction;
import java.util.List;

@RestController
@Service
@AllArgsConstructor
public class TransactionController {
    private ITransaction iTransaction;
    private TransactionRepository transactionRepository;
    private Icreditservice icreditService;


    @GetMapping("/getTransactions")
    List<Transaction> afficher() {
        return iTransaction.retrieveAllTransactions();
    }

    @PostMapping("/addTransaction")
    Transaction ajouter(@RequestBody Transaction transaction) {
        return iTransaction.addTransaction(transaction);
    }

    @GetMapping("/getTransactionById/{idTransaction}")
    Transaction afficherAvecId(@PathVariable Long idTransaction){
        return iTransaction.retrieveTransaction(idTransaction);
    }

    @PutMapping("/updateTransaction")
    public Transaction updateTransaction(@RequestBody Transaction transaction) {
        return iTransaction.updateTransaction(transaction);
    }

    @DeleteMapping("/deleteTransaction/{idTransaction}")
    void  deleteTransaction(@PathVariable ("idTransaction") Long idTransaction)
    {
        iTransaction.deleteTransaction(idTransaction);
    }
    @PostMapping("/assign/{transactionId}/credit")
    public ResponseEntity<Credit> addCreditToTransaction(@PathVariable Long transactionId, @RequestBody Credit credit) {
        Transaction transaction = transactionRepository.findById(transactionId).orElse(null);
        if (transaction == null) {
            return ResponseEntity.notFound().build();
        }
        Credit newCredit = icreditService.addCreditToTransaction(credit, transaction);
        return ResponseEntity.ok(newCredit);
    }

}
