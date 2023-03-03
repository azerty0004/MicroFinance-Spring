package tn.esprit.infini.Pidev.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.Services.IComplaintService;
import tn.esprit.infini.Pidev.entities.Complaint;
import tn.esprit.infini.Pidev.Repository.ComplaintRepository;

import java.util.List;


@Service
@AllArgsConstructor
public  class ComplaintService implements IComplaintService {
    ComplaintRepository complaintRepository;


    @Override
    public List<Complaint> retrieveAllcomplaints() {
        return (List<Complaint>) complaintRepository.findAll();
    }

    @Override
    public Complaint addComplaint(Complaint c) {
        return complaintRepository.save(c);

    }


    @Override
    public Complaint updateComplaint(Complaint c) {
        return complaintRepository.save(c);
    }

    @Override
    public Complaint retrieveComplaint(Long idcomplaint) {
        return complaintRepository.findById(Math.toIntExact(idcomplaint)).get();
    }

    @Override
    public void deleteComplaint(Long idcomplaint) {
        complaintRepository.deleteById(Math.toIntExact(idcomplaint));
    }
}