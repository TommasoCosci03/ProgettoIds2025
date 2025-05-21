package it.unicam.cs.ids25.model.Repository;

import it.unicam.cs.ids25.model.Utenti.Azienda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AziendaRepository extends JpaRepository<Azienda, Long> {
}