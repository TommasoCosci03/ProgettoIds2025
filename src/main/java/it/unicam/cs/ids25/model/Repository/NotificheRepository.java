package it.unicam.cs.ids25.model.Repository;

import it.unicam.cs.ids25.model.Acquisto.Notifica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository dell'entità {@link Notifica}
 */
public interface NotificheRepository extends JpaRepository<Notifica, Long> {
    /**
     * Metodo che cerca le aziende in una lista di notifiche
     * @param id
     * @return List<Notifica>
     */
    List<Notifica> findByAzienda_Id(Long id);

    /**
     * Metodo che cerca tutti i prodotti, che sono stati acquistati e notificati alle aziende, in una lista di notifiche
     * @param id
     * @return List<Notifica>
     */
    List<Notifica> findAllByProdotto_Id(Long id);
}