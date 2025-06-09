package it.unicam.cs.ids25.model.Service;

import it.unicam.cs.ids25.model.Dto.AziendaDTO;;
import it.unicam.cs.ids25.model.Notifiche;
import it.unicam.cs.ids25.model.Repository.AziendaRepository;
import it.unicam.cs.ids25.model.Repository.NotificheRepository;
import it.unicam.cs.ids25.model.Utenti.Azienda;
import it.unicam.cs.ids25.model.Utenti.Distributore;
import it.unicam.cs.ids25.model.Utenti.Produttore;
import it.unicam.cs.ids25.model.Utenti.Trasformatore;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AziendaService {
    private final AziendaRepository repo;
    private final NotificheRepository notificheRepo;

    public AziendaService(AziendaRepository repo, NotificheRepository notificheRepo) {
        this.repo = repo;
        this.notificheRepo = notificheRepo;

    }

    public Azienda crea(AziendaDTO dto) {
        Azienda azienda = null;

        if (dto.getTipoAzienda().equals("produttore")) {
            azienda = new Produttore(dto.getNome(), dto.getSede());
        }
        else if (dto.getTipoAzienda().equals("trasformatore")){
            azienda = new Trasformatore(dto.getNome(), dto.getSede());

        }
        else if (dto.getTipoAzienda().equals("distributore")){
            azienda = new Distributore(dto.getNome(), dto.getSede());
        }

        return repo.save(azienda);
    }

    public List<Azienda> trovaTutte() {
        return repo.findAll();
    }

    public Azienda trova(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void elimina(Long id) {
        repo.deleteById(id);
    }

    public ResponseEntity<StringBuilder> notificheById(Long id) {
        StringBuilder notifiche = new StringBuilder();
        Azienda azienda = repo.findById(id).orElse(null);
        if (azienda != null) {
            //notifiche.append(notificheRepo.findAllById(id));
            return ResponseEntity.ok().body(notifiche);
        }
        return ResponseEntity.notFound().build();
    }
}