package tn.esprit.infini.Pidev.Services;


import tn.esprit.infini.Pidev.entities.Fine;

import java.util.List;

public interface IFine {
    Fine addFine(Fine fine);

    List<Fine> retrieveAllFines();

    Fine updateFine(Fine fine);

    Fine retrieveFine(Integer idFine);

    void deleteFine(Integer idFine);

}
