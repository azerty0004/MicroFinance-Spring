package tn.esprit.infini.Pidev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.infini.Pidev.entities.Cart;
import tn.esprit.infini.Pidev.entities.Guarantor;

public interface GuarantorRepository extends JpaRepository<Guarantor, Integer> {
}
