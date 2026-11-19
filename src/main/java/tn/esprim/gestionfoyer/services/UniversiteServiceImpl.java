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

@Service
@AllArgsConstructor
public class UniversiteServiceImpl implements IUniversiteService {

    private final UniversiteRepository universiteRepository;
    private final FoyerRepository foyerRepository;

    @Override
    public List<Universite> retrieveAllUniversites() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite retrieveUniversite(Long universiteId) {
        return universiteRepository.findById(universiteId).orElse(null);
    }

    @Override
    public Universite addUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public void removeUniversite(Long universiteId) {
        universiteRepository.deleteById(universiteId);
    }

    @Override
    public Universite modifyUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Transactional
    @Override
    public Universite affecterFoyerAUniversite(Long idFoyer, String nomUniversite) {
        Foyer foyer = foyerRepository.findById(idFoyer)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Foyer introuvable avec l'id : " + idFoyer));
        Universite universite = universiteRepository.findByNomUniversite(nomUniversite)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Université introuvable avec le nom : " + nomUniversite));

        if (foyer.getUniversite() != null) {
            throw new IllegalStateException(
                    "Le foyer " + idFoyer + " est déjà affecté à une université");
        }
        if (universite.getFoyer() != null) {
            throw new IllegalStateException(
                    "L'université " + nomUniversite + " a déjà un foyer");
        }
        universite.setFoyer(foyer);
        foyer.setUniversite(universite);
        return universite;
    }
}