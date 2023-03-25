package tn.esprit.infini.Pidev.Services;

import tn.esprit.infini.Pidev.entities.Complaint;
import tn.esprit.infini.Pidev.entities.Typecomplaint;

import java.util.HashMap;
import java.util.List;

public interface IComplaintService {
    List<Complaint> retrieveAllcomplaints();


  /*  boolean complaintContainsBadWords(Complaint complaint); */

    Complaint updateComplaint(Complaint c);

    Complaint retrieveComplaint(Long idcomplaint);

    void deleteComplaint(Long idcomplaint);
     // List<Complaint> getComplaintByUser(int idUser);

    List<Object[]> getComplaintByType();

    HashMap<Typecomplaint, Double> getComplaintStatistics();



    Complaint addComplaint(Complaint complaint, int id);

    Complaint filterBadWords(Complaint complaint);
}


/* Complaint assignComplaintToUser(Long idcomplaint, int id); */
   /* List<Object[]> getComplaintByType(); */




