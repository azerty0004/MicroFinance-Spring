package tn.esprit.infini.Pidev.Services;

import tn.esprit.infini.Pidev.entities.Pack;

import java.util.List;
import java.util.Set;

public interface IPackService {
    List<Pack> retrieveAllPacks();

    Pack addPack(Pack p);

    Pack updatePack (Pack p);

    Pack retrievePack (Integer idPack);

    void deletePack( Integer idPack);

    Pack likePack(int idPack);

    Pack dislikePack(int idPack);

    Pack assignPackToCart(Integer idPack, Integer idCart);

    Set<Pack> PacksCart(Integer idCart);

    List<Pack> findMostLikedPacks(int likes);
    List<Pack> findMostDislikedPacks(int dislikes);
}
