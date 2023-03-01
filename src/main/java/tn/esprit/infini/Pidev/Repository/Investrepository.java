package tn.esprit.infini.Pidev.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.infini.Pidev.entities.Credit;
import tn.esprit.infini.Pidev.entities.Invest;
import tn.esprit.infini.Pidev.entities.Statut;

import java.util.List;
@Repository
public interface Investrepository extends JpaRepository<Invest,Long> {
    @Query(value ="SELECT i FROM Invest i WHERE i.interestrate = :interestrate")
    List<Invest> findByInterestrateLike(@Param("interestrate") double interestrate );

}
