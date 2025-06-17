package it.unicam.cs.ids25.model.Service;

import it.unicam.cs.ids25.model.Dto.AcquirenteDTO;
import it.unicam.cs.ids25.model.Dto.EventoDTO;
import it.unicam.cs.ids25.model.Evento;
import it.unicam.cs.ids25.model.Repository.AcquirenteRepository;
import it.unicam.cs.ids25.model.Repository.AnimatoreRepository;
import it.unicam.cs.ids25.model.Repository.EventoRepository;
import it.unicam.cs.ids25.model.Utenti.Acquirente;
import it.unicam.cs.ids25.model.Utenti.Animatore;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AcquirenteService {
    private final AcquirenteRepository repoAcquirente;
    private final EventoRepository eventoRepo;
    private final AnimatoreRepository animatoreRepository;

    public AcquirenteService(AcquirenteRepository repo, EventoRepository eventoRepo, AnimatoreRepository animatoreRepository) {
        this.repoAcquirente = repo;
        this.eventoRepo = eventoRepo;
        this.animatoreRepository = animatoreRepository;
    }

    public ResponseEntity<String> creaAcquirente(AcquirenteDTO dto){
        Acquirente acquirente = new Acquirente(dto.getNome());
        repoAcquirente.save(acquirente);
        return ResponseEntity.ok(acquirente.getNome() + " creato con successo");
    }

    public Acquirente trova(Long id){
        return repoAcquirente.findById(id).orElse(null);
    }

    public ResponseEntity<String> elimina(Long id){
        if(!repoAcquirente.existsById(id)){
            return ResponseEntity.status(404).body("acquirente non trovato");
        }
        repoAcquirente.deleteById(id);
        return ResponseEntity.ok("acquirente eliminato");
    }

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
