package tn.esprit.infini.Pidev.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.Repository.FineRepository;
import tn.esprit.infini.Pidev.entities.Fine;
import tn.esprit.infini.Pidev.entities.FineType;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class FineService implements  IFine {
    FineRepository fineRepository;
    private EntityManager entityManager;
    @Override
    public Fine addFine(Fine fine) {

        return  fineRepository.save(fine);
    }

    @Override
    public List<Fine> retrieveAllFines() {

        return fineRepository.findAll();
    }

    @Override
    public Fine updateFine(Fine fine) {

        return fineRepository.save(fine);
    }

    @Override
    public Fine retrieveFine(Integer idFine) {
        return fineRepository.findById(idFine).get();
    }

    @Override
    public void deleteFine(Integer idFine) {
        fineRepository.deleteById(idFine);

    }

    @Override
    public List<Fine> searchFines(Map<String, Object> criteria, int numCriteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Fine> cq = cb.createQuery(Fine.class);
        Root<Fine> root = cq.from(Fine.class);
        Predicate[] predicates = new Predicate[numCriteria];
        int i = 0;
        for (Map.Entry<String, Object> entry : criteria.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            switch (key) {
                case "id":
                    predicates[i] = cb.equal(root.get("id"), value);
                    break;
                case "idCustomer":
                    predicates[i] = cb.equal(root.get("idCustomer"), value);
                    break;
                case "totalAmount":
                    predicates[i] = cb.equal(root.get("totalAmount"),  value);
                    break;
                case "dueDate":
                    predicates[i] = cb.greaterThanOrEqualTo(root.get("dueDate"),(Date)  value);
                    break;
                case "startDate":
                    predicates[i] = cb.greaterThanOrEqualTo(root.get("startDate"),(Date)  value);
                    break;
                case "verified":
                    predicates[i] = cb.equal(root.get("verified"), Boolean.valueOf(value.toString()));
                    break;

                case "interest":
                    predicates[i] = cb.equal(root.get("interest"),  value);
                    break;
                case "picture":
                    predicates[i] = cb.equal(root.get("picture"), value);
                    break;
                case "declaredDate":
                    predicates[i] = cb.greaterThanOrEqualTo(root.get("declaredDate"),(Date)  value);
                    break;
                case "fineType":
                    if (value instanceof FineType) {
                        predicates[i] = cb.equal(root.get("fineType"), value);
                    } else if (value instanceof String) {
                        FineType fineType = FineType.valueOf((String) value);
                        predicates[i] = cb.equal(root.get("fineType"), fineType);
                    }
                    break;
                // add more cases for other criteria as needed
            }
            i++;
        }
        cq.where(cb.and(predicates));
        TypedQuery<Fine> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

}
