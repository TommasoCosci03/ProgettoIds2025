package it.unicam.cs.ids25.model.Repository;

import it.unicam.cs.ids25.model.Utenti.Acquirente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository dell'entità {@link Acquirente}
 */
public interface AcquirenteRepository extends JpaRepository<Acquirente, Long> {
    boolean existsByUsername(String username);
}
