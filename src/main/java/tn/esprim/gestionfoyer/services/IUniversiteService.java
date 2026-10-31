package tn.esprim.gestionfoyer.services;

import tn.esprim.gestionfoyer.entities.Universite;

import java.util.List;

public interface IUniversiteService {
    List<Universite> retrieveAllUniversites();
    Universite retrieveUniversite(Long universiteId);
    Universite addUniversite(Universite u);
    void removeUniversite(Long universiteId);
    Universite modifyUniversite(Universite u);
}