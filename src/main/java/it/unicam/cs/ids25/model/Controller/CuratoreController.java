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


    public CuratoreController(CuratoreService service) {
        this.service = service;

    }

    @PostMapping("/approva/{id}")
    public ResponseEntity<String> approva(@PathVariable("id")  Long id){

            return service.approva(id);

    }

    @PostMapping("/approvaTutti")
    public ResponseEntity<String> approvaTutti(){
        return service.approvaTutti();
    }

    @DeleteMapping("/rifiuta/{id}")
    public ResponseEntity<String> rifiuta(@PathVariable("id")  Long id){

            return service.rifiuta(id);

    }

    @GetMapping("/richieste")
    public List<ProdottoDTO> getRichieste(){
        return service.getRichieste().stream().map(ProdottoDTO::fromEntity).toList();
    }
}
