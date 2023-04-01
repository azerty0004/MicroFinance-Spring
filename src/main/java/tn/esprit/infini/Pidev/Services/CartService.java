package tn.esprit.infini.Pidev.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.Repository.CartRepository;
import tn.esprit.infini.Pidev.Repository.PackRepository;
import tn.esprit.infini.Pidev.entities.Cart;
import tn.esprit.infini.Pidev.entities.Pack;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor


    public class CartService implements ICartService {

        CartRepository cartRepository;
    private final PackRepository packRepository;

    @Override
        public List<Cart> retrieveAllCarts() {
            return (List<Cart>) cartRepository.findAll();
        }

        @Override
        public Cart addCart(Cart c) {

            return cartRepository.save(c);
        }

        @Override
        public Cart updateCart(Cart c) {
            return cartRepository.save(c);    }

        @Override
        public Cart retrieveCart(Integer idCart) {

            return cartRepository.findById(idCart).get();
        }

        @Override
        public void deleteCart(Integer idCart) {
            cartRepository.deleteById(idCart);

        }

    public double calculateTotalAmount(int idCart) {
        Cart cart = cartRepository.findByIdCart(idCart);
        double totalAmount = 0.0;
        Set<Pack> packs = cart.getPack();
        for (Pack p : packs) {
            totalAmount += p.getPrice() * cart.getQuantity()  ;
        }
        return totalAmount;
    }



    }

