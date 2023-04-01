package tn.esprit.infini.Pidev.Services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.Repository.Investrepository;
import tn.esprit.infini.Pidev.Repository.TransactionRepository;
import tn.esprit.infini.Pidev.dto.InvestRequestDTO;
import tn.esprit.infini.Pidev.dto.InvestResponseDTO;
import tn.esprit.infini.Pidev.entities.Credit;
import tn.esprit.infini.Pidev.entities.Invest;
import tn.esprit.infini.Pidev.entities.Statut;
import tn.esprit.infini.Pidev.mappers.InvestMapper;
import jakarta.persistence.*;

import java.time.LocalDate;
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
        return (List<Invest>) investrepository.findAll();
    }


    @Override
    public InvestResponseDTO addInvest(InvestRequestDTO investRequestDTO) {
        if (investRequestDTO.getMounths() == null || investRequestDTO.getMounths() == 0) {
            Invest invest = Invest.builder()
                    .amount(investRequestDTO.getAmount())
                    .dateofapplication(LocalDate.now())
                    .mounths((int) ChronoUnit.MONTHS.between(investRequestDTO.getDateofobtaining().withDayOfMonth(1), investRequestDTO.getDateoffinish().withDayOfMonth(1)))
                    .dateofobtaining(investRequestDTO.getDateofobtaining())
                    .dateoffinish(investRequestDTO.getDateoffinish())
                    .interestrate(0.06)
                    .statut(Statut.EN_ATTENTE)
                    .build();
            Invest savedInvest = investrepository.save(invest);
            InvestResponseDTO investResponseDTO = investMapper.fromInvest(savedInvest);
            return investResponseDTO;

        } else {
            Invest invest = Invest.builder()
                    .amount(investRequestDTO.getAmount())
                    .dateofapplication(LocalDate.now())
                    .mounths(investRequestDTO.getMounths())
                    .dateofobtaining(investRequestDTO.getDateofobtaining())
                    .dateoffinish(investRequestDTO.getDateofobtaining().plusMonths(investRequestDTO.getMounths()))
                    .interestrate(0.06)
                    .statut(Statut.EN_ATTENTE)
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
        if (id != null) {
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
        if (dateoffinish != null) {
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

    @Override
   // @Scheduled(cron = "0 0 1 * * ")
    public List<Double> Amountgiven(Long id) {
        Invest i = investrepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Invest not found")));
        Double montantinteret;
        Double tax;
        Double x;
        LocalDate currentdate = LocalDate.now();
        List amountgiven=new ArrayList<>();
        long mounths = ChronoUnit.MONTHS.between( currentdate.withDayOfMonth(1),i.getDateoffinish().withDayOfMonth(1));

      if (mounths == 12) {
            montantinteret=(i.getAmount()*i.getInterestrate());
            tax=montantinteret*0.15;
            x=(i.getAmount()+montantinteret-tax);
            amountgiven.add(x);
            i.setAmount(x);
            investrepository.save(i);
       }

    return amountgiven;

    }
}
