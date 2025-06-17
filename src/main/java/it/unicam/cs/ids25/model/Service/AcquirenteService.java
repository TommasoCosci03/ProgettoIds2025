package it.unicam.cs.ids25.model.Service;

import it.unicam.cs.ids25.model.Dto.AcquirenteDTO;
import it.unicam.cs.ids25.model.Repository.AcquirenteRepository;
import it.unicam.cs.ids25.model.Utenti.Acquirente;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AcquirenteService {
    private final AcquirenteRepository repo;

    public AcquirenteService(AcquirenteRepository repo) {
        this.repo = repo;
    }

    public ResponseEntity<String> creaAcquirente(AcquirenteDTO dto){
        Acquirente acquirente = new Acquirente(dto.getNome());
        repo.save(acquirente);
        return ResponseEntity.ok(acquirente.getNome() + " creato con successo");
    }

    public Acquirente trova(Long id){
        return repo.findById(id).orElse(null);
    }

    public ResponseEntity<String> elimina(Long id){
        if(!repo.existsById(id)){
            return ResponseEntity.status(404).body("acquirente non trovato");
        }
        repo.deleteById(id);
        return ResponseEntity.ok("acquirente eliminato");
    }
}
