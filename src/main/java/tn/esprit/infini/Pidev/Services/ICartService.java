package tn.esprit.infini.Pidev.Services;

import tn.esprit.infini.Pidev.entities.Cart;
import tn.esprit.infini.Pidev.entities.Pack;

import java.util.List;

public interface ICartService {
    List<Cart> retrieveAllCarts();

    Cart addCart(Cart c);

    Cart updateCart (Cart c);

    Cart retrieveCart (Integer idCart);

    void deleteCart( Integer idCart);

    double calculateTotalAmount(int idCart);

    double calculateCartTotalWithInterest(int cartId);

    double getMonthlyPackPrice(Cart cart);

    Cart removePackFromCart(int idCart, int idPack);

    Cart findMostExpensiveCart();

    Cart simulatePayment(int idCart, double totalAmountWithInterest, int numberOfMonths) ;

    List<Pack> getRecommendedPacks(Integer idCart);

    List<Pack> getRecommendedPacksByType(Integer idCart);
}
