package it.unicam.cs.ids25.model.Repository;

import it.unicam.cs.ids25.model.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdineRepository extends JpaRepository<Ordine, Long> {
}
