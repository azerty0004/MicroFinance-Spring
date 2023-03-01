package tn.esprit.infini.Pidev.Services;

import tn.esprit.infini.Pidev.entities.Cart;

import java.util.List;

public interface ICartService {
    List<Cart> retrieveAllCarts();

    Cart addCart(Cart c);

    Cart updateCart (Cart c);

    Cart retrieveCart (Integer idCart);

    void deleteCart( Integer idCart);
}
