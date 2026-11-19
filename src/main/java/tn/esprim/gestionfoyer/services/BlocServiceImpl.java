package tn.esprim.gestionfoyer.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprim.gestionfoyer.entities.Bloc;
import tn.esprim.gestionfoyer.entities.Chambre;
import tn.esprim.gestionfoyer.repositories.BlocRepository;
import tn.esprim.gestionfoyer.repositories.ChambreRepository;

import java.util.HashSet;
import java.util.List;

@Service
public class BlocServiceImpl implements IBlocService {

    private final BlocRepository blocRepository;
    private final ChambreRepository chambreRepository;

    public BlocServiceImpl(BlocRepository blocRepository, ChambreRepository chambreRepository) {
        this.blocRepository = blocRepository;
        this.chambreRepository = chambreRepository;
    }

    @Override
    public List<Bloc> retrieveAllBlocs() {
        return blocRepository.findAll();
    }

    @Override
    public Bloc retrieveBloc(Long blocId) {
        return blocRepository.findById(blocId).orElse(null);
        // ou : .orElseThrow(() -> new RuntimeException("Bloc introuvable : " + blocId));
    }

    @Override
    public Bloc addBloc(Bloc b) {
        return blocRepository.save(b);
    }

    @Override
    public void removeBloc(Long blocId) {
        blocRepository.deleteById(blocId);
    }

    @Override
    public Bloc modifyBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    @Transactional
    public Bloc affecterChambresABloc(List<Long> numChambres, Long idBloc) {
        Bloc bloc = blocRepository.findById(idBloc).orElseThrow(() -> new EntityNotFoundException("Bloc introuvable :" +idBloc));
        List<Chambre> chambres= chambreRepository.findAllByNumeroChambreIn(numChambres);
        if(chambres.size() != new HashSet<>(numChambres).size())
            throw new IllegalStateException("Une ou plusieurs chambres sont introuvables");
        for(Chambre chambre : chambres) {
            if (chambre.getBloc() != null && !chambre.getBloc().getIdBloc().equals(idBloc)){
                throw new IllegalStateException("la chambre" + chambre.getNumeroChampre() + "est deja affecté a un autre bloc");
            }
        for (Chambre chambre1 : chambres){
            chambre1.setBloc(bloc);
        }
        }

        return bloc;
    }
}