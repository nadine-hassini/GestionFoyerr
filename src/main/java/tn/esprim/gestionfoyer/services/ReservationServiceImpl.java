package tn.esprim.gestionfoyer.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprim.gestionfoyer.entities.Chambre;
import tn.esprim.gestionfoyer.entities.Reservation;
import tn.esprim.gestionfoyer.repositories.ChambreRepository;
import tn.esprim.gestionfoyer.repositories.ReservationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private final ReservationRepository reservationRepository;
    private final ChambreRepository chambreRepository;

    @Override
    public List<Reservation> retrieveAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation retrieveReservation(Long reservationId) {
        return reservationRepository.findById(reservationId).orElse(null);
    }

    @Override
    public Reservation addReservation(Reservation r) {
        return reservationRepository.save(r);
    }

    @Override
    public void removeReservation(Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }

    @Override
    public Reservation modifyReservation(Reservation r) {
        return reservationRepository.save(r);
    }

    @Override
    @Transactional
    public Reservation affecterReservationAChambre(Long idReservation, Long idChambre) {
        Reservation reservation = reservationRepository.findById(idReservation)
                .orElseThrow(() -> new EntityNotFoundException("Réservation introuvable : " + idReservation));
        Chambre chambre = chambreRepository.findById(idChambre)
                .orElseThrow(() -> new EntityNotFoundException("Chambre introuvable : " + idChambre));

        List<Reservation> reservations = chambre.getReservations();
        if (reservations == null) {
            reservations = new ArrayList<>();
            chambre.setReservations(reservations);
        }

        boolean dejaAffectee = reservations.stream()
                .anyMatch(r -> Objects.equals(r.getIdReservation(), idReservation));
        if (dejaAffectee) {
            throw new IllegalStateException("La réservation " + idReservation + " est déjà dans cette chambre");
        }

        reservations.add(reservation);
        chambreRepository.save(chambre);
        return reservation;
    }

    @Override
    @Transactional
    public Reservation desaffecterReservationDeChambre(Long idReservation, Long idChambre) {
        Reservation reservation = reservationRepository.findById(idReservation)
                .orElseThrow(() -> new EntityNotFoundException("Réservation introuvable : " + idReservation));
        Chambre chambre = chambreRepository.findById(idChambre)
                .orElseThrow(() -> new EntityNotFoundException("Chambre introuvable : " + idChambre));

        boolean retiree = chambre.getReservations() != null
                && chambre.getReservations().removeIf(r -> Objects.equals(r.getIdReservation(), idReservation));
        if (!retiree) {
            throw new IllegalStateException("La réservation " + idReservation + " n'est pas dans cette chambre");
        }

        chambreRepository.save(chambre);
        return reservation;
    }
}