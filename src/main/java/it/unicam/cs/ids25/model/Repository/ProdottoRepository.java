package it.unicam.cs.ids25.model.Repository;

import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {

    ArrayList<Prodotto> findAllByApprovatoIs(boolean approvato);

    //List<Prodotto> findAllByNomeContaining(String nome);


   // List<Prodotto> findAllByTipo(String tipoProdotto);
}
