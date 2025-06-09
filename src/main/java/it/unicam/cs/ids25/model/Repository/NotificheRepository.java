package it.unicam.cs.ids25.model.Repository;

import it.unicam.cs.ids25.model.Notifiche;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificheRepository extends JpaRepository<Notifiche, Long> {
    List<Notifiche> findByAzienda_Id(Long id);

    boolean findAllByProdotto_Id(Long id);
}