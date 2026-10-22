package tn.esprim.gestionfoyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprim.gestionfoyer.entities.Etudiant;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {
    List<Etudiant> findByDateNaissanceBetween(LocalDate dateDebut, LocalDate dateFin);
    List<Etudiant> findByDateNaissanceBetween(Date dFrom, Date dTo);
}
