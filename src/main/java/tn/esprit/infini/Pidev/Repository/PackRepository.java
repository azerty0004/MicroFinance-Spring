package tn.esprit.infini.Pidev.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.infini.Pidev.entities.Cart;
import tn.esprit.infini.Pidev.entities.Pack;

@Repository
public interface PackRepository extends JpaRepository<Pack, Integer> {
}
