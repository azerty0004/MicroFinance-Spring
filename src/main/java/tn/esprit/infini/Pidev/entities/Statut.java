package tn.esprit.infini.Pidev.entities;

import jakarta.persistence.*;


import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;


public enum Statut {
    ACTIF,
    EN_ATTENTE,
    EN_RETARDISSEMENT,
    REMBOURSE,
    ANNULE,
    Non_Approuvé,
    Approuvé
}
