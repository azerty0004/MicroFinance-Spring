package tn.esprit.infini.Pidev.Services;

import tn.esprit.infini.Pidev.Repository.AccountRepository;
import tn.esprit.infini.Pidev.entities.Account;


import java.util.List;

public class AccountService implements IAccount {
    AccountRepository AR;
    //CRUD
    @Override
    public Account addAccount(Account account) {return AR.save(account);}
    @Override
    public List<Account> retrieveAllAccounts() {return (List<Account>) AR.findAll();}
    @Override
    public Account updateAccount(Account account) {return AR.save(account);}
    @Override
    public void deleteAccount(Integer idAccount) {AR.deleteById(idAccount);}
    //Advanced functions
    @Override
    public Account retrieveAccount(int idAccount) {return AR.findById(idAccount).get();}

}
