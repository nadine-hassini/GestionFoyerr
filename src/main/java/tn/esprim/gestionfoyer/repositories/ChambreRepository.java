package tn.esprim.gestionfoyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprim.gestionfoyer.entities.Chambre;

@Repository

public interface ChambreRepository extends JpaRepository<Chambre,Long> {
}
