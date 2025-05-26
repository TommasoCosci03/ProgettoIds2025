package it.unicam.cs.ids25.model.Controller;

import it.unicam.cs.ids25.model.Dto.AcquirenteDTO;
import it.unicam.cs.ids25.model.Service.AcquirenteService;
import it.unicam.cs.ids25.model.Utenti.Acquirente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/acquirenti")
public class AcquirenteController {
    private final AcquirenteService service;

    @Autowired
    public AcquirenteController(AcquirenteService service) {
        this.service = service;
    }

    @PostMapping("/creaAcquirente")
    public ResponseEntity<String> crea(@RequestBody AcquirenteDTO dto){
        service.creaAcquirente(dto);
        return ResponseEntity.status(200).body(dto.getNome() + " creato con successo");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Acquirente> trovaById(Long idAcquirente) {
        return ResponseEntity.ok(service.trova(idAcquirente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> elimina(@PathVariable Long id){
        service.elimina(id);
        return ResponseEntity.status(200).body("Acquirente eliminato con successo");
    }

}
