package tn.esprit.infini.Pidev.Services;


import tn.esprit.infini.Pidev.entities.Fine;
import tn.esprit.infini.Pidev.entities.FineType;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IFine {
    Fine addFine(Fine fine);

    List<Fine> retrieveAllFines();

    Fine updateFine(Fine fine);

    Fine retrieveFine(Integer idFine);

    void deleteFine(Integer idFine);
    List<Fine> searchFines(Map<String, Object> criteria, int numCriteria);


}
