package it.unicam.cs.ids25.model.Acquisto;

import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import it.unicam.cs.ids25.model.Utenti.Acquirente;
import it.unicam.cs.ids25.model.Utenti.Azienda;
import jakarta.persistence.*;

/**
 * La classe notifica rappresenta una notifica generata da un {@link Acquirente} per un {@link Prodotto}
 * acquistato da un {@link Acquirente} all'interno di un {@link Carrello}.
 */
@Entity
public class Notifica {

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
    private Long idOrdine;


    /**
     * Costruttore vuoto per Springboot.
     */
    public Notifica() {
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

    public Long getIdOrdine() {return idOrdine;}

    public void setIdOrdine(Long idOrdine) {this.idOrdine = idOrdine;}

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