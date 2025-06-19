package it.unicam.cs.ids25.model.Controller;

import it.unicam.cs.ids25.model.Repository.*;
import it.unicam.cs.ids25.model.Service.*;
import it.unicam.cs.ids25.model.Utenti.Acquirente;
import it.unicam.cs.ids25.model.Utenti.Animatore;
import it.unicam.cs.ids25.model.Utenti.Azienda;
import it.unicam.cs.ids25.model.Utenti.Curatore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller("/api/gestore")
public class GestoreController {

    private final CuratoreService curatoreService;
    private final GestoreService gestoreService;
    private final AcquirenteService acquirenteService;
    private final AziendaService aziendaService;
    private final AnimatoreService animatoreService;

    public GestoreController(CuratoreService curatoreService, GestoreService gestoreService, AcquirenteService acquirenteService, AziendaService aziendaService, AnimatoreService animatoreService) {
        this.curatoreService = curatoreService;
        this.gestoreService = gestoreService;
        this.acquirenteService = acquirenteService;
        this.aziendaService = aziendaService;
        this.animatoreService = animatoreService;
    }

    @PostMapping("/creaCuratore")
    public ResponseEntity<String> creaCuratore(){
        return gestoreService.creaCuratore();
    }

    @GetMapping("/trovaAcquirenteById/{id}")
    public ResponseEntity<Acquirente> trovaAcquirenteById(@PathVariable ("id") Long idAcquirente) {
        return ResponseEntity.ok(acquirenteService.trova(idAcquirente));
    }

//    @GetMapping("/trovaAnimatoreById/{id}")
//    public ResponseEntity<Animatore> trovaAnimatoreById(@PathVariable("id") Long idAnimatore) {
//        return ResponseEntity.ok(animatoreService.trova(idAnimatore));
//    }

    @GetMapping("/trovaAziende")
    public ResponseEntity<List<Azienda>> trovaTutti() {
        return ResponseEntity.ok(aziendaService.trovaTutte());
    }
}
