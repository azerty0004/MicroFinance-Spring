/*
package tn.esprit.infini.Pidev.RestController;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.infini.Pidev.entities.Pack;
import tn.esprit.infini.Pidev.Services.IPackService;

import java.util.List;

@RestController
@AllArgsConstructor
public class PackController {

    private IPackService iPackService;

    @GetMapping("/getPack")
    List<Pack> afficher() {
        return iPackService.retrieveAllPack();
    }

    @PostMapping("/addPack")
    Pack ajouter(@RequestBody Pack pack) {
        return iPackService.addPack(pack);
    }

    @GetMapping("/getPackById/{id}")
    Pack afficherAvecId(@PathVariable int idPack){
        return iPackService.retrievePack(idPack);
    }

    @PutMapping("/updatePack")
    public Pack updatePack(@RequestBody Pack pack) {
        return iPackService.updatePack(pack);
    }

    @DeleteMapping("/deletePack/{idPack}")
    void  deletePack(@PathVariable ("idPack") Integer idPack)
    {
        iPackService.deletePack(idPack);
    }
}
*/