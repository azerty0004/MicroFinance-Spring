package tn.esprit.infini.Pidev.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.Repository.TransactionRepository;
import tn.esprit.infini.Pidev.entities.Transaction;

import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService implements ITransaction {
    TransactionRepository transactionRepository;
    @Override
    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> retrieveAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction retrieveTransaction(Integer idTransaction) {
        return transactionRepository.findById(idTransaction).get();
    }

    @Override
    public void deleteTransaction(Integer idTransaction) {
        transactionRepository.deleteById(idTransaction);

    }
}
