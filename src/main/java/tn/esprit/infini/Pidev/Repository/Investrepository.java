package tn.esprit.infini.Pidev.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.infini.Pidev.entities.Invest;
import tn.esprit.infini.Pidev.entities.User;

import java.util.List;
@Repository
public interface Investrepository extends JpaRepository<Invest,Long> {
    @Query ("SELECT i FROM Invest i JOIN Transaction t ON i.transaction.id = t.id JOIN Account a ON  a.transaction.id =t.id JOIN User u ON a.user.id = u.id WHERE (:inputLong is null or a.user.id = :inputLong)")
    List <Invest> getInvestByiduser(@Param("inputLong") Long userid);
        @Query ("SELECT u FROM User u JOIN Account a ON u.id = a.user.id JOIN Transaction t ON  a.transaction.id =t.id JOIN Invest i ON a.transaction.id = i.id WHERE (:inputLong is null or i.transaction.id = :inputLong)")
    User getuserByidinvest(@Param("inputLong") Long credit);

}
