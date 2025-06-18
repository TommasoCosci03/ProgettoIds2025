package it.unicam.cs.ids25.model.Repository;

import it.unicam.cs.ids25.model.Utenti.Azienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AziendaRepository extends JpaRepository<Azienda, Long> {

    @Query(value = "SELECT COUNT(*) FROM carrello_item  WHERE prodotto_id = :idProdotto", nativeQuery = true)
    long countProdottiNelCarrello(@Param("idProdotto") Long idProdotto);


    Optional<Azienda> findByUsername(String username);

    boolean existsByUsername(String username);


}
