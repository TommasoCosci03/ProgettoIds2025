package it.unicam.cs.ids25.model.Controller;

import it.unicam.cs.ids25.model.Dto.AziendaDTO;
import it.unicam.cs.ids25.model.Dto.SedeDTO;
import it.unicam.cs.ids25.model.Service.AziendaService;
import it.unicam.cs.ids25.model.Utenti.Azienda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

/**
 * La classe AziendaController implementa tutti gli endpoint per l'interazione con l'azienda.
 */
@RestController
@RequestMapping("/api/aziende")
public class AziendaController {

    private final AziendaService service;

    /**
     * Costruttore della classe con il service necessario per l'interazione con l'azienda.
     * @param service
     */
    @Autowired
    public AziendaController(AziendaService service) {
        this.service = service;
    }

    /**
     * Metodo per la creazione di un'azienda.
     * @param dto
     * @return ResponseEntity<String> - Risposta HTTP con il messaggio di risultato della creazione dell'azienda.'
     */
    @PostMapping
    public ResponseEntity<String> crea(@RequestBody AziendaDTO dto) {
        return service.crea(dto);
    }

    /**
     * Metodo per la visualizzazione di tutte le aziende disponibili.
     * @return ResponseEntity<List<AziendaDTO>> - Risposta HTTP con il messaggio di risultato della visualizzazione di tutte le aziende disponibili.'
     */
    @DeleteMapping("/eliminaAzienda")
    public ResponseEntity<String> eliminaAzienda(){
        return service.eliminaAzienda();

    }

    /**
     * Metodo per la spedizione di un ordine
     * @param id della notifica dell'ordine da spedire
     * @return ResponseEntity<String> - Risposta HTTP con il messaggio di risultato della spedizione dell'ordine.
     */
    @PostMapping("/spedisci/{id}")
    public ResponseEntity<String> spedisci (@PathVariable Long id) {
        return service.effettuaSpedizione(id);

    }

    /**
     * Metodo per l'aggiunta di una quantita' ad un prodotto
     * @param idProdotto del prodotto a cui si vuole aggiungere una certa quantita'
     * @param quantita da aggiungere al rpodotto
     * @return ResponseEntity<String> - Risposta HTTP con il messaggio di risultato dell'aggiunta della quantita' al prodotto passato.
     */
    @PostMapping("/aggiungiQuantita/{idProdotto}/{quantita}")
    public ResponseEntity<String> aggiungiQuantita (@PathVariable ("idProdotto") Long idProdotto, @PathVariable("quantita") int quantita){
        return service.aggiungiQuantità(idProdotto,quantita);
    }

    /**
     * Metodo per la visualizzazione delle notifiche di un'azienda.
     * @return ResponseEntity<StringBuilder> - Risposta HTTP con il messaggio di risultato della visualizzazione delle notifiche dell'azienda.'
     */
    @GetMapping("/getNotifiche")
    public ResponseEntity<StringBuilder> getNotifiche() {
        return service.notificheById();
    }

    @GetMapping("/sedi")
    public List<SedeDTO>  getSedi(){
        return service.getSedi();
    }
}