package it.unicam.cs.ids25.model.Prodotti;

import it.unicam.cs.ids25.model.Utenti.Azienda;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("trasformato")
public class ProdottoTrasformato extends Prodotto {

    @Transient
    private String materiaPrima;

    public ProdottoTrasformato() {
        super();
        this.materiaPrima = null;
    }

    protected ProdottoTrasformato(String nome, String descrizione, double prezzo, int quantita, Categoria categoria, Azienda azienda, String materiaPrima, List<Certificazioni> certificazioni) {
        super(nome, descrizione, prezzo, quantita, categoria, azienda, certificazioni);
        this.materiaPrima = materiaPrima;
    }

    public String getMateriaPrima() {
        return materiaPrima;
    }
}
