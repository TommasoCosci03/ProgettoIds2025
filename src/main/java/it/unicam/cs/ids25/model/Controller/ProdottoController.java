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
    private final AziendaRepository repo;
    public ProdottoController(ProdottoService service, AziendaRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    @PostMapping("/prodottoSingolo")
    public ResponseEntity<String>creaProdottoSingolo(@RequestBody ProdottoSingoloDTO dto) {
        if(repo.findById(dto.getIdAzienda()).get() instanceof Produttore) {
            service.creaProdottoSingolo(dto);
            return ResponseEntity.status(200).body(dto.getNome() + " creato con successo");
        }else
            return ResponseEntity.status(500).body(repo.findById(dto.getIdAzienda()).get().getNome() +
                    " Non puo' creare prodotti singoli perche' non e' un produttore");
    }


    @PostMapping("/prodottoTrasformato")
    public ResponseEntity<String>creaProdottoTrasformato(@RequestBody ProdottoTrasformatoDTO dto) {
        if (repo.findById(dto.getIdAzienda()).get() instanceof Trasformatore) {
            service.creaProdottoTrasformato(dto);
            return ResponseEntity.status(200).body(dto.getNome() + " creato con successo");
        } else
            return ResponseEntity.status(500).body(repo.findById(dto.getIdAzienda()).get().getNome() +
                    " Non puo' creare prodotti trasformati perche' non e' un trasformatore");
    }

    @PostMapping("/pacchetto")
    public ResponseEntity<String>creaPacchetto(@RequestBody PacchettoProdottiDTO dto){
        if(repo.findById(dto.getIdAzienda()).get() instanceof Distributore) {
            service.creaPacchetto(dto);
            return ResponseEntity.status(200).body(dto.getNome() + " creato con successo");
        }else
            return ResponseEntity.status(500).body(repo.findById(dto.getIdAzienda()).get().getNome() +
                    " Non puo' creare pacchetti perche' non e' un distributore");
    }


    @GetMapping
    public List<ProdottoDTO> trovaTutti(){
        return service.trovaTutti().stream().map(ProdottoDTO::fromEntity).toList();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> elimina(@PathVariable Long id){
        service.elimina(id);
        return ResponseEntity.status(200).body("Prodotto eliminato con successo");
    }
}
