package it.unicam.cs.ids25.model.Repository;

import it.unicam.cs.ids25.model.Acquisto.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository dell'entità {@link Ordine}
 */
public interface OrdineRepository extends JpaRepository<Ordine, Long> {
}
