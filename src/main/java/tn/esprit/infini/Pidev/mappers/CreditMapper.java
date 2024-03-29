package tn.esprit.infini.Pidev.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import tn.esprit.infini.Pidev.dto.CreditRequestDTO;
import tn.esprit.infini.Pidev.dto.CreditResponseDTO;
import tn.esprit.infini.Pidev.entities.Credit;
import jakarta.persistence.*;

@Component

public class CreditMapper {
    public CreditResponseDTO fromCredit(Credit credit){
       CreditResponseDTO creditResponseDTO=new CreditResponseDTO();
        BeanUtils.copyProperties(credit,creditResponseDTO);
        return creditResponseDTO;
    }
    public Credit fromCreditRequestDTO(CreditRequestDTO creditRequestDTO){
       Credit credit= new Credit();
        BeanUtils.copyProperties(creditRequestDTO,credit);
        return credit;

    }
}
