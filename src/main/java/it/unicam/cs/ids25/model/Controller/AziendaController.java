package it.unicam.cs.ids25.model.Controller;

import it.unicam.cs.ids25.model.Dto.AziendaDTO;
import it.unicam.cs.ids25.model.Service.AziendaService;
import it.unicam.cs.ids25.model.Utenti.Azienda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aziende")
public class AziendaController {
    private final AziendaService service;

    @Autowired
    public AziendaController(AziendaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> crea(@RequestBody AziendaDTO dto) {
        service.crea(dto);
        return ResponseEntity.status(200).body(dto.getNome() + " creato con successo");
    }

    @GetMapping
    public ResponseEntity<List<Azienda>> trovaTutti() {
        return ResponseEntity.ok(service.trovaTutte());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> elimina(@PathVariable Long id) {
        service.elimina(id);
        return ResponseEntity.status(200).body("Azienda eliminata con successo");
    }
}