package tn.esprim.gestionfoyer.entities;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Data
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;
    private String anneUniversitaire;
    private boolean esValide;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Etudiant> etudiants;

}