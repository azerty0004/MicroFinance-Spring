package tn.esprit.infini.Pidev.Services;


import tn.esprit.infini.Pidev.entities.Transaction;

import java.util.List;

public interface ITransaction {
    Transaction addTransaction(Transaction transaction);

    List<Transaction> retrieveAllTransactions();

  Transaction updateTransaction(Transaction transaction);

   Transaction retrieveTransaction(Long idTransaction);

    void deleteTransaction(Long idTransaction);

}
