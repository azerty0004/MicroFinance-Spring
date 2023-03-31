package tn.esprit.infini.Pidev.Services;

import tn.esprit.infini.Pidev.dto.InvestRequestDTO;
import tn.esprit.infini.Pidev.dto.InvestResponseDTO;
import tn.esprit.infini.Pidev.entities.Invest;
import tn.esprit.infini.Pidev.entities.Statut;

import java.util.Date;
import java.util.List;

public interface Iinvestservice  {
    List<Invest> retrieveAllInvests();
    InvestResponseDTO addInvest(InvestRequestDTO i);

    Invest updateInvest (Invest i);

    Invest retrieveInvest (Long id);

    void deleteInvest( Long id);

    List<Invest> getInvestByiduser(Long userid);

    List<Invest> searchInvests(Long id, Double amount, Date dateofapplication, Date dateofobtaining, Date dateoffinish, Double interestRate, Integer mounths, Statut statut);
}
