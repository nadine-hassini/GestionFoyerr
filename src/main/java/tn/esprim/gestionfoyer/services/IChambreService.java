package tn.esprim.gestionfoyer.services;

import org.springframework.stereotype.Service;
import tn.esprim.gestionfoyer.entities.Chambre;


@Service
public interface IChambreService {
    Chambre save (Chambre Chambre);
}
