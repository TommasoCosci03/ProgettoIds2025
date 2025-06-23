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

/**
 * La classe AcquirenteController implementa tutti gli endpoint per l'interazione con l'acquirente.
 */
@Controller
@RequestMapping("/api/acquirenti")
public class AcquirenteController {

    private final AcquirenteService service;

    /**
     * Costruttore della classe con il service necessario per l'interazione con l'acquirente.
     * @param service
     */
    @Autowired
    public AcquirenteController(AcquirenteService service) {
        this.service = service;
    }

    /**
     * Metodo per la creazione di un acquirente.
     * @param dto
     * @return ResponseEntity<String> - Risposta HTTP con il messaggio di risultato della creazione dell'acquirente.'
     */
    @PostMapping("/creaAcquirente")
    public ResponseEntity<String> crea(@RequestBody AcquirenteDTO dto){
        return service.creaAcquirente(dto);

    }

    /*@DeleteMapping("/eliminaAcquirente")
    public ResponseEntity<String> elimina(){
        return service.elimina();

    }*/

    /**
     * Metodo per la prenotazione di un evento.
     * @param idEvento
     * @return ResponseEntity<String> - Risposta HTTP con il messaggio di risultato della prenotazione dell'evento.'
     */
    @PostMapping("/prenotaEvento/{idEvento}")
    public ResponseEntity<String> prenotaEvento(@PathVariable ("idEvento") Long idEvento){
        return service.prenotaEvento(idEvento);
    }

    /**
     * Metodo per la ricarica del saldo dell'acquirente.
     * @param saldo
     * @return ResponseEntity<String> - Risposta HTTP con il messaggio di risultato della ricarica del saldo dell'acquirente.'
     */
    @PostMapping("/ricaricaSaldo/{saldo}")
    public ResponseEntity<String> ricaricaSaldo(@PathVariable ("saldo") double saldo){
        return service.ricaricaSaldo(saldo);
    }

    /**
     * Metodo per la visualizzazione di tutti gli eventi disponibili.
     * @return lista di eventi disponibili.
     */
    @GetMapping("/listaEventiDisponibili")
    public ResponseEntity<List<EventoDTO>> eventiDisponibili(){
        return service.trovaEventi();
    }


}
