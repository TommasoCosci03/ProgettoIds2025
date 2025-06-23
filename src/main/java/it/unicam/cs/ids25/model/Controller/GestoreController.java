package it.unicam.cs.ids25.model.Controller;

import it.unicam.cs.ids25.model.Repository.*;
import it.unicam.cs.ids25.model.Service.*;
import it.unicam.cs.ids25.model.Utenti.Acquirente;
import it.unicam.cs.ids25.model.Utenti.Animatore;
import it.unicam.cs.ids25.model.Utenti.Azienda;
import it.unicam.cs.ids25.model.Utenti.Curatore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller("/api/gestore")
public class GestoreController {

    private final GestoreService gestoreService;
    private final AcquirenteService acquirenteService;
    private final AziendaService aziendaService;

    /**
     * Costruttore della classe con il service necessario per l'interazione con il gestore.
     * @param gestoreService
     * @param acquirenteService
     * @param aziendaService
     */
    public GestoreController(GestoreService gestoreService, AcquirenteService acquirenteService, AziendaService aziendaService) {
        this.gestoreService = gestoreService;
        this.acquirenteService = acquirenteService;
        this.aziendaService = aziendaService;
    }

    /**
     * Metodo per la creazione di un curatore.
     * @return ResponseEntity<String> - Risposta HTTP con il messaggio di risultato della creazione del curatore.'
     */
    @PostMapping("/creaCuratore")
    public ResponseEntity<String> creaCuratore(){
        return gestoreService.creaCuratore();
    }

    /**
     * Metodo per trovare un'acquirente all'interno del database.
     * @param idAcquirente
     * @return ResponseEntity<Acquirente> - Risposta HTTP con il messaggio di risultato della visualizzazione dell'acquirente.'
     */
    /*@GetMapping("/trovaAcquirenteById/{id}")
    public ResponseEntity<Acquirente> trovaAcquirenteById(@PathVariable ("id") Long idAcquirente) {
        return ResponseEntity.ok(acquirenteService.trova(idAcquirente));
    }*/

    /*@GetMapping("/trovaAnimatoreById/{id}")
    public ResponseEntity<Animatore> trovaAnimatoreById(@PathVariable("id") Long idAnimatore) {
        return ResponseEntity.ok(animatoreService.trova(idAnimatore));
    }*/

    /**
     * Metodo che restituisce tutte le aziende presenti nel database.
     * @return ResponseEntity<List<Azienda>> - Risposta HTTP con il messaggio di risultato della visualizzazione delle aziende.'
     */
    @GetMapping("/trovaAziende")
    public ResponseEntity<List<Azienda>> trovaTutti() {
        return ResponseEntity.ok(aziendaService.trovaTutte());
    }
}
