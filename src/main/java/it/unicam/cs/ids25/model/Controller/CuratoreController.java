package it.unicam.cs.ids25.model.Controller;

import it.unicam.cs.ids25.model.Dto.ProdottoDTO;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import it.unicam.cs.ids25.model.Repository.ProdottoRepository;
import it.unicam.cs.ids25.model.Service.CuratoreService;
import it.unicam.cs.ids25.model.Utenti.Curatore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * La classe CuratoreController implementa tutti gli endpoint per l'interazione con il curatore.
 */
@RestController
@RequestMapping("/api/curatore")
public class CuratoreController {

    private final CuratoreService service;

    /**
     * Costruttore della classe con il service necessario per l'interazione con il curatore.
     * @param service
     */
    public CuratoreController(CuratoreService service) {
        this.service = service;
    }

    /**
     * Metodo per l'approvazione di un prodotto.
     * @param id del prodotto da approvare.
     * @return ResponseEntity<String> - Risposta HTTP con il messaggio di risultato dell'approvazione del prodotto.'
     */
    @PostMapping("/approva/{id}")
    public ResponseEntity<String> approva(@PathVariable("id")  Long id){
            return service.approva(id);
    }

    /**
     * Metodo per l'approvazione di tutti i prodotti.
     * @return ResponseEntity<String> - Risposta HTTP con il messaggio di risultato dell'approvazione di tutti i prodotti.'
     */
    @PostMapping("/approvaTutti")
    public ResponseEntity<String> approvaTutti(){
        return service.approvaTutti();
    }

    /**
     * Metodo per il rifiuto di un prodotto.
     * @param id del prodotto da rifiutare
     * @return ResponseEntity<String> - Risposta HTTP con il messaggio di risultato del rifiuto del prodotto.''
     */
    @DeleteMapping("/rifiuta/{id}")
    public ResponseEntity<String> rifiuta(@PathVariable("id")  Long id){
            return service.rifiuta(id);
    }

    /**
     * Metodo per la visualizzazione dei prodotti in attesa di approvazione.
     * @return ResponseEntity<List<ProdottoDTO>> - Risposta HTTP con il messaggio di risultato della visualizzazione dei prodotti in attesa di approvazione.'
     */
    @GetMapping("/richieste")
    public List<ProdottoDTO> getRichieste(){
        return service.getRichieste().stream().map(ProdottoDTO::fromEntity).toList();
    }
}
