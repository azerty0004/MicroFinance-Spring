package tn.esprit.infini.Pidev.Services;

import tn.esprit.infini.Pidev.entities.Account;


import java.util.List;

public interface IAccount {
    //CRUD
    Account addAccount(Account account);
    List<Account> retrieveAllAccounts();
    Account updateAccount (Account account);
    void deleteAccount(Integer idAccount);
    //Advanced functions
    Account retrieveAccount (int idAccount);
}
