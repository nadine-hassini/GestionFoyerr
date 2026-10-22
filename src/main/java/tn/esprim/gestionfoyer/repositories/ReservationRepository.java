package tn.esprim.gestionfoyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprim.gestionfoyer.entities.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
}
