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
public class PackService implements IPackService {

    PackRepository packRepository;
    CartRepository cartRepository;
    CartService cartService;

    @Override
    public List<Pack> retrieveAllPacks() {

        return (List<Pack>) packRepository.findAll();
    }

    @Override
    public Pack addPack(Pack p) {

        return packRepository.save(p);
    }

    @Override
    public Pack updatePack(Pack p) {

        return packRepository.save(p);
    }

    @Override
    public Pack retrievePack(Integer idPack) {

        return packRepository.findById(idPack).get();
    }

    @Override
    public void deletePack(Integer idPack) {
        packRepository.deleteById(idPack);

    }

     @Override
     public Pack likePack(int idPack) {
        Pack pack = retrievePack(idPack);
         packRepository.findById(idPack);
         pack.setLikes(pack.getLikes()+ 1);
         packRepository.save(pack);
       /*if (!pack.getLikedByUsers().contains(userId)) {
            pack.setLikes(pack.getLikes() + 1);
            pack.getLikedByUsers().add(userId);
            packRepository.save(pack);
        } else {
            throw new IllegalArgumentException("User has already liked this product.");
        }

        */
         return pack;
     }

    @Override
    public Pack dislikePack(int idPack) {
        Pack pack = retrievePack(idPack);
        packRepository.findById(idPack);
        pack.setDislikes(pack.getDislikes()+ 1);
        packRepository.save(pack);
        return pack;
    }

    @Override
    public Pack assignPackToCart(Integer idPack, Integer idCart) {
        Pack pack = packRepository.findByIdPack(idPack);
        Cart cart = cartRepository.findByIdCart(idCart);
        pack.setCart(cart);
        packRepository.save(pack);
        return pack;
    }

    @Override
    public Set<Pack> PacksCart(Integer idCart) {
        Cart cart = cartRepository.findById(idCart).orElse(null);
        return cart.getPack();
    }


}
