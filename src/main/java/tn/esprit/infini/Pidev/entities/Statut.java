package tn.esprit.infini.Pidev.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

public enum Statut {
    ACTIF,
    EN_ATTENTE,
    EN_RETAEDISSEMENT,
    REMBOURSE,
    ANNULE
}
