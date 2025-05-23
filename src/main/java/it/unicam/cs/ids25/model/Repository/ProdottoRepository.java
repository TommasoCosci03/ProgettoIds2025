package it.unicam.cs.ids25.model.Repository;

import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {

}
