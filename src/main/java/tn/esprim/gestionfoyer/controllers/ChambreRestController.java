package tn.esprim.gestionfoyer.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.esprim.gestionfoyer.entities.Chambre;
import tn.esprim.gestionfoyer.services.IChambreService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/chambre")
@Controller
public class ChambreRestController {
    @Autowired
    private IChambreService chambreService;


    @GetMapping("/retrieve-all-chambres")
    public List<Chambre> getChambres(){
        List<Chambre> listChambres=chambreService.retrieveAllChambres();
        return listChambres;
    }

    @GetMapping("/retrieve-chambre/{chambre-id}")
    public Chambre retrieveChambre(@PathVariable("chambre-id") Long chId){
        Chambre chambre = chambreService.retrieveChambre(chId);
        return chambre;
    }

    @PostMapping("/add-chambre")
    public Chambre addChambre(@RequestBody Chambre c){
        Chambre chambre = chambreService.addChambre(c);
        return chambre;
    }

    @DeleteMapping("/remove-chambre/{chambre-id}")
    public void removeChambre(@PathVariable("chambre-id") Long chId ){
        chambreService.removeChambre(chId);
    }

    @PutMapping("/modify-chambre")
    public Chambre modifyChambre(@RequestBody Chambre c){
        Chambre chambre = chambreService.modifyChambre(c);
        return chambre;
    }
    @PutMapping("/affecter-bloc/{chambre-id}/{bloc-id}")
    public Chambre affecterChambreABloc(@PathVariable("chambre-id") Long idChambre,
                                        @PathVariable("bloc-id") Long idBloc) {
        return chambreService.affecterChambreABloc(idChambre, idBloc);
    }

    @PutMapping("/desaffecter-bloc/{chambre-id}")
    public Chambre desaffecterChambreDeBloc(@PathVariable("chambre-id") Long idChambre) {
        return chambreService.desaffecterChambreDeBloc(idChambre);
    }
}
