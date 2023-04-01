package tn.esprit.infini.Pidev.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.infini.Pidev.entities.Account;
import tn.esprit.infini.Pidev.entities.Credit;
import tn.esprit.infini.Pidev.entities.Transaction;

import java.util.List;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> getTransactionByAccounts(Account account);
    List <Transaction> getTransactionBycredit(Credit credit);
}
