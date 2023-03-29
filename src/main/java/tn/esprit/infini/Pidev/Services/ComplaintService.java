package tn.esprit.infini.Pidev.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.Repository.ComplaintRepository;
import tn.esprit.infini.Pidev.Repository.UserRepository;
import tn.esprit.infini.Pidev.entities.Complaint;
import tn.esprit.infini.Pidev.entities.Typecomplaint;
import tn.esprit.infini.Pidev.entities.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@AllArgsConstructor
public  class ComplaintService implements IComplaintService {

  private EmailService emailService;
    ComplaintRepository complaintRepository;
    UserRepository userRepository;


    @Override
    public List<Complaint> retrieveAllcomplaints() {
        return (List<Complaint>) complaintRepository.findAll();
    }

    /*  @Override
      public Complaint addComplaint(Complaint c,Integer idUser) {
          User user=userRepository.findById(idUser).get();
          c.setUser(user);
          return complaintRepository.save(c);

      }

     */
     @Override
    public Complaint addComplaint(Complaint complaint, int id) {
        //        UR.findByAccount(account).setType(TypeUser.Casual_Client);

        User user= userRepository.findById(id).orElse(null);
        complaint.setUser(user);
        return complaintRepository.save(filterBadWords(complaint));
    }
 /*@Override
    public Complaint addComplaint(Complaint complaint, int id) {

        User user = userRepository.findById(id).orElse(null);

        if (complaintContainsBadWords(complaint)) {
           user.setBlockedUntil(LocalDateTime.now().plusHours(2)); // Block the user for 2 hours

            throw new BadWordsException("Your complaint contains inappropriate language and has been blocked for 2 hours.");
        }

        complaint.setUser(user);
        return complaintRepository.save(filterBadWords(complaint));
    }
       @Override
       public boolean complaintContainsBadWords(Complaint complaint) {
        String[] badWords = {"shit", "crap", "basterd","damn it"," Bloody Hell"," Rubbish",};

        for (String word : badWords) {
            if (complaint.getDescription().toLowerCase().contains(word.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

*/



    @Override
    public Complaint updateComplaint(Complaint c) {

        return complaintRepository.save(c);
    }



    @Override
    public void deleteComplaint(Long idcomplaint) {

        complaintRepository.deleteById(idcomplaint);
    }

 /*   @Override
    public List<Complaint> getComplaintByUser(int idUser) {

        return  complaintRepository.getComplaintsByUser(idUser);
        return (List<Complaint>) complaints ;
    } */
    @Override
    public List<Object[]> getComplaintByType() {

        return complaintRepository.getComplaintsByType();
    }

    @Override
    public HashMap<Typecomplaint, Double> getComplaintStatistics() {
        List<Complaint> complaints = (List<Complaint>) complaintRepository.findAll();
        HashMap<Typecomplaint, Long> statistics = new HashMap<>();
        for (Complaint complaint : complaints) {
            Typecomplaint type = complaint.getTypecomplaint();
              if (statistics.containsKey(type)) {
                statistics.put(type, statistics.get(type) + 1);
            } else {
                statistics.put(type, 1L);
            }
        }
        HashMap<Typecomplaint, Double> percentages = new HashMap<>();
        long totalComplaints = complaints.size();
        for (Map.Entry<Typecomplaint, Long> entry : statistics.entrySet()) {
            Typecomplaint type = entry.getKey();
            long count = entry.getValue();
            double percentage = (double) count / totalComplaints * 100;
            percentages.put(type, percentage);
        }
        return percentages;
    }
    @Override
    public Complaint filterBadWords(Complaint complaint) {
        String[] badWords = {"shit", "crap", "basterd","damn it"," Bloody Hell"," Rubbish",};

        for (String word : badWords) {
            complaint.description = complaint.description.replaceAll("(?i)" + word, "**********");
        }

        return complaint;
    }
    @Override
    public Complaint retrieveComplaint(Long idcomplaint) {
        return complaintRepository.findById(idcomplaint).get();
    }

 /*   public void updateComplaintState(Long complaintId, Stateofcomplaint newState) {
        Complaint complaint = complaintRepository.findById(complaintId).orElseThrow(() -> new EntityNotFoundException("Complaint not found"));
        Stateofcomplaint oldState = complaint.getStateofcomplaint();
        complaint.setStateofcomplaint(newState);
        complaintRepository.save(complaint);

        if (oldState != Stateofcomplaint.complaintresolved && newState == Stateofcomplaint.complaintresolved) {
            String recipientEmail = complaint.getUser().getEmail();
            String subject = "Votre réclamation a été traitée";
            String body = "Bonjour,\n\nNous sommes heureux de vous informer que votre réclamation a été traitée avec succès.\n\nCordialement,\nL'équipe de support";
            emailService.sendEmail(recipientEmail, subject, body);
        }
    } */
 /*public void resolveComplaint(Long complaintId) {
     Complaint complaint = complaintRepository.findById(complaintId).get();
     if (complaint.getStateofcomplaint() == Stateofcomplaint.complaintresolved) {
         // Envoyer un courriel au client
         String to = complaint.getUser().getEmail();
         String subject = "Votre plainte a été résolue";
         String text = "Votre plainte a été résolue. Merci d'avoir contacté notre service clientèle.";
         emailService.sendEmail(to, subject, text);
     }
     // Mettre à jour l'état de la plainte dans la base de données
     complaint.setStateofcomplaint(Stateofcomplaint.complaintresolved);
     complaintRepository.save(complaint);
 }*/
 /*  public void sendResolvedComplaintsEmails() {
     List<Complaint> resolvedComplaints = complaintRepository.findByStateOfComplaint(Stateofcomplaint.complaintresolved);
     for (Complaint complaint : resolvedComplaints) {
         // envoyer l'e-mail à l'utilisateur correspondant ici
         User user = complaint.getUser();
         String userEmail = user.getEmail();
         String emailBody = "Votre réclamation a été traitée avec succès.";
         emailService.sendEmail(userEmail, "Réclamation traitée", emailBody);
     }
 } */
}















