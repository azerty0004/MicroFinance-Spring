package tn.esprit.infini.Pidev.RestController;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.infini.Pidev.Services.ICartService;
import tn.esprit.infini.Pidev.entities.Cart;

import java.util.List;

@RestController
@AllArgsConstructor
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

    @PutMapping("/updateCart")
    public Cart updateCart(@RequestBody Cart cart) {
        return iCartService.updateCart(cart);
    }

    @DeleteMapping("/deleteCart/{idCart}")
    void deleteCart(@PathVariable ("idCart") Integer idCart)
    {
        iCartService.deleteCart(idCart);
    }

    @GetMapping("/getSumCart/{idCart}")
    double calculateTotalAmount(@PathVariable int idCart){
        return iCartService.calculateTotalAmount(idCart);
    }
}
