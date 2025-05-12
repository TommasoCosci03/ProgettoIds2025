package it.unicam.cs.ids25.model;

import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import it.unicam.cs.ids25.model.Utenti.Acquirente;

import java.util.ArrayList;

public class Ordine {
    private static long contatoreID = 0;
    private long id;
    private Acquirente acquirente;
    private ArrayList<Prodotto> prodottiAcquistati;
    private String indirizzo;

    public Ordine(Acquirente acquirente, ArrayList<Prodotto> prodottiAcquistati, String indirizzo) {
        this.id = ++contatoreID;
        this.acquirente = acquirente;
        this.prodottiAcquistati = prodottiAcquistati;
        this.indirizzo = indirizzo;
    }

    public ArrayList<Prodotto> getProdottiAcquistati() {
        return prodottiAcquistati;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public Acquirente getAcquirente() {
        return acquirente;
    }
}
