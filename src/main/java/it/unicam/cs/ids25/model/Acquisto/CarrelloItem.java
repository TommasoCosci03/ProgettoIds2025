package it.unicam.cs.ids25.model.Acquisto;

import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import it.unicam.cs.ids25.model.Utenti.Acquirente;
import jakarta.persistence.*;

/**
 * La classe CarrelloItem rappresenta un prodotto presente nel carrello di un {@link Acquirente}.
 */
@Entity
public class CarrelloItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Prodotto prodotto;
    private int quantita;

    /**
     * Aggiunge un prodotto al carrello specificando quantità e prodotto.
     *
     * @param prodotto
     * @param quantita
     */
    public CarrelloItem(Prodotto prodotto, int quantita) {
        this.prodotto = prodotto;
        this.quantita = quantita;
    }

    /**
     * Costruttore vuoto per Springboot.
     */
    public CarrelloItem() {

    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public int getQuantita() {
        return quantita;
    }


}
