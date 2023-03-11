package tn.esprit.infini.Pidev.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.infini.Pidev.entities.Pack;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface PackRepository extends JpaRepository<Pack, Integer> {


    Pack findByIdPack(Integer idPack);
    // Pack findByPrice (double price);

    @Query(value = "select p from Pack p order by p.likes desc")
    List<Pack> findMostLikedPacks(@Param("likes") int likes);
    @Query(value = "select p from Pack p order by p.dislikes desc")
    List<Pack> findMostDislikedPacks(@Param("dislikes")int dislikes);
}
