package tn.esprit.infini.Pidev.RestController;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.infini.Pidev.entities.Guarantor;
import tn.esprit.infini.Pidev.Services.IGuarantorService;

import java.util.List;

@RestController
@AllArgsConstructor
public class GuarantorController {
    private IGuarantorService iGuarantorService;

    @GetMapping("/getGuarantor")
    List<Guarantor> afficher() {
        return iGuarantorService.retrieveAllGuarantor();
    }
    @PostMapping("/addGuarantor")
    Guarantor ajouter(@RequestBody Guarantor guarantor) {
        return iGuarantorService.addGuarantor(guarantor);
    }

    @GetMapping("/getById/{id}")
    Guarantor afficherAvecId(@PathVariable int id){
        return iGuarantorService.retrieveGuarantor(id);
    }

    @PutMapping("/updateGuarantor")
    public Guarantor updateGuarantor(@RequestBody Guarantor guarantor) {
        return iGuarantorService.updateGuarantor(guarantor);
    }

    @DeleteMapping("/deleteGuarantor/{idGuarantor}")
     void deleteGuarantor(@PathVariable ("idGuarantor") Integer idGuarantor )
    {
         iGuarantorService.deleteGuarantor(idGuarantor);
    }
}
