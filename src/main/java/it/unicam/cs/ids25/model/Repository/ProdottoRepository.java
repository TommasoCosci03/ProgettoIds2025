package it.unicam.cs.ids25.model.Repository;

import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {

    ArrayList<Prodotto> findAllByApprovatoIs(boolean approvato);

    @Query(value = "SELECT COUNT(*) FROM pacchetto_prodotto  WHERE prodotto_id = :idProdotto", nativeQuery = true)
    long countPacchettiConProdotto(@Param("idProdotto") Long idProdotto);
}
