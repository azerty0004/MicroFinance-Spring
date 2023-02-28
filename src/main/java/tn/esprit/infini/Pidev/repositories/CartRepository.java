package tn.esprit.infini.Pidev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.infini.Pidev.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
