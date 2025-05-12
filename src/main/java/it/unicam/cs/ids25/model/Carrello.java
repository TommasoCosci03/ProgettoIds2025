package it.unicam.cs.ids25.model;

import it.unicam.cs.ids25.model.Prodotti.Prodotto;

import java.util.ArrayList;

public class Carrello {
    private ArrayList<Prodotto> prodottiDaAcquistare;

    public Carrello() {
        this.prodottiDaAcquistare = new ArrayList<>();
    }

    public ArrayList<Prodotto> getProdottiDaAcquistare() {
        return prodottiDaAcquistare;
    }

    public double prezzoTotale(){
        return 0;
    }
}
