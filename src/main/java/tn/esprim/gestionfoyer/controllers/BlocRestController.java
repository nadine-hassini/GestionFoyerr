package tn.esprim.gestionfoyer.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprim.gestionfoyer.entities.Bloc;
import tn.esprim.gestionfoyer.services.IBlocService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/bloc")
public class BlocRestController {

    private final IBlocService blocService;

    @GetMapping("/retrieve-all-blocs")
    public List<Bloc> getBlocs() {
        List<Bloc> listBlocs = blocService.retrieveAllBlocs();
        return listBlocs;
    }

    @GetMapping("/retrieve-bloc/{bloc-id}")
    public Bloc retrieveBloc(@PathVariable("bloc-id") Long bId) {
        Bloc bloc = blocService.retrieveBloc(bId);
        return bloc;
    }

    @PostMapping("/add-bloc")
    public Bloc addBloc(@RequestBody Bloc b) {
        Bloc bloc = blocService.addBloc(b);
        return bloc;
    }

    @DeleteMapping("/remove-bloc/{bloc-id}")
    public void removeBloc(@PathVariable("bloc-id") Long bId) {
        blocService.removeBloc(bId);
    }

    @PutMapping("/modify-bloc")
    public Bloc modifyBloc(@RequestBody Bloc b) {
        Bloc bloc = blocService.modifyBloc(b);
        return bloc;
    }
}