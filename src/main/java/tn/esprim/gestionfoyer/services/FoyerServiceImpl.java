package tn.esprim.gestionfoyer.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprim.gestionfoyer.entities.Foyer;
import tn.esprim.gestionfoyer.entities.Universite;
import tn.esprim.gestionfoyer.repositories.FoyerRepository;
import tn.esprim.gestionfoyer.repositories.UniversiteRepository;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class FoyerServiceImpl implements IFoyerService {

    private final FoyerRepository foyerRepository;
    private final UniversiteRepository universiteRepository;


    @Override
    public List<Foyer> retrieveAllFoyers() {
        return foyerRepository.findAll();
    }

    @Override
    public Foyer retrieveFoyer(Long foyerId) {
        return foyerRepository.findById(foyerId).orElse(null);
    }

    @Override
    public Foyer addFoyer(Foyer f) {
        return foyerRepository.save(f);
    }

    @Override
    public void removeFoyer(Long foyerId) {
        foyerRepository.deleteById(foyerId);
    }

    @Override
    public Foyer modifyFoyer(Foyer f) {
        return foyerRepository.save(f);
    }

    @Override
    @Transactional
    public Foyer affecterFoyerAUniversite(Long idFoyer, Long idUniversite) {
        Foyer foyer = foyerRepository.findById(idFoyer)
                .orElseThrow(() -> new EntityNotFoundException("Foyer introuvable : " + idFoyer));
        Universite universite = universiteRepository.findById(idUniversite)
                .orElseThrow(() -> new EntityNotFoundException("Université introuvable : " + idUniversite));

        if (universite.getFoyer() != null) {
            throw new IllegalStateException("L'université " + idUniversite + " a déjà un foyer");
        }

        universite.setFoyer(foyer);
        universiteRepository.save(universite);
        return foyer;
    }

    @Override
    @Transactional
    public Foyer desaffecterFoyerDeUniversite(Long idFoyer, Long idUniversite) {
        Foyer foyer = foyerRepository.findById(idFoyer)
                .orElseThrow(() -> new EntityNotFoundException("Foyer introuvable : " + idFoyer));
        Universite universite = universiteRepository.findById(idUniversite)
                .orElseThrow(() -> new EntityNotFoundException("Université introuvable : " + idUniversite));

        if (universite.getFoyer() == null
                || !Objects.equals(universite.getFoyer().getIdFoyer(), idFoyer)) {
            throw new IllegalStateException("Le foyer " + idFoyer + " n'est pas affecté à cette université");
        }

        universite.setFoyer(null);
        universiteRepository.save(universite);
        return foyer;
    }


}