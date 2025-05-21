package it.unicam.cs.ids25.model.Service;

import it.unicam.cs.ids25.model.Dto.AziendaDTO;;
import it.unicam.cs.ids25.model.Repository.AziendaRepository;
import it.unicam.cs.ids25.model.Utenti.Azienda;
import it.unicam.cs.ids25.model.Utenti.Distributore;
import it.unicam.cs.ids25.model.Utenti.Produttore;
import it.unicam.cs.ids25.model.Utenti.Trasformatore;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AziendaService {
    private final AziendaRepository repo;

    public AziendaService(AziendaRepository repo) {
        this.repo = repo;
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

    public void elimina(Long id) {
        repo.deleteById(id);
    }
}