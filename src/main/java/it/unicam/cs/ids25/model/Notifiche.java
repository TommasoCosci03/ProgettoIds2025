package it.unicam.cs.ids25.model;

import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import it.unicam.cs.ids25.model.Utenti.Acquirente;
import it.unicam.cs.ids25.model.Utenti.Azienda;
import jakarta.persistence.*;

@Entity
public class Notifiche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Prodotto prodotto;

    @ManyToOne
    private Azienda azienda;
    @ManyToOne
    private Acquirente acquirente;
    private String indirizzo;
    private int quantita;


    public Notifiche() {
    }

    public Long getId() {
        return id;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public Azienda getAzienda() {
        return azienda;
    }

    public void setAzienda(Azienda azienda) {
        this.azienda = azienda;
    }

    public void setAcquirente(Acquirente acquirente) {
        this.acquirente = acquirente;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    @Override
    public String toString() {

        return "---------Ordine----------\n" +
                //"id=" + id +
                " prodotto=" + (prodotto != null ? prodotto.getNome() : "null") +
                //", azienda=" + (azienda != null ? azienda.getNome() : "null") +
                ", \nacquirente=" + (acquirente != null ? acquirente.getNome() : "null") +
                ", \nindirizzo='" + indirizzo +
                ", \nquantita=" + quantita + "\n\n\n";
    }
}