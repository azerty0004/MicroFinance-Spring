package tn.esprit.infini.Pidev.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.infini.Pidev.entities.*;

import java.util.Date;
import java.util.List;

@Repository
public interface Creditrepository extends JpaRepository<Credit,Long>, JpaSpecificationExecutor<Credit>
{
    @Query("SELECT c FROM Credit c JOIN Transaction t ON c.transaction.id = t.id JOIN Account a ON  a.transaction.id =t.id JOIN User u ON a.user.id = u.id  WHERE (:inputLong is null or c.id = :inputLong) and " +
            "(:inputDouble is null or c.amount = :inputDouble) and " +
            "(:inputDate is null or c.dateofapplication = :inputDate) and " +
            "(:inputDate is null or c.dateofobtaining = :inputDate) and " +
            "(:inputDate is null or c.dateoffinish = :inputDate) and " +
            "(:inputInteger is null or c.duration = :inputInteger) and " +
            "(:inputStatut is null or c.statut = :inputStatut) and " +
            "(:inputGuarantor is null or c.guarantor = :inputGuarantor) and" +
            "(:inputInsurance is null or c.insurance = :inputInsurance) and" +
            "(:inputTypeCredit is null or c.typeCredit = :inputTypeCredit) and"+
            "(:inputLong is null or t.id = :inputLong) and" +
            "(:inputLong is null or a.id = :inputLong) and"+
            "(:inputLong is null or u.id = :inputLong) ")

            List<Credit> findBySearchParams(@Param("inputLong") Long creditId,
                                    @Param("inputDouble") Double amount,
                                    @Param("inputDate") Date date,
                                    @Param("inputInteger") Integer duration,
                                    @Param("inputStatut") Statut statut,
                                    @Param("inputGuarantor") Guarantor guarantor,
                                    @Param("inputInsurance") Insurance insurance,
                                    @Param("inputTypeCredit")TypeCredit typecredit,
                                    @Param("inputLong")Long transactionid,
                                    @Param("inputLong")Long accountid,
                                    @Param("inputLong")Long userid

                                            );
    @Query ("SELECT c FROM Credit c JOIN Transaction t ON c.transaction.id = t.id JOIN Account a ON  a.transaction.id =t.id JOIN User u ON a.user.id = u.id WHERE (:inputLong is null or a.user.id = :inputLong)")
           List <Credit> getCreditByiduser(@Param("inputLong") Long userid);
    @Query ("SELECT u FROM User u JOIN Account a ON u.id = a.user.id JOIN Transaction t ON  a.transaction.id =t.id JOIN Credit c ON a.transaction.id = c.id WHERE (:inputLong is null or c.transaction.id = :inputLong)")
    User getuserByidcredit(@Param("inputLong") Long credit);


}
