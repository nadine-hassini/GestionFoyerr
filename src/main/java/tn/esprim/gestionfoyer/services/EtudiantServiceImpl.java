package tn.esprim.gestionfoyer.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprim.gestionfoyer.entities.Etudiant;
import tn.esprim.gestionfoyer.entities.Reservation;
import tn.esprim.gestionfoyer.repositories.EtudiantRepository;
import tn.esprim.gestionfoyer.repositories.ReservationRepository;

import java.util.*;


@Service
@AllArgsConstructor
public class EtudiantServiceImpl implements IEtudiantService {

    private final EtudiantRepository etudiantRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public List<Etudiant> retrieveAllEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant retrieveEtudiant(Long etudiantId) {
        return etudiantRepository.findById(etudiantId).orElse(null);
    }

    @Override
    public Etudiant addEtudiant(Etudiant e) {
        return etudiantRepository.save(e);
    }

    @Override
    public void removeEtudiant(Long etudiantId) {
        etudiantRepository.deleteById(etudiantId);
    }

    @Override
    public Etudiant modifyEtudiant(Etudiant e) {
        return etudiantRepository.save(e);
    }

    @Override
    @Transactional
    public Etudiant affecterEtudiantAReservation(Long idEtudiant, Long idReservation) {
        Etudiant etudiant = etudiantRepository.findById(idEtudiant)
                .orElseThrow(() -> new EntityNotFoundException("Étudiant introuvable : " + idEtudiant));
        Reservation reservation = reservationRepository.findById(idReservation)
                .orElseThrow(() -> new EntityNotFoundException("Réservation introuvable : " + idReservation));

        Set<Etudiant> etudiants = reservation.getEtudiants();
        if (etudiants == null) {
            etudiants = new HashSet<>();
            reservation.setEtudiants(etudiants);
        }

        boolean dejaAffecte = etudiants.stream()
                .anyMatch(e -> Objects.equals(e.getIdEtudiant(), idEtudiant));
        if (dejaAffecte) {
            throw new IllegalStateException("L'étudiant " + idEtudiant + " est déjà dans cette réservation");
        }

        etudiants.add(etudiant);
        return etudiant;
    }

    @Override
    @Transactional
    public Etudiant desaffecterEtudiantDeReservation(Long idEtudiant, Long idReservation) {
        Etudiant etudiant = etudiantRepository.findById(idEtudiant)
                .orElseThrow(() -> new EntityNotFoundException("Étudiant introuvable : " + idEtudiant));
        Reservation reservation = reservationRepository.findById(idReservation)
                .orElseThrow(() -> new EntityNotFoundException("Réservation introuvable : " + idReservation));

        boolean retire = reservation.getEtudiants() != null
                && reservation.getEtudiants().removeIf(e -> Objects.equals(e.getIdEtudiant(), idEtudiant));
        if (!retire) {
            throw new IllegalStateException("L'étudiant " + idEtudiant + " n'est pas dans cette réservation");
        }
        return etudiant;
    }
}