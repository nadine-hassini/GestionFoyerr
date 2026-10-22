package tn.esprim.gestionfoyer.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprim.gestionfoyer.entities.Chambre;
import tn.esprim.gestionfoyer.repositories.ChambreRepository;
@Service
public class ChambreServiceImpl implements IChambreService {
    @Autowired
    private ChambreRepository chambreRepository;

    @Override
    public Chambre save(Chambre chambre){
        return chambreRepository.save(chambre);
    }
}
