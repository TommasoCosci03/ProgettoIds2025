package it.unicam.cs.ids25.model.Utenti;


import it.unicam.cs.ids25.model.Prodotti.Categoria;
import it.unicam.cs.ids25.model.Prodotti.Certificazioni;
import it.unicam.cs.ids25.model.Ordine;
import it.unicam.cs.ids25.model.Prodotti.BuilderProdottoTrasformato;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

import java.util.ArrayList;

@Entity
@DiscriminatorValue("trasformatore")
public class Trasformatore extends Azienda {
    @Transient
    private String materiePrime;

    public Trasformatore(String nome, String sede) {
        super(nome, sede);
    }


    protected Trasformatore() {super();}


    @Override
    public Prodotto creaProdottoAzienda(String nome, String descrizione, double prezzo, int quantita,
                                        Categoria categoria, ArrayList<Certificazioni> certificazioni) {
        if (materiePrime == null) { throw new IllegalStateException("materie prime mancanti");}
        BuilderProdottoTrasformato builderProdottoTrasformato = new BuilderProdottoTrasformato();
        builderProdottoTrasformato.setNome(nome);
        builderProdottoTrasformato.setDescrizione(descrizione);
        builderProdottoTrasformato.setPrezzo(prezzo);
        builderProdottoTrasformato.setAzienda(this);
        builderProdottoTrasformato.setQuantita(quantita);
        builderProdottoTrasformato.setMateriaPrima(materiePrime);
        builderProdottoTrasformato.setCategoria(categoria);
        builderProdottoTrasformato.setCertificazioni(certificazioni);
        materiePrime = null;
        return builderProdottoTrasformato.buildProdottoTrasformato();
    }

    public void setMateriePrime(String materiePrime) {
        this.materiePrime = materiePrime;
    }

    @Override
    public void vediProdottiCaricati() {
        System.out.println("--- Lista di prodotti caricati da " + this.getNome() + " ---");

        for(Prodotto prodotto : this.getProdottiCaricati()) {
            System.out.println(prodotto.getNome() + " - Approvato : " + prodotto.isApprovato());
        }
    }


    @Override
    public void update(Ordine ordine) {
        System.out.println("--- L'azienda " + this.getNome()+  " ha ricevuto l'ordine ricevuto da "
                + ordine.getAcquirente().getNome() + " ---");
    }
}



