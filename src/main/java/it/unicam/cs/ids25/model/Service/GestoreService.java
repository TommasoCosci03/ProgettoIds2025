package it.unicam.cs.ids25.model.Service;

import it.unicam.cs.ids25.model.Repository.*;
import it.unicam.cs.ids25.model.Utenti.Curatore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class GestoreService {
    private final AcquirenteRepository acquirenteRepository;
    private final AziendaRepository aziendaRepository;
    private final AnimatoreRepository animatoreRepository;
    private final UtenteRepository utenteRepository;
    private final CuratoreRepository curatoreRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public GestoreService(AcquirenteRepository acquirenteRepository, AziendaRepository aziendaRepository, AnimatoreRepository animatoreRepository, UtenteRepository utenteRepository, CuratoreRepository curatoreRepository) {
        this.acquirenteRepository = acquirenteRepository;
        this.aziendaRepository = aziendaRepository;
        this.animatoreRepository = animatoreRepository;
        this.utenteRepository = utenteRepository;
        this.curatoreRepository = curatoreRepository;
    }

    public ResponseEntity<String> creaCuratore() {
        if (curatoreRepository.findByUsername("curatore") == null) {
            String encodedPassword = passwordEncoder.encode("curatore");
            Curatore curatore = new Curatore("curatore", encodedPassword);
            curatoreRepository.save(curatore);
            return ResponseEntity.ok("Curatore inizializzato con username 'curatore'");
        }
        return ResponseEntity.status(500).body("Curatore già presente");
    }


}
