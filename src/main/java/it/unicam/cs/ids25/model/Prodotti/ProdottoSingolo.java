package it.unicam.cs.ids25.model.Prodotti;

import it.unicam.cs.ids25.model.Utenti.Azienda;

import java.util.ArrayList;

public class ProdottoSingolo extends Prodotto {


    protected ProdottoSingolo(String nome, String descrizione, double prezzo, int quantita, Categoria categoria, Azienda azienda, ArrayList<Certificazioni> certificazione) {
        super(nome, descrizione, prezzo, quantita, categoria, azienda, certificazione);
    }

}
