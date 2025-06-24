package it.unicam.cs.ids25.model.Repository;

import it.unicam.cs.ids25.model.Utenti.Animatore;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository dell'entità {@link Animatore}
 */
public interface AnimatoreRepository extends JpaRepository<Animatore, Long> {
    boolean existsByUsername(String username);
}
