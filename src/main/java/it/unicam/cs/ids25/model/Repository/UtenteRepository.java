package it.unicam.cs.ids25.model.Repository;

import it.unicam.cs.ids25.model.Autenticazione.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * Repository dell'entità {@link Utente}
 */
public interface UtenteRepository extends JpaRepository<Utente, Long> {
    /**
     * Metodo per trovare un utente passando il proprio userneme
     * @param username
     */
    Utente findByUsername(String username);

    /**
     * Query per verificare se la tabella curatore è vuota o meno
     * @return 1 se il curatore esiste gia altrimenti ritorna 0
     */
    @Query(value = "SELECT COUNT(*) FROM curatore", nativeQuery = true)
    long isEmpty();
}
