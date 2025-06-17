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

import java.util.List;

@RestController
@RequestMapping("/api/prodotti")
public class ProdottoController {
    private final ProdottoService service;

    public ProdottoController(ProdottoService service) {
        this.service = service;

    }

    @PostMapping("/prodottoSingolo")
    public ResponseEntity<String>creaProdottoSingolo(@RequestBody ProdottoSingoloDTO dto) {

        return service.creaProdottoSingolo(dto);

    }


    @PostMapping("/prodottoTrasformato")
    public ResponseEntity<String>creaProdottoTrasformato(@RequestBody ProdottoTrasformatoDTO dto) {

            return service.creaProdottoTrasformato(dto);

    }

    @PostMapping("/pacchetto")
    public ResponseEntity<String>creaPacchetto(@RequestBody PacchettoProdottiDTO dto){

        return service.creaPacchetto(dto);

    }


    @GetMapping
    public List<ProdottoDTO> trovaTutti(){
        return service.trovaTutti().stream().map(ProdottoDTO::fromEntity).toList();
    }

    @DeleteMapping("/eliminaProdotto/{idProdotto}/{idAzienda}")
    public ResponseEntity<String> eliminaProdotto(@PathVariable Long idProdotto, @PathVariable Long idAzienda){
        return service.eliminaProdotto(idProdotto, idAzienda);

    }
}
