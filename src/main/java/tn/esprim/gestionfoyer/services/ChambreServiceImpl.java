package tn.esprim.gestionfoyer.services;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprim.gestionfoyer.entities.Chambre;
import tn.esprim.gestionfoyer.repositories.BlocRepository;
import tn.esprim.gestionfoyer.repositories.ChambreRepository;
import tn.esprim.gestionfoyer.entities.Bloc;

import java.util.List;

@Service
public class ChambreServiceImpl implements IChambreService {
    @Autowired
    private ChambreRepository chambreRepository;
    private BlocRepository blocRepository;

    @Override
    public Chambre save(Chambre chambre){
        return chambreRepository.save(chambre);
    }

    @Override
    public List<Chambre> retrieveAllChambres() {
        return chambreRepository.findAll();
    }

    @Override
    public Chambre retrieveChambre(Long chambreId) {
        return chambreRepository.findById(chambreId).get();
    }

    @Override
    public Chambre addChambre(Chambre c) {
        return chambreRepository.save(c);
    }

    @Override
    public void removeChambre(Long chambreId) {
        chambreRepository.deleteById(chambreId);

    }

    @Override
    public Chambre modifyChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @Override
    @Transactional
    public Chambre affecterChambreABloc(Long idChambre, Long idBloc) {
        Chambre chambre = chambreRepository.findById(idChambre)
                .orElseThrow(() -> new EntityNotFoundException("Chambre introuvable : " + idChambre));
        Bloc bloc = blocRepository.findById(idBloc)
                .orElseThrow(() -> new EntityNotFoundException("Bloc introuvable : " + idBloc));

        if (chambre.getBloc() != null && !chambre.getBloc().getIdBloc().equals(idBloc)) {
            throw new IllegalStateException(
                    "La chambre " + chambre.getNumeroChambre() + " est déjà affectée à un autre bloc");
        }
        chambre.setBloc(bloc);
        return chambre;
    }

    @Override
    @Transactional
    public Chambre desaffecterChambreDeBloc(Long idChambre) {
        Chambre chambre = chambreRepository.findById(idChambre)
                .orElseThrow(() -> new EntityNotFoundException("Chambre introuvable : " + idChambre));

        if (chambre.getBloc() == null) {
            throw new IllegalStateException("La chambre " + chambre.getNumeroChambre() + " n'a pas de bloc");
        }
        chambre.setBloc(null);
        return chambre;
    }

}
