package tn.esprit.infini.Pidev.Services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.Repository.Investrepository;
import tn.esprit.infini.Pidev.Repository.TransactionRepository;
import tn.esprit.infini.Pidev.dto.InvestRequestDTO;
import tn.esprit.infini.Pidev.dto.InvestResponseDTO;
import tn.esprit.infini.Pidev.entities.Invest;
import tn.esprit.infini.Pidev.entities.Statut;
import tn.esprit.infini.Pidev.mappers.InvestMapper;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Getter
@Setter
@AllArgsConstructor
public class Investservice implements Iinvestservice {
    @PersistenceContext
    EntityManager entityManager;
    private Investrepository investrepository;
    private TransactionRepository transactionRepository;
    private InvestMapper investMapper;

    @Override
    public List<Invest> retrieveAllInvests() {
        return (List<Invest>) investrepository.findAll() ;
    }


    @Override
    public InvestResponseDTO addInvest(InvestRequestDTO investRequestDTO) {
        if (investRequestDTO.getMounths() == null || investRequestDTO.getMounths() == 0 ) {
            Invest invest = Invest.builder()
                    .amount(investRequestDTO.getAmount())
                    .mounths((int) ChronoUnit.MONTHS.between(investRequestDTO.getDateofobtaining().withDayOfMonth(1), investRequestDTO.getDateoffinish().withDayOfMonth(1)))
                    .dateofapplication(investRequestDTO.getDateOfApplication())
                    .dateofobtaining(investRequestDTO.getDateofobtaining())
                    .dateoffinish(investRequestDTO.getDateoffinish())
                    .statut(investRequestDTO.getStatut())
                    .build();
            Invest savedInvest = investrepository.save(invest);
            InvestResponseDTO investResponseDTO = investMapper.fromInvest(savedInvest);
            return investResponseDTO;

        } else {
            Invest invest = Invest.builder()
                    .amount(investRequestDTO.getAmount())
                    .mounths(investRequestDTO.getMounths())
                    .dateofapplication(investRequestDTO.getDateOfApplication())
                    .dateofobtaining(investRequestDTO.getDateofobtaining())
                    .dateoffinish(investRequestDTO.getDateofobtaining().plusMonths(investRequestDTO.getMounths()))
                    .statut(investRequestDTO.getStatut())
                    .build();

            Invest savedInvest = investrepository.save(invest);
           InvestResponseDTO investResponseDTO = investMapper.fromInvest(savedInvest);
            return investResponseDTO;

        }

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
    public List<Invest> searchInvests(Long id, Double amount, Date dateofapplication, Date dateofobtaining, Date dateoffinish, Double interestRate, Integer mounths, Statut statut) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Invest> query = builder.createQuery(Invest.class);
        Root<Invest> root = query.from(Invest.class);

        List<Predicate> predicates = new ArrayList<>();
        if (id!= null) {
            predicates.add(builder.equal(root.get("id"), id));
        }
        if (amount != null) {
            predicates.add(builder.equal(root.get("amount"), amount));
        }
        if (dateofapplication != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("dateofapplication"), dateofapplication));
        }
        if (dateofobtaining != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("dateofobtaining"), dateofobtaining));
        }
        if (dateoffinish!= null) {
            predicates.add(builder.equal(root.get("dateoffinish"), dateoffinish));
        }
        if (interestRate != null) {
            predicates.add(builder.equal(root.get("interestrate"), interestRate));
        }
        if (mounths != null) {
            predicates.add(builder.equal(root.get("mounths"), mounths));
        }
        if (statut != null) {
            predicates.add(builder.equal(root.get("statut"), statut));
        }

        if (!predicates.isEmpty()) {
            query.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
        }

        return entityManager.createQuery(query).getResultList();
    }


}
