package tn.esprit.infini.Pidev.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.infini.Pidev.entities.Pack;
import tn.esprit.infini.Pidev.entities.User;


import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLogin(String login);
    int findByPhoneNumber(int phoneNumber);
    User findById ( int idUser);
    //@Query("SELECT u FROM User u JOIN u.likedPacks p WHERE p.id = :idPack")
  //  public List<User> getLikedByUsers(@Param("idPack") int idPack);


import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    //User findByLogin(String login);
    //@Query(value = "SELECT all FROM user WHERE type like '%Client%'")
    //List<User> findAllByTypeEndingWith(@Param("type") String type);

    //User findByAccount(Account account);
}
