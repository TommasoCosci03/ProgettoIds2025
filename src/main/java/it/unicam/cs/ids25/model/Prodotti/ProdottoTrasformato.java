package it.unicam.cs.ids25.model.Prodotti;

import it.unicam.cs.ids25.model.Utenti.Azienda;
import it.unicam.cs.ids25.model.Categoria;

public class ProdottoTrasformato extends Prodotto {

    public ProdottoTrasformato(String nome, String descrizione, double prezzo, int quantita, Categoria categoria, Azienda azienda) {
        super(nome, descrizione, prezzo, quantita, categoria, azienda);
    }

}
