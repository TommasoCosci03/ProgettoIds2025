package it.unicam.cs.ids25.model.Utenti;

import it.unicam.cs.ids25.model.Carrello;
import it.unicam.cs.ids25.model.Marketplace;
import it.unicam.cs.ids25.model.Ordine;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;

public class Acquirente {
    private static long contatoreId = 0;
    private long id;
    private String nome;
    private Carrello carrello;
    private Marketplace marketplace = Marketplace.getIstanzaMarketplace();

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

    public void aggiungiAlCarrello(Prodotto prodotto, int quantita){
        if(marketplace.getProdottiInVendita().contains(prodotto) && marketplace.controllaQuantita(prodotto, quantita))
            carrello.getProdottiDaAcquistare().put(prodotto, quantita);
        else
            throw new IllegalArgumentException("Prodotto non disponibile nel marketplace");
    }

    public void togliDalCarrello(Prodotto prodotto){
        carrello.getProdottiDaAcquistare().remove(prodotto);
    }

    public void cancellaCarrello(){
        carrello.getProdottiDaAcquistare().clear();
    }

    public void acquista(){
        Ordine ordine = new Ordine(this, carrello.getProdottiDaAcquistare(), "via marche");
        ordine.notifyAziende();
        marketplace.aggiornaQuantita(carrello);
        carrello.getProdottiDaAcquistare().clear();
    }
}
