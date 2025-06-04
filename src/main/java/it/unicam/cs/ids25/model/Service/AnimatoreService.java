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
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class AnimatoreService {
   private final AnimatoreRepository repo;
   private final EventoRepository eventoRepository;
   private final AziendaRepository aziendaRepository;

   public AnimatoreService(AnimatoreRepository repo, EventoRepository eventoRepository, AziendaRepository aziendaRepository) {
       this.repo = repo;
       this.eventoRepository = eventoRepository;
       this.aziendaRepository = aziendaRepository;
   }
    public void creaAnimatore(AnimatoreDTO dto){
       Animatore animatore = new Animatore(dto.getNome());
       repo.save(animatore);
    }


    public void creaEvento(EventoDTO dto){
        // Recupera l'ID dell'animatore dal corpo JSON
        Long animatoreId = dto.getIdAnimatore();
        if (animatoreId == null) {
            throw new IllegalArgumentException("L'ID dell'animatore è obbligatorio.");
        }

        // Verifica che l'animatore esista
        Animatore animatore = repo.findById(animatoreId)
                .orElseThrow(() -> new IllegalArgumentException("Animatore non trovato con ID: " + animatoreId));

        // Creazione del nuovo evento
        Evento evento = animatore.creaEvento(
                dto.getNome(),
                dto.getDescrizione(),
                dto.getLuogo(),
                dto.getDataEvento(),
                aziendaRepository.findAllById(dto.getAziendeInvitateId())
        );

        eventoRepository.save(evento);
    }


    public Animatore trova(Long id){
        return repo.findById(id).orElse(null);
    }

    public List<Evento> trovaEventi(){
       return eventoRepository.findAll();}

    public void elimina(Long id){
        repo.deleteById(id);
    }
}
