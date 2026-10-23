package tn.esprim.gestionfoyer.services;

import org.springframework.stereotype.Service;
import tn.esprim.gestionfoyer.entities.Chambre;

import java.util.List;



public interface IChambreService {
    Chambre save (Chambre Chambre);
    public List<Chambre> retrieveAllChambres();
    public Chambre retrieveChambre(Long chambreId);
    public Chambre addChambre(Chambre c);
    public void removeChambre(Long chambreId);
    public Chambre modifyChambre(Chambre chambre);
}
