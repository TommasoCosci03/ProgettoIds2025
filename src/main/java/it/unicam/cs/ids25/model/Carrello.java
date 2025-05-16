package it.unicam.cs.ids25.model;

import it.unicam.cs.ids25.model.Prodotti.Prodotto;

import java.util.HashMap;

public class Carrello {
    private HashMap<Prodotto, Integer> prodottiDaAcquistare;

    public Carrello() {
        this.prodottiDaAcquistare = new HashMap<>();
    }

    public HashMap<Prodotto, Integer> getProdottiDaAcquistare() {
        return prodottiDaAcquistare;
    }

    public double prezzoTotale(){
        double somma = 0;
        for(Prodotto p : prodottiDaAcquistare.keySet()){
            somma += p.getPrezzo() * prodottiDaAcquistare.get(p);
        }
        return somma;
    }
}
