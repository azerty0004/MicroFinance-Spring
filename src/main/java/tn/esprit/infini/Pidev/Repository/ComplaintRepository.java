package tn.esprit.infini.Pidev.Repository;


import org.springframework.data.repository.CrudRepository;
import tn.esprit.infini.Pidev.entities.Complaint;

public interface ComplaintRepository extends CrudRepository<Complaint,Integer> {

}
