package tn.esprit.infini.Pidev.RestController;

import org.springframework.web.bind.annotation.*;
import tn.esprit.infini.Pidev.entities.Cart;
import tn.esprit.infini.Pidev.entities.Pack;
import tn.esprit.infini.Pidev.Services.ICartService;

import java.util.List;

public class CartController {

    private ICartService iCartService;

    @GetMapping("/getCart")
    List<Cart> afficher() {
        return iCartService.retrieveAllCarts();
    }

    @PostMapping("/addCart")
    Cart ajouter(@RequestBody Cart cart) {
        return iCartService.addCart(cart);
    }

    @GetMapping("/getCartById/{id}")
    Cart afficherAvecId(@PathVariable int idCart){
        return iCartService.retrieveCart(idCart);
    }

    @PutMapping("/updatePack")
    public Cart updatePack(@RequestBody Cart cart) {
        return iCartService.updateCart(cart);
    }

    @DeleteMapping("/deletePack/{idPack}")
    void deletePack(@PathVariable ("idGuarantor") Integer idPack)
    {
        iCartService.deleteCart(idPack);
    }
}
