package it.unicam.cs.ids25.model.Repository;

import it.unicam.cs.ids25.model.Utenti.Azienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Repository dell'entità {@link Azienda}
 */
public interface AziendaRepository extends JpaRepository<Azienda, Long> {

    /**
     * Query per la conta dei prodotti caricati da parte di un azienda nella tabella carrello_item del database
     * @param idProdotto
     * @return un valore Long del numero di prodotti all'interno di uno o piu carrelli
     */
    @Query(value = "SELECT COUNT(*) FROM carrello_item  WHERE prodotto_id = :idProdotto", nativeQuery = true)
    long countProdottiNelCarrello(@Param("idProdotto") Long idProdotto);

    /**
     * Metodo per verificare, passato un username, se esiste una determinata azienda
     * @param username
     * @return un valore boolean
     */
    boolean existsByUsername(String username);


}
