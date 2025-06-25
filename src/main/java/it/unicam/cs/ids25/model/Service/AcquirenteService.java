package it.unicam.cs.ids25.model.Service;

import it.unicam.cs.ids25.model.Autenticazione.CustomUserDetailsService;
import it.unicam.cs.ids25.model.Autenticazione.SecurityService;
import it.unicam.cs.ids25.model.Autenticazione.Utente;
import it.unicam.cs.ids25.model.Dto.AcquirenteDTO;
import it.unicam.cs.ids25.model.Dto.EventoDTO;
import it.unicam.cs.ids25.model.Evento;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import it.unicam.cs.ids25.model.Repository.AcquirenteRepository;
import it.unicam.cs.ids25.model.Repository.AnimatoreRepository;
import it.unicam.cs.ids25.model.Repository.EventoRepository;
import it.unicam.cs.ids25.model.Utenti.Acquirente;
import it.unicam.cs.ids25.model.Utenti.Animatore;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * la classe AcquirenteService è responsabile della logica per le operazioni di{@link Acquirente}
 */
@Service
@Transactional
public class AcquirenteService {

    private final AcquirenteRepository repoAcquirente;

    private final EventoRepository eventoRepo;

    private final AnimatoreRepository animatoreRepository;

    private final SecurityService securityService;

    private final PasswordEncoder passwordEncoder;

    private final CustomUserDetailsService customUserDetailsService;



    /**
     * Costruttore della classe service per l'entità {@link Acquirente}.
     * Inietta i repository e i servizi necessari.
     *
     * @param repo repository dell'entità {@link Acquirente}
     * @param eventoRepo repository dell'entità {@link Evento}
     * @param animatoreRepository repository dell'entità {@link Animatore}
     * @param passwordEncoder componente per codificare in modo sicuro le password
     * @param securityService servizio per la gestione della sicurezza e delle autorizzazioni
     * @param customUserDetailsService servizio per il caricamento dei dettagli utente personalizzati
     */
    public AcquirenteService(AcquirenteRepository repo, EventoRepository eventoRepo, AnimatoreRepository animatoreRepository, PasswordEncoder passwordEncoder, SecurityService securityService, CustomUserDetailsService customUserDetailsService) {
        this.repoAcquirente = repo;
        this.eventoRepo = eventoRepo;
        this.animatoreRepository = animatoreRepository;
        this.passwordEncoder = passwordEncoder;
        this.securityService = securityService;
        this.customUserDetailsService = customUserDetailsService;
    }


    /**
     * metodo per la creazione di un acquirente.
     * @param dto json passato nella richiesta
     * @return ResponseEntity<String> - Risposta HTTP con il messaggio di risultato della creazione dell'acquirente.'
     */
    public ResponseEntity<String> creaAcquirente(AcquirenteDTO dto) {

        if (dto.getUsername().isEmpty() || dto.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("username o Password non valido");
        }

        if (repoAcquirente.existsByUsername(dto.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username: " + dto.getUsername() + " già in uso");
        }

        Acquirente acquirente = new Acquirente(dto.getNome());
        acquirente.setUsername(dto.getUsername());

        acquirente.setPassword(passwordEncoder.encode(dto.getPassword()));
        repoAcquirente.save(acquirente);

        return ResponseEntity.ok(acquirente.getNome() + " creato con successo");

    }




    /**
     * metodo per la prenotazione di un evento.
     * @param idEvento id evento a cui prenotarsi
     * @return ResponseEntity<String> - Risposta HTTP con il messaggio di risultato della prenotazione dell'evento.'
     */
    public ResponseEntity<String> prenotaEvento(Long idEvento) {

        Acquirente acquirente = securityService.getAcquirenteCorrente();

        if (!eventoRepo.existsById(idEvento)) {
            return ResponseEntity.status(404).body("Evento non trovato");
        }

        Evento evento = eventoRepo.findById(idEvento).get();

        // Crea un nuova lista con i nuovi partecipanti
        List<Acquirente> partecipanti = evento.getPartecipanti();
        partecipanti.add(acquirente);

        // Aggiorna la variabile evento con la nuova lista
        evento.setPartecipanti(partecipanti);

        // Salva l'evento nel database'
        eventoRepo.save(evento);

        return ResponseEntity.status(200).body("Prenotazione avvenuta con successo all'evento " + evento.getNome());
    }



    /**
     * metodo per vedere tutti gli eventi.
     * @return ResponseEntity<List<EventoDTO> - Risposta HTTP con la lista degli eventi.'
     */
    public ResponseEntity<List<EventoDTO>> trovaEventi() {
        // Acquirente acquirente = securityService.getAcquirenteCorrente();
        List<EventoDTO> eventi = new ArrayList<>();
        for (Animatore animatore : animatoreRepository.findAll()) {
            for (Evento evento : animatore.getEventi()) {
                EventoDTO dto = new EventoDTO();
                dto.setNome(evento.getNome());
                dto.setDescrizione(evento.getDescrizione());
                dto.setLuogo(evento.getLuogo());
                dto.setDataEvento(evento.getDataEvento());
                dto.setAziendeInvitateId(evento.getInvitatiId());
                dto.setPartecipantiId(evento.getPartecipantiId());
                eventi.add(dto);
            }
        }
        return ResponseEntity.ok(eventi);
    }


    /**
     * metodo per la ricarica del saldo dell'acquirente.
     * l'acquirente che ha effettuato il login ricarica il proprio saldo
     * @param saldo importo da ricaricare
     * @return ResponseEntity<String> - Risposta HTTP con il messaggio di risultato della ricarica del saldo dell'acquirente.'
     */
    public ResponseEntity<String> ricaricaSaldo(double saldo) {
        Acquirente acquirente = securityService.getAcquirenteCorrente();
        if (saldo <= 0) {
            ResponseEntity.badRequest().body("Saldo inserito non valido");
        }
        acquirente.setSaldo(acquirente.getSaldo() + saldo);
        return ResponseEntity.ok().body("Saldo corrente: " + acquirente.getSaldo() + "€");

    }

}
