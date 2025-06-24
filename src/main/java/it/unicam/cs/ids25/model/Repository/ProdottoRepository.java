package it.unicam.cs.ids25.model.Repository;

import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository dell'entità {@link Prodotto}
 */
public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {

    /**
     * Metodo che trova tutti i prodotti approvati
     * @param approvato
     * @return ArrayList<Prodotto>
     */
    ArrayList<Prodotto> findAllByApprovatoIs(boolean approvato);

    /**
     * Query per la conta di quante volte un prodotto passato tramite id è presente nella tabella pacchetto_prodotto
     * @param idProdotto
     * @return un valore Long di quante volte il prodotto è presente all'interno dei pacchetti
     */
    @Query(value = "SELECT COUNT(*) FROM pacchetto_prodotto  WHERE prodotto_id = :idProdotto", nativeQuery = true)
    long countPacchettiConProdotto(@Param("idProdotto") Long idProdotto);
}
