package it.unicam.cs.ids25.model.Prodotti;

import it.unicam.cs.ids25.model.Utenti.Azienda;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("singolo")
public class ProdottoSingolo extends Prodotto {

    public ProdottoSingolo() {
        super();
    }

    protected ProdottoSingolo(String nome, String descrizione, double prezzo, int quantita, Categoria categoria, Azienda azienda, List<Certificazioni> certificazione) {
        super(nome, descrizione, prezzo, quantita, categoria, azienda, certificazione);
    }
}
