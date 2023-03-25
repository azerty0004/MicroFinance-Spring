package tn.esprit.infini.Pidev.RestController;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.infini.Pidev.Services.IComplaintService;
import tn.esprit.infini.Pidev.entities.Complaint;
import tn.esprit.infini.Pidev.entities.Typecomplaint;

import java.util.HashMap;
import java.util.List;

@RestController
@AllArgsConstructor
public class ComplaintRestController {
    private IComplaintService iComplaintService;

    @GetMapping("/displayComplaint")
    public List<Complaint> display() {
        return iComplaintService.retrieveAllcomplaints();
    }

    /*@PostMapping("/addComplaint/{idUser}")
    public Complaint add(@RequestBody Complaint complaint,@PathVariable("idUser")Integer idUser) {
        return iComplaintService.addComplaint(complaint,idUser);
    }
    */
     /* @PostMapping("/addComplaint")
    Complaint ajouter(@RequestBody Complaint c) {
        return iComplaintService.addComplaint(c);
    } */
    @PostMapping("/addComplaint/{id}")
    Complaint ajouter(@RequestBody Complaint complaint, @PathVariable("id") int id) {
        return iComplaintService.addComplaint(complaint,id);
    }

    @GetMapping("/displaywithId/{idcomplaint}")
    public Complaint displaywithid(@PathVariable ("idcomplaint") Long idcomplaint) {
        return iComplaintService.retrieveComplaint( idcomplaint);
    }

    @GetMapping("/statistics")
    public HashMap<Typecomplaint, Double> getComplaintStatistics() {
        return iComplaintService.getComplaintStatistics();
    }
    @GetMapping("/getcomplaintbyType")
    public List<Object[]> getcomplaintByTyoe() {
        return iComplaintService.getComplaintByType();

    }
    @DeleteMapping("/deletecomplaint/{idcomplaint}")
    void  deletecomplaint(@PathVariable ("idcomplaint") Long idcomplaint)
    {
        iComplaintService.deleteComplaint(idcomplaint);
    }

     /*68@PostMapping("/complaints")
    public Complaint addComplaint(@RequestBody Complaint complaint) {
        String filteredDescription = iComplaintService.filterBadWords(complaint.getDescription());
        complaint.setDescription(filteredDescription);
     return   iComplaintService.addComplaint(complaint);

    } */

}

    /* @PutMapping("/assignComplainttTouser/{idcomplaint}/{id}")
    public Complaint  addandassingComplaintTouser(@PathVariable("idcomplaint") Long idcomplaint,@PathVariable("id") int id){
     return iComplaintService.assignComplaintToUser(idcomplaint,id);
    }

*/

