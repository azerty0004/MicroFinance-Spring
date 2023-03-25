package tn.esprit.infini.Pidev.Repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tn.esprit.infini.Pidev.entities.Complaint;

import java.util.List;

public interface ComplaintRepository extends CrudRepository<Complaint,Long> {
    @Query("SELECT c.typecomplaint, COUNT(c) FROM Complaint c GROUP BY c.typecomplaint")
     List<Object[]> getComplaintsByType();

    @Query("select i From Complaint i where i.user.id = :iddonne")
    List<Complaint> getComplaintsByUser(@Param("iddonne") int id);


   /*  Long findByTypecomplaint(Typecomplaint typecomplaint); */
   /* List<Complaint>findAllByTypecomplaint(String type);
    int countByTypecomplaint();
    */

}