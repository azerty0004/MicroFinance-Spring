package tn.esprit.infini.Pidev.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.infini.Pidev.entities.Credit;
import tn.esprit.infini.Pidev.entities.Statut;
import tn.esprit.infini.Pidev.entities.Guarantor;
import tn.esprit.infini.Pidev.entities.Insurance;

import java.util.Date;
import java.util.List;

@Repository
public interface Creditrepository extends JpaRepository<Credit,Long>, JpaSpecificationExecutor<Credit>
{
    @Query("SELECT c FROM Credit c  WHERE (:creditId is null or c.id = :creditId) and " +
            "(:inputDouble is null or c.amount = :inputDouble) and " +
            "(:inputDate is null or c.dateofapplication = :inputDate) and " +
            "(:inputDate is null or c.dateofobtaining = :inputDate) and " +
            "(:inputDate is null or c.dateoffinish = :inputDate) and " +
            "(:inputInteger is null or c.duration = :inputInteger) and " +
            "(:inputStatut is null or c.statut = :inputStatut) and " +
            "(:inputGuarantor is null or c.guarantor = :inputGuarantor) and" +
            "(:inputInsurance is null or c.insurance = :inputInsurance)")
    List<Credit> findBySearchParams(@Param("creditId") Long creditId,
                                    @Param("inputDouble") Double amount,
                                    @Param("inputDate") Date date,
                                    @Param("inputInteger") Integer duration,
                                    @Param("inputStatut") Statut statut,
                                    @Param("inputGuarantor") Guarantor guarantor,
                                    @Param("inputInsurance") Insurance insurance);
    Credit findByAmount(double amount);

}
