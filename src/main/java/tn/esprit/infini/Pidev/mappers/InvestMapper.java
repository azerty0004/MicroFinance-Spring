package tn.esprit.infini.Pidev.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import tn.esprit.infini.Pidev.dto.CreditRequestDTO;
import tn.esprit.infini.Pidev.dto.CreditResponseDTO;
import tn.esprit.infini.Pidev.dto.InvestRequestDTO;
import tn.esprit.infini.Pidev.dto.InvestResponseDTO;
import tn.esprit.infini.Pidev.entities.Credit;
import tn.esprit.infini.Pidev.entities.Invest;
import jakarta.persistence.*;

@Component
public class InvestMapper {
    public InvestResponseDTO fromInvest(Invest invest){
        InvestResponseDTO investResponseDTO=new InvestResponseDTO();
        BeanUtils.copyProperties(invest,investResponseDTO);
        return investResponseDTO;
    }
    public Invest fromInvestRequestDTO(InvestRequestDTO investRequestDTO){
        Invest invest= new Invest();
        BeanUtils.copyProperties(investRequestDTO,invest);
        return invest;

    }
}
