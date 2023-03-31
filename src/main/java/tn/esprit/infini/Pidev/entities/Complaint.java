package tn.esprit.infini.Pidev.entities;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table( name = "Complaint")

public class Complaint implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcomplaint;
    private Date dateofcomplaint;
    @Enumerated(EnumType.STRING)
    private Stateofcomplaint stateofcomplaint;
    private String description;
    @Enumerated(EnumType.STRING)
    private Typecomplaint typecomplaint;

     // @ManyToOne
    //User user;
}
