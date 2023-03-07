package tn.esprit.infini.Pidev.Services;
import org.springframework.data.jpa.domain.Specification;
import tn.esprit.infini.Pidev.entities.Credit;
import tn.esprit.infini.Pidev.entities.Invest;
import tn.esprit.infini.Pidev.entities.Statut;
import tn.esprit.infini.Pidev.entities.User;

import java.util.Date;
import java.util.List;
public interface Iinvestservice  {
    List<Invest> retrieveAllInvests();
    Invest addInvest(Invest i);

    Invest updateInvest (Invest i);

    Invest retrieveInvest (Long id);

    void deleteInvest( Long id);

    Invest assingInvestToTransaction(Long idInvest, Long idTransaction);
    User getuserByidinvest(Long investid);
    List<Invest> getInvestByiduser(Long userid);

    Specification<Invest> searchInvests(Double minAmount, Double maxAmount, Date minDateOfApplication,
                                         Date maxDateOfApplication, Date minDateOfObtaining, Date maxDateOfObtaining,
                                         Date minDateOfFinish, Date maxDateOfFinish, Double minInterestRate,
                                         Double maxInterestRate, Integer minMonths, Integer maxMonths,
                                         Statut statut, Long transactionId);
}
