package tn.esprit.infini.Pidev.Services;

import tn.esprit.infini.Pidev.entities.Account;
import tn.esprit.infini.Pidev.entities.Bill;

import java.util.List;

public interface IBill {
    Bill addbill(Bill bill);
    List<Bill> retrieveAllBills();
    Bill updateBill(Bill bill);
    Bill retrieveBill(Integer idBill);
    void deleteBill(Integer idBill);


}
