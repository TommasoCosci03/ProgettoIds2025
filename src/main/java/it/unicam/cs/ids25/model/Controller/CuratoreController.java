package it.unicam.cs.ids25.model.Controller;

import it.unicam.cs.ids25.model.Dto.ProdottoDTO;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import it.unicam.cs.ids25.model.Repository.ProdottoRepository;
import it.unicam.cs.ids25.model.Service.CuratoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/curatore")
public class CuratoreController {
    private final CuratoreService service;
    private final ProdottoRepository repo;

    public CuratoreController(CuratoreService service, ProdottoRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    @PostMapping("/approva/{id}")
    public ResponseEntity<String> approva(@PathVariable("id")  Long id){
        if(repo.existsById(id)){
            service.approva(id);
            return ResponseEntity.ok("Richiesta approvata con successo");
        }else
            return ResponseEntity.status(404).body("Prodotto non trovato");
    }

    @DeleteMapping("/rifiuta/{id}")
    public ResponseEntity<String> rifiuta(@PathVariable("id")  Long id){
        if(repo.existsById(id)){
            service.rifiuta(id);
            return ResponseEntity.ok("Richiesta rifiutata con successo");
        }else
            return ResponseEntity.status(404).body("Prodotto non trovato");

    }

    @GetMapping("/richieste")
    public List<ProdottoDTO> getRichieste(){
        return service.getRichieste().stream().map(ProdottoDTO::fromEntity).toList();
    }
}
