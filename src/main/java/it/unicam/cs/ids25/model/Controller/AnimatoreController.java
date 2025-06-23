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

/**
 * La classe AnimatoreController implementa tutti gli endpoint per l'interazione con l'animatore.
 */
@Controller
@RequestMapping("/api/animatore")
public class AnimatoreController {

    private final AnimatoreService service;

    /**
     * Costruttore della classe con il service necessario per l'interazione con l'animatore.
     * @param service
     */
    public AnimatoreController(AnimatoreService service) {
        this.service = service;

    }

    /**
     * Metodo per la creazione di un animatore.
     * @param dto
     * @return ResponseEntity<String> - Risposta HTTP con il messaggio di risultato della creazione dell'animatore.'
     */
    @PostMapping("/creaAnimatore")
    public ResponseEntity<String> crea(@RequestBody AnimatoreDTO dto){
        return service.creaAnimatore(dto);

    }

    /**
     * Metodo per la creazione di un evento.
     * @param dto
     * @return ResponseEntity<String> - Risposta HTTP con il messaggio di risultato della creazione dell'evento.'
     */
    @PostMapping("/creaEvento")
    public ResponseEntity<String> creaEvento(@RequestBody EventoDTO dto)
    {
           return service.creaEvento(dto);
    }

    /**
     * Metodo per la visualizzazione di tutti gli eventi disponibili.
     * @return ResponseEntity<List<EventoDTO>> - Risposta HTTP con il messaggio di risultato della visualizzazione di tutti gli eventi disponibili.'
     */
    @GetMapping("/trovaTuttiEventi")
    public ResponseEntity<List<EventoDTO>> trovaTutti(){
        return ResponseEntity.ok(service.trovaEventi());
    }

    /*@DeleteMapping("/eliminaAnimatore/{id}")
    public ResponseEntity<String> eliminaAnimatore(@PathVariable Long id){
        return service.elimina(id);
    }*/

    /**
     * Metodo per l'invito di un'azienda ad un evento.
     * @param idEvento
     * @param idAzienda
     * @return ResponseEntity<String> - Risposta HTTP con il messaggio di risultato dell'invito dell'azienda ad un evento.''
     */
    @PostMapping("/invitaAzienda/{idEvento}/{idAzienda}")
    public ResponseEntity<String> invitaAdEvento(@PathVariable ("idEvento") Long idEvento, @PathVariable ("idAzienda") Long idAzienda){
        return service.invitaAzienda(idEvento, idAzienda);
    }
}
