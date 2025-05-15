package it.unicam.cs.ids25.model.Utenti;


import it.unicam.cs.ids25.model.Categoria;
import it.unicam.cs.ids25.model.Certificazioni;
import it.unicam.cs.ids25.model.Prodotti.BuilderProdottoTrasformato;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import it.unicam.cs.ids25.model.Prodotti.ProdottoTrasformato;

import java.util.ArrayList;

public class Trasformatore extends Azienda {
    private String materiePrime;
    public Trasformatore(String nome, String sede) {
        super(nome, sede);
    }

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



}



