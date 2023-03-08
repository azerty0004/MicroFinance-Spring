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

    @Query("SELECT c FROM Credit c WHERE "
            + "(:id IS NULL OR c.id = :id) AND "
            + "(:amount IS NULL OR c.amount = :amount) AND "
            + "(:dateofapplication IS NULL OR c.dateofapplication = :dateofapplication) AND "
            + "(:dateofobtaining IS NULL OR c.dateofobtaining = :dateofobtaining) AND "
            + "(:dateoffinish IS NULL OR c.dateoffinish = :dateoffinish) AND "
            + "(:interestrate IS NULL OR c.interestrate = :interestrate) AND "
            + "(:duration IS NULL OR c.duration = :duration) AND "
            + "(:statut IS NULL OR c.statut = :statut) AND "
            + "(:guarantor IS NULL OR c.guarantor = :guarantor) AND "
            + "(:typeCredit IS NULL OR c.typeCredit = :typeCredit) AND "
            + "(:transaction IS NULL OR c.transaction = :transaction) AND "
            + "(:insurance IS NULL OR c.insurance = :insurance)")
    List<Credit> findCreditsByAttributes(
            @Param("id") Long id,
            @Param("amount") Double amount,
            @Param("dateofapplication") Date dateofapplication,
            @Param("dateofobtaining") Date dateofobtaining,
            @Param("dateoffinish") Date dateoffinish,
            @Param("interestrate") Double interestrate,
            @Param("duration") Integer duration,
            @Param("statut") Statut statut,
            @Param("guarantor") Guarantor guarantor,
            @Param("typeCredit") TypeCredit typeCredit,
            @Param("transaction") Transaction transaction,
            @Param("insurance") Insurance insurance);



    @Query ("SELECT c FROM Credit c JOIN Transaction t ON c.transaction.id = t.id JOIN Account a ON  a.transaction.id =t.id JOIN User u ON a.user.id = u.id WHERE (:inputLong is null or a.user.id = :inputLong)")
           List <Credit> getCreditByiduser(@Param("inputLong") Long userid);
    @Query ("SELECT u FROM User u JOIN Account a ON u.id = a.user.id JOIN Transaction t ON  a.transaction.id =t.id JOIN Credit c ON a.transaction.id = c.id WHERE (:inputLong is null or c.transaction.id = :inputLong)")
    User getuserByidcredit(@Param("inputLong") Long credit);


}
