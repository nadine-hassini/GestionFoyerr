package tn.esprim.gestionfoyer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprim.gestionfoyer.entities.Reservation;
import tn.esprim.gestionfoyer.repositories.ReservationRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private final ReservationRepository reservationRepository;

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
}