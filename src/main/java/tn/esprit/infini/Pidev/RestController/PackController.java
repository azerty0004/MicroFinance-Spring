package tn.esprit.infini.Pidev.RestController;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.infini.Pidev.Repository.PackRepository;
import tn.esprit.infini.Pidev.Services.IPackService;
import tn.esprit.infini.Pidev.entities.Pack;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
public class PackController {

    IPackService iPackService;
    PackRepository packRepository;

    @GetMapping("/getPack")
    List<Pack> afficher() {
        return iPackService.retrieveAllPacks();
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
        return
                iPackService.updatePack(pack);
    }

    @DeleteMapping("/deletePack/{idPack}")
    void  deletePack(@PathVariable ("idPack") Integer idPack)
    {
        iPackService.deletePack(idPack);
    }

    @PutMapping("/like/{idPack}")
    public Pack likePack(@PathVariable int idPack) {

        return iPackService.likePack(idPack);
    }

    @PutMapping("/dislike/{idPack}")
    public Pack dislikePack(@PathVariable int idPack) {
        return iPackService.dislikePack(idPack);
    }

    @GetMapping("/CartPack/{idCart}") // affiche les packs d'une carte
    public Set<Pack> CartPack (@PathVariable ("idCart") Integer idCart)
    {
        return iPackService.PacksCart(idCart);
    }

    @PutMapping("/assignPackToCart/{idPack}/{idCart}") // affecte un pack au panier ( bouton ajouter au panier)
    public Pack assignPackToCart(@PathVariable Integer idPack, @PathVariable Integer idCart){
        return iPackService.assignPackToCart( idPack,  idCart);
    }
}
