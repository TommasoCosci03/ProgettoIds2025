package it.unicam.cs.ids25.model.Service;

import it.unicam.cs.ids25.model.Dto.AcquirenteDTO;
import it.unicam.cs.ids25.model.Repository.AcquirenteRepository;
import it.unicam.cs.ids25.model.Utenti.Acquirente;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AcquirenteService {
    private final AcquirenteRepository repo;

    public AcquirenteService(AcquirenteRepository repo) {
        this.repo = repo;
    }

    public void creaAcquirente(AcquirenteDTO dto){
        Acquirente acquirente = new Acquirente(dto.getNome());
        repo.save(acquirente);
    }

    public Acquirente trova(Long id){
        return repo.findById(id).orElse(null);
    }

    public void elimina(Long id){
        repo.deleteById(id);
    }
}
