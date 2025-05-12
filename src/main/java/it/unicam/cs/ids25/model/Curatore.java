package it.unicam.cs.ids25.model;

import java.util.ArrayList;

public class Curatore {
    private String nome;
    private ArrayList<Prodotto> richieste;

    public void Curatore(String nome){
        this.nome = nome;
        this.richieste = new ArrayList<>();
    }

    public void approvaProdotto(Prodotto prodotto){}

    public ArrayList<Prodotto> getRichieste() {
        return richieste;
    }
}
