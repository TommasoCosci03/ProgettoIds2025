package progettoIDS25.src.main.java.it.unicam.cs.ids25.model;

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
