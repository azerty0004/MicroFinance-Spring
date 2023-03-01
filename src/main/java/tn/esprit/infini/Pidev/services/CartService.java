package tn.esprit.infini.Pidev.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.entities.Cart;
import tn.esprit.infini.Pidev.Repository.CartRepository;

import java.util.List;

@Service
@AllArgsConstructor

public class CartService implements ICartService {

    CartRepository cartRepository;


    @Override
    public List<Cart> retrieveAllCarts() {
        return (List<Cart>) cartRepository.findAll();
    }

    @Override
    public Cart addCart(Cart c) {
        return null;
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
}
