package it.unicam.cs.ids25.model.Repository;

import it.unicam.cs.ids25.model.Utenti.Curatore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CuratoreRepository extends JpaRepository<Curatore, Long> {
    Curatore findByUsername(String username);

    @Query(value = "SELECT COUNT(*) FROM curatore", nativeQuery = true)
    long isEmpty();
}
