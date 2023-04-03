package tn.esprit.infini.Pidev.entities;
import jakarta.persistence.*;


import java.io.Serializable;
import java.util.Date;


public enum Statut {
    ACTIF,
    EN_ATTENTE,
    EN_RETARDISSEMENT,
    REMBOURSE,
    ANNULE
}
