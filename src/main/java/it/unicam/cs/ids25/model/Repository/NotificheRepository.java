package it.unicam.cs.ids25.model.Repository;

import it.unicam.cs.ids25.model.Acquisto.Notifica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificheRepository extends JpaRepository<Notifica, Long> {
    List<Notifica> findByAzienda_Id(Long id);

    List<Notifica> findAllByProdotto_Id(Long id);
}