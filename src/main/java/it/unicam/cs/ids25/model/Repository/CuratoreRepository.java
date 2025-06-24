package it.unicam.cs.ids25.model.Repository;

import it.unicam.cs.ids25.model.Utenti.Curatore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * Repository dell'entità {@link Curatore}
 */
public interface CuratoreRepository extends JpaRepository<Curatore, Long> {
    /**
     * Metodo che passato l'username del Curatore verifica se esiste gia o deve crearne uno nuovo
     * @param username
     * @return se il curatore non esisto lo crea
     * altrimenti ritorna ResponseEntity.Status(500).body("Curatore gia presente")
     */
    Curatore findByUsername(String username);

    /**
     * Query per verificare se la tabella curatore è vuota o meno
     * @return 1 se il curatore esiste gia altrimenti ritorna 0
     */
    @Query(value = "SELECT COUNT(*) FROM curatore", nativeQuery = true)
    long isEmpty();
}
