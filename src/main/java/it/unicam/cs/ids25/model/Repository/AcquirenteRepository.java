package it.unicam.cs.ids25.model.Repository;

import it.unicam.cs.ids25.model.Utenti.Acquirente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcquirenteRepository extends JpaRepository<Acquirente, Long> {
    boolean existsByUsername(String username);
}
