package it.unicam.cs.ids25.model.Repository;

import it.unicam.cs.ids25.model.Notifiche;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificheRepository extends JpaRepository<Notifiche, Long> {
}