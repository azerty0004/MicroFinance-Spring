package tn.esprit.infini.Pidev.entities;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    public String description;
    @Enumerated(EnumType.STRING)
    private Typecomplaint typecomplaint;

    @ManyToOne
    User user;
}
