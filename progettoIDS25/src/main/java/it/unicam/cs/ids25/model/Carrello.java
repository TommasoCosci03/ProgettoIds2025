package progettoIDS25.src.main.java.it.unicam.cs.ids25.model;

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
