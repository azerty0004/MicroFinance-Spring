package tn.esprit.infini.Pidev.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.infini.Pidev.entities.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

}
