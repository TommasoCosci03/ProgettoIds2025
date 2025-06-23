package it.unicam.cs.ids25.model.Controller;


import it.unicam.cs.ids25.model.Dto.OrdineDTO;
import it.unicam.cs.ids25.model.Dto.ProdottoOrdineDTO;
import it.unicam.cs.ids25.model.Service.OrdineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * La classe OrdineController implementa tutti gli endpoint per l'interazione con l'ordine.
 */
@RestController
@RequestMapping("/api/ordine")
public class OrdineController {

    private final OrdineService ordineService;

    /**
     * Costruttore della classe con il service necessario per l'interazione con l'ordine.
     * @param ordineService
     */
    public OrdineController(OrdineService ordineService) {
        this.ordineService = ordineService;
    }

    /**
     * Metodo per l'aggiunta di un prodotto al carrello dell'utente.
     * @param dto
     * @return ResponseEntity<String> - Risposta HTTP con il messaggio di risultato dell'aggiunta del prodotto al carrello dell'utente.''
     */
    @PostMapping("/aggiungiProdottoCarrello")
    public ResponseEntity<String> aggiungiProdottoCarrello(@RequestBody ProdottoOrdineDTO dto) {
        return ordineService.aggiungiAlCarrello(dto);
    }

    /**
     * Metodo per la cancellazione del carrello dell'utente.
     * @return ResponseEntity<String> - Risposta HTTP con il messaggio di risultato della cancellazione del carrello dell'utente.'
     */
    @PostMapping("/cancellaCarrello")
    public ResponseEntity<String> cancellaCarrello() {
        return ordineService.cancellaCarrello();
    }

    /**
     * Metodo per la visualizzazione del carrello dell'utente.
     * @return ResponseEntity<String> - Risposta HTTP con il messaggio di risultato della visualizzazione del carrello dell'utente.'
     */
    @GetMapping("/getCarrello")
    public ResponseEntity<String> getCarrello() {
        return ordineService.getCarrello();
    }

    /**
     * Metodo per confermare il carrello dell'utente.
     * @param dto che descrive il carrello dell'utente da confermare.
     * @return ResponseEntity<String> - Risposta HTTP con il messaggio di risultato della conferma del carrello dell'utente.'
     */
    @PostMapping("/effettuaOrdine")
    public ResponseEntity<String> effettuaOrdine(@RequestBody OrdineDTO dto) {
        return ordineService.effettuaOrdine(dto);

    }

}
