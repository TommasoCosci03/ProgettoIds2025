package it.unicam.cs.ids25.model.Controller;

import it.unicam.cs.ids25.model.Dto.AziendaDTO;
import it.unicam.cs.ids25.model.Service.AziendaService;
import it.unicam.cs.ids25.model.Utenti.Azienda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
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
        return service.crea(dto);
    }

    @DeleteMapping("/eliminaAzienda")
    public ResponseEntity<String> eliminaAzienda(){
        return service.eliminaAzienda();

    }

    @PostMapping("/spedisci/{id}")
    public ResponseEntity<String> spedisci (@PathVariable Long id) {
        return service.effettuaSpedizione(id);

    }

    @GetMapping("/getNotifiche")
    public ResponseEntity<StringBuilder> getNotifiche() {
        return service.notificheById();
    }
}