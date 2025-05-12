package it.unicam.cs.ids25.model.Prodotti;

import it.unicam.cs.ids25.model.Utenti.Azienda;
import it.unicam.cs.ids25.model.Categoria;

public class ProdottoSingolo extends Prodotto {


    public ProdottoSingolo(String nome, String descrizione, double prezzo, int quantita, Categoria categoria, Azienda azienda) {
        super(nome, descrizione, prezzo, quantita, categoria, azienda);
    }

}
