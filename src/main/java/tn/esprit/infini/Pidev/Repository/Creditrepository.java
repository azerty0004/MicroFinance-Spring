package tn.esprit.infini.Pidev.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.infini.Pidev.entities.Credit;
import tn.esprit.infini.Pidev.entities.Statut;

import java.util.Date;
import java.util.List;

@Repository
public interface Creditrepository extends JpaRepository<Credit,Long>
{
    @Query("SELECT c FROM Credit c WHERE c.dateofobtaining > :date")

    List<Credit> findByDateOfObtainingGreaterThan(@Param("date") Date date);
    @Query("SELECT c FROM Credit c WHERE c.statut = :statut")
    List<Credit> findByStatuts(@Param("statut") Statut statut );



}
