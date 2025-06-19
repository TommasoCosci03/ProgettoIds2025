package it.unicam.cs.ids25.model.Controller;

import it.unicam.cs.ids25.model.Dto.AnimatoreDTO;
import it.unicam.cs.ids25.model.Dto.EventoDTO;
import it.unicam.cs.ids25.model.Repository.AnimatoreRepository;
import it.unicam.cs.ids25.model.Service.AnimatoreService;
import it.unicam.cs.ids25.model.Service.AziendaService;
import it.unicam.cs.ids25.model.Utenti.Animatore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/animatore")
public class AnimatoreController {

    @Autowired
    private final AnimatoreService service;



    public AnimatoreController(AnimatoreService service) {
        this.service = service;

    }

    @PostMapping("/creaAnimatore")
    public ResponseEntity<String> crea(@RequestBody AnimatoreDTO dto){
        return service.creaAnimatore(dto);

    }

    @PostMapping("/creaEvento")
    public ResponseEntity<String> creaEvento(@RequestBody EventoDTO dto)
    {
           return service.creaEvento(dto);
    }

    @GetMapping("/trovaTuttiEventi")
    public ResponseEntity<List<EventoDTO>> trovaTutti(){
        return ResponseEntity.ok(service.trovaEventi());
    }

//    @DeleteMapping("/eliminaAnimatore/{id}")
//    public ResponseEntity<String> eliminaAnimatore(@PathVariable Long id){
//        return service.elimina(id);
//    }

    @PostMapping("/invitaAzienda/{idEvento}/{idAzienda}")
    public ResponseEntity<String> invitaAdEvento(@PathVariable ("idEvento") Long idEvento, @PathVariable ("idAzienda") Long idAzienda){
        return service.invitaAzienda(idEvento, idAzienda);
    }
}
