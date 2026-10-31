package tn.esprim.gestionfoyer.services;

import tn.esprim.gestionfoyer.entities.Etudiant;

import java.util.List;

public interface IEtudiantService {
    List<Etudiant> retrieveAllEtudiants();
    Etudiant retrieveEtudiant(Long etudiantId);
    Etudiant addEtudiant(Etudiant e);
    void removeEtudiant(Long etudiantId);
    Etudiant modifyEtudiant(Etudiant e);
}