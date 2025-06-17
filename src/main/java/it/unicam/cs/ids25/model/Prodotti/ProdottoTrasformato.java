package it.unicam.cs.ids25.model.Prodotti;

import it.unicam.cs.ids25.model.Prodotti.Enum.Categoria;
import it.unicam.cs.ids25.model.Prodotti.Enum.Certificazioni;
import it.unicam.cs.ids25.model.Utenti.Azienda;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
@DiscriminatorValue("trasformato")
public class ProdottoTrasformato extends Prodotto {


    private String materiePrime;

    public ProdottoTrasformato() {
        super();
        this.materiePrime = null;
    }

    protected ProdottoTrasformato(String nome, String descrizione, double prezzo, int quantita, Categoria categoria,
                                  Azienda azienda, String materiePrime, List<Certificazioni> certificazioni) {

        super(nome, descrizione, prezzo, quantita, categoria, azienda, certificazioni);
        this.materiePrime = materiePrime;
    }

    public String getMateriePrime() {
        return materiePrime;
    }
}
