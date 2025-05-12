package it.unicam.cs.ids25.model.Utenti;


import it.unicam.cs.ids25.model.Categoria;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import it.unicam.cs.ids25.model.Prodotti.ProdottoTrasformato;

public class Trasformatore extends Azienda {
    public Trasformatore(String nome, String sede) {
        super(nome, sede);
    }

    @Override
    public Prodotto creaProdottoAzienda(String nome, String descrizione, double prezzo, int quantita,
                                        Categoria categoria) {
        return new ProdottoTrasformato(nome,descrizione,prezzo,quantita,categoria, this);
    }

    @Override
    public void vediProdottiCaricati() {
        System.out.println("--- Lista di prodotti caricati da " + this.getNome() + " ---");

        for(Prodotto prodotto : this.getProdottiCaricati()) {
            System.out.println(prodotto.getNome() + " - Approvato : " + prodotto.isApprovato());
        }
    }



}



