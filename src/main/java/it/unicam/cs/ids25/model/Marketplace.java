package it.unicam.cs.ids25.model;

import it.unicam.cs.ids25.model.Prodotti.Prodotto;

import java.util.ArrayList;

public class Marketplace {
    private ArrayList<Prodotto> prodottiInVendita;

    public Marketplace(){
        prodottiInVendita = new ArrayList<>();
    }

    public ArrayList<Prodotto> getProdottiInVendita() {
        return prodottiInVendita;
    }
}
