package it.unicam.cs.ids25.model;

import it.unicam.cs.ids25.model.observer.Observer;
import it.unicam.cs.ids25.model.observer.Subject;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import it.unicam.cs.ids25.model.Utenti.Acquirente;

import java.util.ArrayList;
import java.util.HashMap;

public class Ordine implements Subject {
    private static long contatoreID = 0;
    private long id;
    private Acquirente acquirente;
    private HashMap<Prodotto, Integer> prodottiAcquistati;
    private String indirizzo;
    private ArrayList<Observer> observer = new ArrayList<>();

    public Ordine(Acquirente acquirente, HashMap<Prodotto, Integer> prodottiAcquistati, String indirizzo) {
        this.id = ++contatoreID;
        this.acquirente = acquirente;
        this.prodottiAcquistati = prodottiAcquistati;
        this.indirizzo = indirizzo;
    }

    public HashMap<Prodotto, Integer> getProdottiAcquistati() {
        return prodottiAcquistati;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public Acquirente getAcquirente() {
        return acquirente;
    }

    public void aziendeOrdine(HashMap<Prodotto, Integer> prodottiAcquistati){
        for(Prodotto p : prodottiAcquistati.keySet()){
            observer.add(p.getAzienda());
        }
    }

    @Override
    public void attach(Observer o) {
        observer.add(o);
    }

    @Override
    public void detach(Observer o) {
        observer.remove(o);
    }

    @Override
    public void notifyAziende() {
        aziendeOrdine(prodottiAcquistati);

        for(Observer o : observer) {
            o.update(this);
        }
    }
}
