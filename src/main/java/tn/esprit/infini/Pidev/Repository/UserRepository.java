package tn.esprit.infini.Pidev.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.infini.Pidev.entities.Account;
import tn.esprit.infini.Pidev.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLogin(String login);
    //User findByAccount(Account account);
}
