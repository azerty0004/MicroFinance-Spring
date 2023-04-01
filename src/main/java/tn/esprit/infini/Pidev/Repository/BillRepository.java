package tn.esprit.infini.Pidev.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.infini.Pidev.entities.Bill;
import jakarta.persistence.*;


public interface BillRepository extends JpaRepository<Bill,Integer> {
}
