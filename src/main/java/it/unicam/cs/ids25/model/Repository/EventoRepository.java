package it.unicam.cs.ids25.model.Repository;

import it.unicam.cs.ids25.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository dell'entità {@link Evento}
 */
public interface EventoRepository extends JpaRepository<Evento, Long> {


    /**
     * Query per l'eliminazione delle aziende, tramite {@param idAzienda}, nella tabella evento_invitati
     * @param idAzienda
     */
    @Modifying
    @Query(value = "DELETE FROM evento_invitati WHERE azienda_id = :idAzienda", nativeQuery = true)
    int eliminaAziendaInvitata(@Param("idAzienda") Long idAzienda);

}
