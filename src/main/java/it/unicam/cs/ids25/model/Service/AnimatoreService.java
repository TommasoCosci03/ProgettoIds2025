package it.unicam.cs.ids25.model.Service;

import it.unicam.cs.ids25.model.Dto.AnimatoreDTO;
import it.unicam.cs.ids25.model.Dto.EventoDTO;
import it.unicam.cs.ids25.model.Evento;
import it.unicam.cs.ids25.model.Repository.AnimatoreRepository;
import it.unicam.cs.ids25.model.Repository.AziendaRepository;
import it.unicam.cs.ids25.model.Repository.EventoRepository;
import it.unicam.cs.ids25.model.Utenti.Animatore;
import it.unicam.cs.ids25.model.Utenti.Azienda;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
// IMPLEMENTARE LA PERTE DEGLI EVENTI COLLEGATA AL LOGIN
public class AnimatoreService {
    private final AnimatoreRepository animatoreRepository;
    private final EventoRepository eventoRepository;
    private final AziendaRepository aziendaRepository;

    public AnimatoreService(AnimatoreRepository repo, EventoRepository eventoRepository, AziendaRepository aziendaRepository) {
        this.animatoreRepository = repo;
        this.eventoRepository = eventoRepository;
        this.aziendaRepository = aziendaRepository;
    }

    public ResponseEntity<String> creaAnimatore(AnimatoreDTO dto) {
        Animatore animatore = new Animatore(dto.getNome());
        animatoreRepository.save(animatore);
        return ResponseEntity.ok( animatore.getNome() + " animatore creato");
    }


    public ResponseEntity<String> creaEvento(EventoDTO dto) {
        // Recupera l'ID dell'animatore dal corpo JSON
        Long animatoreId = dto.getIdAnimatore();
        if (animatoreId == null || !(animatoreRepository.existsById(animatoreId))) {
            return ResponseEntity.status(404).body("Id animatore non trovato");
        }

        for(Long id : dto.getAziendeInvitateId()) {
            if(!aziendaRepository.existsById(id)) {
                return ResponseEntity.status(404).body("Azienda/e invitate non trovate");
            }
        }

        Animatore animatore = animatoreRepository.findById(animatoreId).get();
        // Creazione del nuovo evento

        Evento evento = animatore.creaEvento(
                dto.getNome(),
                dto.getDescrizione(),
                dto.getLuogo(),
                dto.getDataEvento(),
                getInvitati(dto)
        );

        eventoRepository.save(evento);
        return ResponseEntity.status(200).body(evento.getNome() + " creato con successo");
    }

    private List<Azienda> getInvitati(EventoDTO dto) {
        List<Azienda> AziendeInvitate = new ArrayList<>();

        for(Long id : dto.getAziendeInvitateId()){
            Azienda azienda = aziendaRepository.findById(id).get();
            AziendeInvitate.add(azienda);
        }

        return AziendeInvitate;
    }


    public Animatore trova(Long id) {
        return animatoreRepository.findById(id).orElse(null);
    }

    public List<EventoDTO> trovaEventi() {
        List<EventoDTO> eventi = new ArrayList<>();
        for (Animatore animatore : animatoreRepository.findAll()) {
            for (Evento evento : animatore.getEventi()) {
                EventoDTO dto = new EventoDTO();
                dto.setIdAnimatore(animatore.getId());
                dto.setNome(evento.getNome());
                dto.setDescrizione(evento.getDescrizione());
                dto.setLuogo(evento.getLuogo());
                dto.setDataEvento(evento.getDataEvento());
                dto.setAziendeInvitateId(evento.getInvitatiId());
                dto.setPartecipantiId(evento.getPartecipantiId());
                eventi.add(dto);
            }
        }
        return eventi;
    }

    public ResponseEntity<String> elimina(Long id){
        if(!animatoreRepository.existsById(id)) {
          return ResponseEntity.status(404).body("Id animatore non trovato");
        }
        animatoreRepository.deleteById(id);
        return ResponseEntity.ok("Animatore eliminato con successo");
    }

    public ResponseEntity<String> invitaAzienda(Long idEvento, Long idAzienda) {
        if(!eventoRepository.existsById(idEvento)) {
            return ResponseEntity.status(404).body("Evento non trovato");
        }

        if(!aziendaRepository.existsById(idAzienda)) {
            return ResponseEntity.status(404).body("Azienda non trovata");
        }

        Evento evento = eventoRepository.findById(idEvento).get();
        Azienda azienda = aziendaRepository.findById(idAzienda).get();
        evento.getInvitati().add(azienda);

        return ResponseEntity.ok("Azienda " + azienda.getNome() + " invitata con successo all'evento " + evento.getNome());
    }
}
