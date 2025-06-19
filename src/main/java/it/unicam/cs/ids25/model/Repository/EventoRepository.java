package it.unicam.cs.ids25.model.Repository;

import it.unicam.cs.ids25.model.Evento;
import it.unicam.cs.ids25.model.Utenti.Acquirente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EventoRepository extends JpaRepository<Evento, Long> {


    @Modifying
    @Query(value = "DELETE FROM evento_invitati WHERE azienda_id = :idAzienda", nativeQuery = true)
    int eliminaAziendaInvitata(@Param("idAzienda") Long idAzienda);

}
