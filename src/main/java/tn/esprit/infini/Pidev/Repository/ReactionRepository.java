package tn.esprit.infini.Pidev.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.infini.Pidev.entities.Pack;
import tn.esprit.infini.Pidev.entities.Reaction;

public interface ReactionRepository  extends JpaRepository<Reaction, Integer> {
    Reaction findByIdUserAndPackIdPack (int idUser, int idPack);
    Reaction findById (int id);
}
