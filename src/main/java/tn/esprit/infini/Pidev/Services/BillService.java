package tn.esprit.infini.Pidev.Services;

import tn.esprit.infini.Pidev.Repository.BillRepository;
import tn.esprit.infini.Pidev.entities.Account;
import tn.esprit.infini.Pidev.entities.Bill;

import java.util.List;

public class BillService implements IBill {
    BillRepository billRepository;
    @Override
    public Bill addbill(Bill bill) {

       return billRepository.save(bill);
    }

    @Override
    public List<Bill> retrieveAllBills() {
        return billRepository.findAll();
    }

    @Override
    public Bill updateBill(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public Bill retrieveBill(Integer idBill) {
        return billRepository.findById(idBill).get();
    }

    @Override
    public void deleteBill(Integer idBill) {
         billRepository.deleteById(idBill);


    }


}
