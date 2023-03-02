package tn.esprit.infini.Pidev.Services;

import tn.esprit.infini.Pidev.entities.Complaint;

import java.util.List;

public interface IComplaintService {
    List<Complaint> retrieveAllcomplaints();
     Complaint addComplaint(Complaint c);

    Complaint updateComplaint (Complaint c);

    Complaint retrieveComplaint (Long idcomplaint);

    void deleteComplaint( Long idcomplaint);

}
