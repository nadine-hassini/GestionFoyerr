package tn.esprim.gestionfoyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprim.gestionfoyer.entities.Foyer;

@Repository
public interface FoyerRepository extends JpaRepository<Foyer,Long> {
}
