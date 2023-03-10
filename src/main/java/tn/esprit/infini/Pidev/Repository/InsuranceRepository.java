package tn.esprit.infini.Pidev.Repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.infini.Pidev.entities.Insurance;
@Repository

public interface InsuranceRepository extends CrudRepository<Insurance,Integer> {
}
