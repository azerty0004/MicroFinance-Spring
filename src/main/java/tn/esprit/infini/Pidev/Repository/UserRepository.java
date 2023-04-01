package tn.esprit.infini.Pidev.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.infini.Pidev.entities.Account;
import tn.esprit.infini.Pidev.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    //User findByLogin(String login);
    //@Query(value = "SELECT all FROM user WHERE type like '%Client%'")
    //List<User> findAllByTypeEndingWith(@Param("type") String type);
    //User findByAccount(Account account);
}
