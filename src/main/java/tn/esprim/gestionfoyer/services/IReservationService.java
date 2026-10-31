package tn.esprim.gestionfoyer.services;

import tn.esprim.gestionfoyer.entities.Reservation;

import java.util.List;

public interface IReservationService {
    List<Reservation> retrieveAllReservations();
    Reservation retrieveReservation(Long reservationId);
    Reservation addReservation(Reservation r);
    void removeReservation(Long reservationId);
    Reservation modifyReservation(Reservation r);
}