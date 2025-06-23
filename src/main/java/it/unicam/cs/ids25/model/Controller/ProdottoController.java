package it.unicam.cs.ids25.model.Controller;

import it.unicam.cs.ids25.model.Dto.PacchettoProdottiDTO;
import it.unicam.cs.ids25.model.Dto.ProdottoDTO;
import it.unicam.cs.ids25.model.Dto.ProdottoSingoloDTO;
import it.unicam.cs.ids25.model.Dto.ProdottoTrasformatoDTO;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import it.unicam.cs.ids25.model.Repository.AziendaRepository;
import it.unicam.cs.ids25.model.Service.AziendaService;
import it.unicam.cs.ids25.model.Service.ProdottoService;
import it.unicam.cs.ids25.model.Utenti.Distributore;
import it.unicam.cs.ids25.model.Utenti.Produttore;
import it.unicam.cs.ids25.model.Utenti.Trasformatore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

/**
 * La classe ProdottoController implementa tutti gli endpoint per l'interazione con i prodotti.
 */
@RestController
@RequestMapping("/api/prodotti")
public class ProdottoController {
    private final ProdottoService service;

    /**
     * Costruttore della classe con il service necessario per l'interazione con i prodotti.
     * @param service
     */
    public ProdottoController(ProdottoService service) {
        this.service = service;
    }

    /**
     * Metodo per la creazione di un prodotto singolo.
     * @param dto con tutte le informazioni del prodotto da creare.
     * @return ResponseEntity<String> - Risposta HTTP con il messaggio di risultato della creazione del prodotto singolo.''
     */
    @PostMapping("/prodottoSingolo")
    public ResponseEntity<String>creaProdottoSingolo(@RequestBody ProdottoSingoloDTO dto)  {
            return service.creaProdottoSingolo(dto);
    }


    /**
     * Metodo per la creazione di un prodotto trasformato.
     * @param dto con tutte le informazioni del prodotto trasformato da creare.
     * @return ResponseEntity<String> - Risposta HTTP con il messaggio di risultato della creazione del prodotto trasformato.''
     */
    @PostMapping("/prodottoTrasformato")
    public ResponseEntity<String>creaProdottoTrasformato(@RequestBody ProdottoTrasformatoDTO dto)  {
            return service.creaProdottoTrasformato(dto);
    }

    /**
     * Metodo per la creazione di un prodotto di un altro prodotto.
     * @param dto con tutte le informazioni del pacchetto da creare.
     * @return ResponseEntity<String> - Risposta HTTP con il messaggio di risultato della creazione del pacchetto.''
     */
    @PostMapping("/pacchetto")
    public ResponseEntity<String>creaPacchetto(@RequestBody PacchettoProdottiDTO dto)  {
            return service.creaPacchetto(dto);
    }


    /**
     * Metodo per la visualizzazione di tutti i prodotti disponibili.
     * @return List<ProdottoDTO> - List di prodotti disponibili.
     */
    @GetMapping
    public List<ProdottoDTO> trovaTutti(){
            return service.trovaTutti().stream().map(ProdottoDTO::fromEntity).toList();
    }

    /**
     * Metodo per l'eliminazione del prodotto.
     * @param idProdotto da eliminare
     * @return ResponseEntity<String> - Risposta HTTP con il messaggio di risultato dell'eliminazione del prodotto.'''
     */
    @DeleteMapping("/eliminaProdotto/{idProdotto}")
    public ResponseEntity<String> eliminaProdotto(@PathVariable Long idProdotto)  {
            return service.eliminaProdotto(idProdotto);

    }
}
