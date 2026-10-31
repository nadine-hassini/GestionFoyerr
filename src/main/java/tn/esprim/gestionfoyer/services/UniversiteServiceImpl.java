package tn.esprim.gestionfoyer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprim.gestionfoyer.entities.Universite;
import tn.esprim.gestionfoyer.repositories.UniversiteRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UniversiteServiceImpl implements IUniversiteService {

    private final UniversiteRepository universiteRepository;

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
}