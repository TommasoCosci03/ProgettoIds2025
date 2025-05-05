package progettoIDS25.src.main.java.it.unicam.cs.ids25.model;

import java.util.ArrayList;

public abstract class Azienda {
    private static long contatoreID = 0;
    private long id;
    private String nome;
    private String sede;
    private ArrayList<Prodotto> prodottiCaricati;



    public Azienda(String nome, String sede) {
        this.id = ++contatoreID;
        this.nome = nome;
        this.sede = sede;
        this.prodottiCaricati = new ArrayList<>();
    }

    public long getId() {
        return id;
}

    public String getNome() {
        return nome;
    }

    public String getSede() {
        return sede;
    }

    public ArrayList<Prodotto> getProdottiCaricati() {
        return prodottiCaricati;
    }

    public void creaProdotto(){}

    public void vediProdottiCaricati(){}

    public void eliminaProdotto(Prodotto prodotto){
        prodottiCaricati.remove(prodotto);
    }
}
