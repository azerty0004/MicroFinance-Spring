package tn.esprit.infini.Pidev.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.infini.Pidev.entities.Account;
import tn.esprit.infini.Pidev.entities.Pack;
import tn.esprit.infini.Pidev.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLogin(String login);
    User findById(int idUser);

    //@Query("SELECT u FROM User u JOIN u.likedPacks p WHERE p.id = :idPack")
  //  public List<User> getLikedByUsers(@Param("idPack") int idPack);

    //User findByAccount(Account account);
}
