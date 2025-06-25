package it.unicam.cs.ids25.model.Service;

import it.unicam.cs.ids25.model.Repository.*;
import it.unicam.cs.ids25.model.Utenti.Curatore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import it.unicam.cs.ids25.model.Utenti.*;
import it.unicam.cs.ids25.model.Autenticazione.Utente;

/**
 * la classe GestoreService è responsabile della logica per le operazioni di{@link it.unicam.cs.ids25.model.Utenti.Gestore}
 */
@Service
public class GestoreService {
    private final AcquirenteRepository acquirenteRepository;
    private final AziendaRepository aziendaRepository;
    private final AnimatoreRepository animatoreRepository;
    private final UtenteRepository utenteRepository;
    private final CuratoreRepository curatoreRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Costruttore del service {@code GestoreService} per la gestione centralizzata
     * degli utenti del sistema. Inietta i repository necessari per accedere ai dati
     * di acquirenti, aziende, animatori, curatori e utenti generici.
     * il Gestore sarà responsabile della creazione del Curatore
     *
     * @param acquirenteRepository repository per la gestione degli {@link Acquirente}
     * @param aziendaRepository repository per la gestione delle {@link Azienda}
     * @param animatoreRepository repository per la gestione degli {@link Animatore}
     * @param utenteRepository repository per la gestione degli {@link Utente}
     * @param curatoreRepository repository per la gestione dei {@link Curatore}
     */
    public GestoreService(AcquirenteRepository acquirenteRepository, AziendaRepository aziendaRepository, AnimatoreRepository animatoreRepository, UtenteRepository utenteRepository, CuratoreRepository curatoreRepository) {
        this.acquirenteRepository = acquirenteRepository;
        this.aziendaRepository = aziendaRepository;
        this.animatoreRepository = animatoreRepository;
        this.utenteRepository = utenteRepository;
        this.curatoreRepository = curatoreRepository;
    }


    /**
     * metodo per creare il curatore.
     * il curatore verrà creato con credenziali standard
     * @return ResponseEntity<String> - Risposta HTTP con il messaggio di creazione'
     */
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
