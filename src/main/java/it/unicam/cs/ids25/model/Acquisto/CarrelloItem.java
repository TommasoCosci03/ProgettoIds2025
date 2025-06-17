package it.unicam.cs.ids25.model.Acquisto;

import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import jakarta.persistence.*;

@Entity
public class CarrelloItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Prodotto prodotto;
    private int quantita;

    public CarrelloItem(Prodotto prodotto, int quantita) {
        this.prodotto = prodotto;
        this.quantita = quantita;
    }

    public CarrelloItem() {

    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public int getQuantita() {
        return quantita;
    }


}
