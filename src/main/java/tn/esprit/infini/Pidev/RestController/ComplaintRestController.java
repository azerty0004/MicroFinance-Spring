package tn.esprit.infini.Pidev.RestController;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.infini.Pidev.Services.IComplaintService;
import tn.esprit.infini.Pidev.entities.Complaint;

import java.util.List;

@RestController
@AllArgsConstructor
public class ComplaintRestController {
 private IComplaintService iComplaintService;
 @GetMapping("/displayComplaint")
    public List<Complaint> display() {
     return iComplaintService.retrieveAllcomplaints();
 }
    @PostMapping("/addComplaint")
    public Complaint add(@RequestBody Complaint complaint) {
        return iComplaintService.addComplaint(complaint);
    }
    @GetMapping("/displaywithId/{idcomplaint}")
    public Complaint displaywithid(@PathVariable int idcomplaint){
        return iComplaintService.retrieveComplaint((long) idcomplaint);
    }

}
