package tn.esprit.infini.Pidev.Services;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.Repository.Investrepository;
import tn.esprit.infini.Pidev.Repository.TransactionRepository;
import tn.esprit.infini.Pidev.entities.Invest;
import tn.esprit.infini.Pidev.entities.Statut;
import tn.esprit.infini.Pidev.entities.Transaction;
import tn.esprit.infini.Pidev.entities.User;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Getter
@Setter
@AllArgsConstructor
public class Investservice implements Iinvestservice {
    Investrepository investrepository;
    TransactionRepository transactionRepository;

    @Override
    public List<Invest> retrieveAllInvests() {
        return (List<Invest>) investrepository.findAll() ;
    }


    @Override
    public Invest addInvest(Invest i) {
        return investrepository.save(i);
    }

    @Override
    public Invest updateInvest(Invest i) {
        return investrepository.save(i);
    }

    @Override
    public Invest retrieveInvest(Long id) {
        return investrepository.findById(id).get();
    }

    @Override
    public void deleteInvest(Long id) {
        investrepository.deleteById(id);

    }



    @Override
    public List<Invest> getInvestByiduser(Long userid) {
        return investrepository.getInvestByiduser(userid);
    }

    @Override
    public  Specification<Invest> searchInvests(Double minAmount, Double maxAmount, Date minDateOfApplication,
                                                      Date maxDateOfApplication, Date minDateOfObtaining, Date maxDateOfObtaining,
                                                      Date minDateOfFinish, Date maxDateOfFinish, Double minInterestRate,
                                                      Double maxInterestRate, Integer minMonths, Integer maxMonths,
                                                      Statut statut) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (minAmount != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("amount"), minAmount));
            }
            if (maxAmount != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("amount"), maxAmount));
            }
            if (minDateOfApplication != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("dateofapplication"), minDateOfApplication));
            }
            if (maxDateOfApplication != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("dateofapplication"), maxDateOfApplication));
            }
            if (minDateOfObtaining != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("dateofobtaining"), minDateOfObtaining));
            }
            if (maxDateOfObtaining != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("dateofobtaining"), maxDateOfObtaining));
            }
            if (minDateOfFinish != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("dateoffinish"), minDateOfFinish));
            }
            if (maxDateOfFinish != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("dateoffinish"), maxDateOfFinish));
            }
            if (minInterestRate != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("interestrate"), minInterestRate));
            }
            if (maxInterestRate != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("interestrate"), maxInterestRate));
            }
            if (minMonths != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("mounths"), minMonths));
            }
            if (maxMonths != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("mounths"), maxMonths));
            }
            if (statut != null) {
                predicates.add(builder.equal(root.get("statut"), statut));
            }

            return builder.and(predicates.toArray(new Predicate[1]));
        };
    }

}
