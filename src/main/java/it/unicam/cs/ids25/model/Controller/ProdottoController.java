package it.unicam.cs.ids25.model.Controller;

import it.unicam.cs.ids25.model.Dto.ProdottoSingoloDTO;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import it.unicam.cs.ids25.model.Service.ProdottoService;
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
    public ResponseEntity<String>crea(@RequestBody ProdottoSingoloDTO dto) {
        service.crea(dto);
        return ResponseEntity.status(200).body(dto.getNome() + " creato con successo");
    }

    @PostMapping("/prodottoTrasformato")


    @GetMapping
    public ResponseEntity<List<Prodotto>>trovaTutti(){
        return ResponseEntity.ok(service.trovaTutti());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> elimina(@PathVariable Long id){
        service.elimina(id);
        return ResponseEntity.status(200).body("Prodotto eliminato con successo");
    }
}
