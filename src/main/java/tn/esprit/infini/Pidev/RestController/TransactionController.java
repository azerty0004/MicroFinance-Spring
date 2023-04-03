package tn.esprit.infini.Pidev.RestController;


import com.google.gson.Gson;
import com.stripe.exception.StripeException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import tn.esprit.infini.Pidev.Services.ITransaction;
import tn.esprit.infini.Pidev.entities.Transaction;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Transaction")
public class TransactionController {
    @Autowired
    private ITransaction iTransaction;
    private static Gson gson = new Gson();

    @GetMapping("/getTransactions")
    List<Transaction> afficher() {
        return iTransaction.retrieveAllTransactions();
    }

    @PostMapping("/addTransaction")
    Transaction ajouter(@RequestBody Transaction transaction) {
        System.out.println(gson.toJson(transaction));
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
    @PostMapping("/confirmTransaction")
    void confirmTransaction(@RequestBody String intentId)
    {
        iTransaction.confirmTransaction(intentId);


    }





    @GetMapping("/GetPaymentModels/{amount}/{numberOfMonths}")
    List<Transaction> ajouter(@PathVariable Long amount, @PathVariable Integer numberOfMonths) {
        return iTransaction.divideTransaction(amount, numberOfMonths);

    }



}
