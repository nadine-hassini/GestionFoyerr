package tn.esprim.gestionfoyer.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprim.gestionfoyer.entities.Universite;
import tn.esprim.gestionfoyer.services.IUniversiteService;

import java.util.List;

@Tag(name = "Gestion Universite")
@RestController
@AllArgsConstructor
@RequestMapping("/universite")
public class UniversiteRestController {

    private final IUniversiteService universiteService;


    @GetMapping("/retrieve-all-universites")
    public List<Universite> getUniversites() {
        return universiteService.retrieveAllUniversites();
    }


    @GetMapping("/retrieve-universite/{universite-id}")
    public Universite retrieveUniversite(@PathVariable("universite-id") Long uId) {
        return universiteService.retrieveUniversite(uId);
    }


    @PostMapping("/add-universite")
    public Universite addUniversite(@RequestBody Universite u) {
        return universiteService.addUniversite(u);
    }


    @DeleteMapping("/remove-universite/{universite-id}")
    public void removeUniversite(@PathVariable("universite-id") Long uId) {
        universiteService.removeUniversite(uId);
    }


    @PutMapping("/modify-universite")
    public Universite modifyUniversite(@RequestBody Universite u) {
        return universiteService.modifyUniversite(u);
    }


    @PostMapping("/affecterFoyer/{idFoyer}/{nomUniversite}")
    public Universite affecterFoyerAUniversite(
            @PathVariable("idFoyer") Long idFoyer,
            @PathVariable("nomUniversite") String nomUniversite) {
        return universiteService.affecterFoyerAUniversite(idFoyer, nomUniversite);
    }
    @PutMapping("/desaffecterFoyer/{idUniversite}")
    public Universite desaffecterFoyerUniversite(@PathVariable("idUniversite") Long idUniversite){
        Universite universite =universiteService.desaffecterFoyerUniversite(idUniversite);
        return universite;
    }
}