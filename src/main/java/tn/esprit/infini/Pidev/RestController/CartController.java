package tn.esprit.infini.Pidev.RestController;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.infini.Pidev.Repository.CartRepository;
import tn.esprit.infini.Pidev.Repository.PackRepository;
import tn.esprit.infini.Pidev.Services.CartService;
import tn.esprit.infini.Pidev.Services.ICartService;
import tn.esprit.infini.Pidev.Services.PackService;
import tn.esprit.infini.Pidev.entities.Cart;
import tn.esprit.infini.Pidev.entities.Pack;

import java.util.List;

@RestController
@AllArgsConstructor
public class CartController {

    ICartService iCartService;
    CartService cartService;
    private final CartRepository cartRepository;

    PackService packService;
    private final PackRepository packRepository;

    @GetMapping("/getCart")
    List<Cart> afficher() {
        return iCartService.retrieveAllCarts();
    }

    @PostMapping("/addCart")
    Cart ajouter(@RequestBody Cart cart) {
        return iCartService.addCart(cart);
    }

    @GetMapping("/getCartById/{idCart}")
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
    @GetMapping("/getSumCartInterest/{idCart}")
    double calculateCartTotalWithInterest(@PathVariable int idCart){

        return iCartService.calculateCartTotalWithInterest(idCart);
    }

    @DeleteMapping("/removePackFromCart/{idCart}/{idPack}") // c bon khedmet
    public Cart removePackFromCart(@PathVariable int idCart,
                                   @PathVariable int idPack){
        cartService.removePackFromCart(idCart,idPack);
        return null;
    }

    @GetMapping("/{idCart}/monthly-price")
    public double getMonthlyPrice(@PathVariable("idCart") int idCart) {
        Cart cart = cartRepository.findByIdCart(idCart);
        return cartService.getMonthlyPackPrice(cart);
    }

    @GetMapping("/most-expensive")
    public Cart findMostExpensiveCart() {
        Cart mostExpensiveCart = cartService.findMostExpensiveCart();
        return mostExpensiveCart ;
    }

    @PostMapping("/{cartId}/simulate-payment")
    public Cart simulatePayment(@PathVariable int cartId,
                                @RequestParam double monthlyPrice,
                                @RequestParam int numberOfMonths) {
        return cartService.simulatePayment(cartId, monthlyPrice, numberOfMonths);
    }

    @GetMapping("/recommended-packs/{idCart}")
    public List<Pack> getRecommendedPacks(@PathVariable Integer idCart) {
        List<Pack> recommendedPacks = cartService.getRecommendedPacks(idCart);
        return recommendedPacks;
    }

    @GetMapping("/recommended-packs-by-type/{idCart}")
    public List<Pack> getRecommendedPacksByType(@PathVariable Integer idCart) {
        List<Pack> recommendedPacks = cartService.getRecommendedPacksByType(idCart);
        return recommendedPacks;
    }
}
