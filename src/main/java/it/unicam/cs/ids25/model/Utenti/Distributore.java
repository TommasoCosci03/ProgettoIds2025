package it.unicam.cs.ids25.model.Utenti;

import it.unicam.cs.ids25.model.Prodotti.Categoria;
import it.unicam.cs.ids25.model.Prodotti.Certificazioni;
import it.unicam.cs.ids25.model.Marketplace;
import it.unicam.cs.ids25.model.Ordine;
import it.unicam.cs.ids25.model.Prodotti.BuilderPacchetti;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;

import java.util.ArrayList;


public class Distributore extends Azienda {
    Marketplace marketplace = Marketplace.getIstanzaMarketplace();
    ArrayList<Prodotto> prodotti;
    public Distributore(String nome, String sede) {super(nome, sede);
    }

    @Override
    public Prodotto creaProdottoAzienda(String nome, String descrizione, double prezzo, int quantita, Categoria categoria,  ArrayList<Certificazioni> certificazioni) {
        if (prodotti.size() < 2) { throw new IllegalStateException("il pacchetto deve essere composto da almeno due prodotti");}
        BuilderPacchetti builderPacchetti = new BuilderPacchetti();
        builderPacchetti.setPrezzo(prezzo);
        builderPacchetti.setQuantita(quantita);
        builderPacchetti.setCategoria(categoria);
        builderPacchetti.setNome(nome);
        builderPacchetti.setDescrizione(descrizione);
        builderPacchetti.setAzienda(this);
        builderPacchetti.setCertificazioni(certificazioni);
        builderPacchetti.setProdotti(prodotti);
        prodotti = null;
        return builderPacchetti.buildPacchetto();

    }

    public void setProdotti(ArrayList<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    public Prodotto getProdottoMarketplace(int id) {
        return marketplace.getProdotto(id);
    }

    @Override
    public void update(Ordine ordine) {

    }
}
