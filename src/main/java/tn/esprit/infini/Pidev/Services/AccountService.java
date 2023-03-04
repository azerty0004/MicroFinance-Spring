package tn.esprit.infini.Pidev.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.Repository.AccountRepository;
import tn.esprit.infini.Pidev.Repository.UserRepository;
import tn.esprit.infini.Pidev.entities.Account;
import tn.esprit.infini.Pidev.entities.TypeUser;


import java.util.List;
@Service
@AllArgsConstructor
public class AccountService implements IAccount {
    AccountRepository AR;
    UserRepository UR;
    //CRUD
    @Override
    public Account addAccount(Account account) {
        UR.findByAccount(account).setType(TypeUser.Casual_Client);
        return AR.save(account);}
    @Override
    public List<Account> retrieveAllAccounts() {return (List<Account>) AR.findAll();}
    @Override
    public Account updateAccount(Account account) {return AR.save(account);}
    @Override
    public void deleteAccount(Integer idAccount) {AR.deleteById(idAccount);}
    //Advanced functions
    @Override
    public Account retrieveAccount(int idAccount) {return AR.findById(idAccount).get();}
    @Override
    public void addBalance(Account account, float amount) {AR.findById(account.getId()).get().setBalance(AR.findById(account.getId()).get().getBalance()+amount);}
    @Override
    public void substractBalance(Account account, float amount) {AR.findById(account.getId()).get().setBalance(AR.findById(account.getId()).get().getBalance()-amount);}


}
