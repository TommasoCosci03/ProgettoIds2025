package it.unicam.cs.ids25.model.Utenti;


import it.unicam.cs.ids25.model.Prodotti.Categoria;
import it.unicam.cs.ids25.model.Prodotti.Certificazioni;
import it.unicam.cs.ids25.model.Ordine;
import it.unicam.cs.ids25.model.Prodotti.BuilderProdottoSingolo;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;


@Entity
@DiscriminatorValue("produttore")
public class Produttore extends Azienda {

    public Produttore(String nome, String sede) {
        super(nome, sede);
    }

    protected Produttore() {super();}

    @Override
    public Prodotto creaProdottoAzienda(String nome, String descrizione, double prezzo, int quantita,
                                        Categoria categoria,  List<Certificazioni> certificazioni) {
        BuilderProdottoSingolo prodottobuilder = new BuilderProdottoSingolo();
        prodottobuilder.setNome(nome);
        prodottobuilder.setDescrizione(descrizione);
        prodottobuilder.setPrezzo(prezzo);
        prodottobuilder.setAzienda(this);
        prodottobuilder.setQuantita(quantita);
        prodottobuilder.setCategoria(categoria);
        prodottobuilder.setCertificazioni(certificazioni);
        return prodottobuilder.buildProdottoSingolo();
    }



    @Override
    public void vediProdottiCaricati() {
        System.out.println("--- Lista di prodotti caricati da " + this.getNome() + " ---\n" + this.getProdottiCaricati());
    }

    @Override
    public void update(Ordine ordine) {
        System.out.println("--- L'azienda " + this.getNome()+  " ha ricevuto l'ordine ricevuto da "
                + ordine.getAcquirente().getNome() + " ---");
    }
}
