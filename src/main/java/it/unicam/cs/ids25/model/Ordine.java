package it.unicam.cs.ids25.model;

import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import it.unicam.cs.ids25.model.Utenti.Acquirente;
import it.unicam.cs.ids25.model.observer.Observer;
import it.unicam.cs.ids25.model.observer.Subject;

import java.util.ArrayList;

public class Ordine implements Subject {
    private static long contatoreID = 0;
    private long id;
    private Acquirente acquirente;
    private ArrayList<Prodotto> prodottiAcquistati;
    private String indirizzo;
    private ArrayList<Observer> observer = new ArrayList<>();

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

    //Metodi per Observer
    @Override
    public void attach(Observer o) {
        observer.add(o);
    }

    @Override
    public void detach(Observer o) {
        observer.remove(o);
    }

    @Override
    public void notifyAll(Ordine ordine) {
        for (Observer o : observer)
            o.update(ordine);

        observer.clear();
    }
}