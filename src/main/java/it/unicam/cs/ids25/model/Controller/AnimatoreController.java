package it.unicam.cs.ids25.model.Controller;

import it.unicam.cs.ids25.model.Dto.AnimatoreDTO;
import it.unicam.cs.ids25.model.Dto.EventoDTO;
import it.unicam.cs.ids25.model.Evento;
import it.unicam.cs.ids25.model.Repository.AnimatoreRepository;
import it.unicam.cs.ids25.model.Service.AnimatoreService;
import it.unicam.cs.ids25.model.Service.AziendaService;
import it.unicam.cs.ids25.model.Utenti.Acquirente;
import it.unicam.cs.ids25.model.Utenti.Animatore;
import it.unicam.cs.ids25.model.Utenti.Azienda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/animatore")
public class AnimatoreController {
    private final AnimatoreService service;
    private final AnimatoreRepository animatoreRepo;
    private final AziendaService aziendaService;

    @Autowired
    public AnimatoreController(AnimatoreService service, AnimatoreRepository animatoreRepo, AziendaService aziendaService) {
        this.service = service;
        this.animatoreRepo = animatoreRepo;
        this.aziendaService = aziendaService;
    }

    @PostMapping("/creaAnimatore")
    public ResponseEntity<String> crea(@RequestBody AnimatoreDTO dto){
        service.creaAnimatore(dto);
        return ResponseEntity.status(200).body(dto.getNome() + " creato con successo");
    }

    @PostMapping("/creaEvento")
    public ResponseEntity<String> creaEvento(@RequestBody EventoDTO dto)
    {
        service.creaEvento(dto);
            return ResponseEntity.status(200).body(" Evento creato con successo ");

    }



    @GetMapping("/{id}")
    public ResponseEntity<Animatore> trovaById(Long idAnimatore) {
        return ResponseEntity.ok(service.trova(idAnimatore));
    }

    @GetMapping("/trovaTutti")
    public ResponseEntity<List<Evento>> trovaTutti(){
        return ResponseEntity.ok(service.trovaEventi());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> elimina(@PathVariable Long id){
        service.elimina(id);
        return ResponseEntity.status(200).body("Animatore eliminato con successo");
    }







}
