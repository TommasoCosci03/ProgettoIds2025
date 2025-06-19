package it.unicam.cs.ids25.model.Controller;

import it.unicam.cs.ids25.model.Dto.AcquirenteDTO;
import it.unicam.cs.ids25.model.Dto.EventoDTO;
import it.unicam.cs.ids25.model.Service.AcquirenteService;
import it.unicam.cs.ids25.model.Utenti.Acquirente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/acquirenti")
public class AcquirenteController {
    private final AcquirenteService service;

    @Autowired
    public AcquirenteController(AcquirenteService service) {
        this.service = service;
    }

    @PostMapping("/creaAcquirente")
    public ResponseEntity<String> crea(@RequestBody AcquirenteDTO dto){
        return service.creaAcquirente(dto);

    }

//    @DeleteMapping("/eliminaAcquirente")
//    public ResponseEntity<String> elimina(){
//        return service.elimina();
//
//    }

    @PostMapping("/prenotaEvento/{idEvento}")
    public ResponseEntity<String> prenotaEvento(@PathVariable ("idEvento") Long idEvento){
        return service.prenotaEvento(idEvento);
    }

    @GetMapping("/listaEventiDisponibili")
    public ResponseEntity<List<EventoDTO>> eventiDisponibili(){
        return service.trovaEventi();
    }
}
