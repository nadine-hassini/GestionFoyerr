package tn.esprim.gestionfoyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprim.gestionfoyer.entities.Bloc;

@Repository
public interface BlocRepository extends JpaRepository<Bloc,Long> {

}
