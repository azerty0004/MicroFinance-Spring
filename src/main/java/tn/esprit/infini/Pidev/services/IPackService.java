package tn.esprit.infini.Pidev.services;

import tn.esprit.infini.Pidev.entities.Pack;

import java.util.List;

public interface IPackService {
    List<Pack> retrieveAllPack();

    Pack addPack(Pack c);

    Pack updatePack (Pack c);

    Pack retrievePack (Integer idPack);

    void deletePack( Integer idPack);
}
