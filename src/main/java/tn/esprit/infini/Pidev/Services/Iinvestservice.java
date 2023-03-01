package tn.esprit.infini.Pidev.Services;
import tn.esprit.infini.Pidev.entities.Invest;
import java.util.List;
public interface Iinvestservice  {
    List<Invest> retrieveAllInvests();
    List<Invest> retrieveByInterestRatelike(double i);
    Invest addInvest(Invest i);

    Invest updateInvest (Invest i);

    Invest retrieveInvest (Long id);

    void deleteInvest( Long id);

}
