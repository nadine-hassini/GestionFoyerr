package tn.esprim.gestionfoyer.services;

import org.springframework.stereotype.Service;
import tn.esprim.gestionfoyer.entities.Bloc;
import tn.esprim.gestionfoyer.repositories.BlocRepository;

import java.util.List;

@Service
public class BlocServiceImpl implements IBlocService {

    private final BlocRepository blocRepository;

    public BlocServiceImpl(BlocRepository blocRepository) {
        this.blocRepository = blocRepository;
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
}