package it.unicam.cs.ids25.model.Repository;

import it.unicam.cs.ids25.model.Utenti.Gestore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GestoreRepository extends JpaRepository<Gestore, Long> {
}
