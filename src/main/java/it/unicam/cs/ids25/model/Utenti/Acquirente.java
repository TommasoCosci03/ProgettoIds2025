package it.unicam.cs.ids25.model.Utenti;

import it.unicam.cs.ids25.model.Carrello;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;

public class Acquirente {
    private static long contatoreId = 0;
    private long id;
    private String nome;
    private Carrello carrello;

    public Acquirente(String nome) {
        this.id = ++contatoreId;
        this.nome = nome;
        this.carrello = new Carrello();
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Carrello getCarrello() {
        return carrello;
    }

    public void aggiungiAlCarrello(Prodotto prodotto){
        carrello.getProdottiDaAcquistare().add(prodotto);
    }

    public void togliDalCarrello(Prodotto prodotto){
        carrello.getProdottiDaAcquistare().remove(prodotto);
    }

    public void cancellaCarrello(){
        carrello.getProdottiDaAcquistare().removeAll(carrello.getProdottiDaAcquistare());
    }
}
