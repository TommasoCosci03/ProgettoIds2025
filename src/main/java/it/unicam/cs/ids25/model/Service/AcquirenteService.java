package it.unicam.cs.ids25.model.Service;

import it.unicam.cs.ids25.model.Autenticazione.CustomUserDetailsService;
import it.unicam.cs.ids25.model.Autenticazione.SecurityService;
import it.unicam.cs.ids25.model.Dto.AcquirenteDTO;
import it.unicam.cs.ids25.model.Dto.EventoDTO;
import it.unicam.cs.ids25.model.Evento;
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

@Service
@Transactional
public class AcquirenteService {
    private final AcquirenteRepository repoAcquirente;
    private final EventoRepository eventoRepo;
    private final AnimatoreRepository animatoreRepository;


    private final PasswordEncoder passwordEncoder;

    private final SecurityService securityService;
    private final CustomUserDetailsService customUserDetailsService;

    public AcquirenteService(AcquirenteRepository repo, EventoRepository eventoRepo, AnimatoreRepository animatoreRepository, PasswordEncoder passwordEncoder, SecurityService securityService, CustomUserDetailsService customUserDetailsService) {
        this.repoAcquirente = repo;
        this.eventoRepo = eventoRepo;
        this.animatoreRepository = animatoreRepository;
        this.passwordEncoder = passwordEncoder;
        this.securityService = securityService;
        this.customUserDetailsService = customUserDetailsService;
    }

    public ResponseEntity<String> creaAcquirente(AcquirenteDTO dto){

        if (dto.getUsername().isEmpty() || dto.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("username o Password non valido");
        }

        if (repoAcquirente.existsByUsername(dto.getUsername()))
        {return ResponseEntity.status(HttpStatus.CONFLICT).body("Username: " + dto.getUsername() + " già in uso");}

        Acquirente acquirente = new Acquirente(dto.getNome());
        acquirente.setUsername(dto.getUsername());

        acquirente.setPassword(passwordEncoder.encode(dto.getPassword()));
        repoAcquirente.save(acquirente);

        return ResponseEntity.ok(acquirente.getNome() + " creato con successo");

    }

    public Acquirente trova(Long id){
        return repoAcquirente.findById(id).orElse(null);
    }
//LO IMPLEMENTEREMO NEL PROSSIMO AGGIORNAMENTO DEL PROGRAMMA
//    public ResponseEntity<String> elimina(){
//        if(securityService.getAcquirenteCorrente() == null ){ return ResponseEntity.badRequest().body("l'utente autenticato non è un acquirente");}
//        if(!repoAcquirente.existsById(securityService.getAcquirenteCorrente().getId())){
//            return ResponseEntity.status(404).body("acquirente non trovato");
//        }
//        repoAcquirente.deleteById(securityService.getAcquirenteCorrente().getId());
//        return ResponseEntity.ok("acquirente eliminato");
//    }

//IMPLEMENTARE LA PERTE DEGLI EVENTI COLLEGATA AL LOGIN
    public ResponseEntity<String> prenotaEvento(Long idEvento, Long idAcquirente){
        if(!eventoRepo.existsById(idEvento)){
            return ResponseEntity.status(404).body("Evento non trovato");
        }

        if(!repoAcquirente.existsById(idAcquirente)){
            return ResponseEntity.status(404).body("Acquirente non trovato");
        }

        Evento evento = eventoRepo.findById(idEvento).get();
        Acquirente acquirente = repoAcquirente.findById(idAcquirente).get();

        // Crea un nuova lista con i nuovi partecipanti
        List<Acquirente> partecipanti = evento.getPartecipanti();
        partecipanti.add(acquirente);

        // Aggiorna la variabile evento con la nuova lista
        evento.setPartecipanti(partecipanti);

        // Salva l'evento nel database'
        eventoRepo.save(evento);

        return ResponseEntity.status(200).body("Prenotazione avvenuta con successo all'evento " + evento.getNome());
    }
    // IMPLEMENTARE LA PERTE DEGLI EVENTI COLLEGATA AL LOGIN
    public ResponseEntity<List<EventoDTO>> trovaEventi() {
        List<EventoDTO> eventi = new ArrayList<>();
        for (Animatore animatore : animatoreRepository.findAll()) {
            for (Evento evento : animatore.getEventi()) {
                EventoDTO dto = new EventoDTO();
                dto.setIdAnimatore(animatore.getId());
                dto.setNome(animatore.getNome());
                dto.setDescrizione(evento.getDescrizione());
                dto.setLuogo(evento.getLuogo());
                dto.setDataEvento(evento.getDataEvento());
                //dto.setAziendeInvitateId(evento.getInvitati());
                eventi.add(dto);
            }
        }
        return ResponseEntity.ok(eventi);
    }
}
