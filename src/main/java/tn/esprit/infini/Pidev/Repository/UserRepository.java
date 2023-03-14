package tn.esprit.infini.Pidev.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.infini.Pidev.entities.Account;
import tn.esprit.infini.Pidev.entities.Credit;
import tn.esprit.infini.Pidev.entities.User;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLogin(String login);

        @Query("SELECT u FROM User u INNER JOIN u.account a INNER JOIN a.transaction t INNER JOIN t.credit c WHERE c.id = :creditId")
        User findUserByCreditId(@Param("creditId") Long creditId);
}


