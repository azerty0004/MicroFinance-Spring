package tn.esprit.infini.Pidev.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.Repository.*;
import tn.esprit.infini.Pidev.entities.*;

import java.util.*;

@Service
@AllArgsConstructor
public class PackService implements IPackService {

    PackRepository packRepository;
    CartRepository cartRepository;
    CartService cartService;
    UserRepository userRepository;
    UserService userService;


    ReactionRepository reactionRepository;
    TransactionRepository transactionRepository;


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
    // @Override
    //  public Pack likePack(int idPack, int idUser) {

        /* if (idPack <= 0) {
             throw new IllegalArgumentException("Invalid pack ID.");
         }

         Pack pack = retrievePack(idPack);
         if (packRepository.getLikedByUsers(idPack).contains(idUser)) {
             throw new IllegalArgumentException("User has already liked this pack.");
         }

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

    @Override
    public void addReaction(int idPack, int idUser, TypeReaction TypeReaction) {
        Pack pack = packRepository.findByIdPack(idPack);
        Reaction existingReaction = reactionRepository.findByIdUserAndPackIdPack(idUser, idPack);
        if (existingReaction != null) {
            if (existingReaction.getType() == TypeReaction) {
                System.out.println("l'utilisateur a déjà réagi de la même manière");
                //rien à faire

            } else {
                Reaction newReaction = new Reaction();
                newReaction.setType(TypeReaction);
                newReaction.setIdUser(idUser);
                newReaction.setPack(pack);
                reactionRepository.save(newReaction);
                packRepository.save(pack);

                System.out.println("L'utilisateur a réagi différemment");

            }
        } else {
            // l'utilisateur n'a pas encore réagi, on crée deux nouvelles réactions
            Reaction reaction1 = new Reaction();
            reaction1.setType(TypeReaction.like);
            reaction1.setIdUser(idUser);
            reaction1.setPack(pack);
            reactionRepository.save(reaction1);

            Reaction reaction2 = new Reaction();
            reaction2.setType(TypeReaction.dislike);
            reaction2.setIdUser(idUser);
            reaction2.setPack(pack);
            reactionRepository.save(reaction2);

            pack.setLikes(pack.getLikes() + 1);
            pack.setDislikes(pack.getDislikes() + 1);
            packRepository.save(pack);

            System.out.println("L'utilisateur a réagi différemment");
        }
    }


    public double getAverageRating(int idPack) {
        Pack pack = packRepository.findByIdPack(idPack);
        int totalReactions = pack.getLikes() + pack.getDislikes();
        if (totalReactions == 0) {
            return 0;
        } else {
            return ((double) pack.getLikes() / totalReactions);
        }
    }

    @Override
    public Pack getMostLikedPack() {
        List<Pack> packs = packRepository.findAll();
        Pack mostLikedPack = null;
        int maxLikes = 0;
        for (Pack pack : packs) {
            if (pack.getLikes() > maxLikes) {
                maxLikes = pack.getLikes();
                mostLikedPack = pack;
            }
        }
        return mostLikedPack;
    }

    @Override
    public Pack getMostDislikedPack() {
        List<Pack> packs = packRepository.findAll();
        Pack mostDislikedPack = null;
        int maxDislikes = 0;
        for (Pack pack : packs) {
            if (pack.getDislikes() > maxDislikes) {
                maxDislikes = pack.getDislikes();
                mostDislikedPack = pack;
            }
        }
        return mostDislikedPack;
    }


    @Override
    public List<Pack> getPacksByPriceRange(double minPrice, double maxPrice) {
        return packRepository.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public List<Pack> getPacksSortedByPrice(boolean ascending) {
        if (ascending) {
            return packRepository.findByOrderByPriceAsc();
        } else {
            return packRepository.findByOrderByPriceDesc();
        }
    }

}

