package it.unicam.cs.ids25.model.Prodotti;

import it.unicam.cs.ids25.model.Certificazioni;
import it.unicam.cs.ids25.model.Utenti.Azienda;
import it.unicam.cs.ids25.model.Categoria;

import java.util.ArrayList;

public class ProdottoTrasformato extends Prodotto {

    private String materiaPrima;

    protected ProdottoTrasformato(String nome, String descrizione, double prezzo, int quantita, Categoria categoria, Azienda azienda, String materiaPrima, ArrayList<Certificazioni> certificazioni) {
        super(nome, descrizione, prezzo, quantita, categoria, azienda, certificazioni);
        this.materiaPrima = materiaPrima;
    }

    public String getMateriaPrima() {
        return materiaPrima;
    }
}
