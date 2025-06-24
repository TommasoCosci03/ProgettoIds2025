package it.unicam.cs.ids25.model.Prodotti;

import it.unicam.cs.ids25.model.Prodotti.Enum.Categoria;
import it.unicam.cs.ids25.model.Prodotti.Enum.Certificazioni;
import it.unicam.cs.ids25.model.Utenti.Azienda;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.List;

/**
 * La classe PacchettoDiProdotti estende la classe astratta {@link Prodotto}
 * in piu ha al suo interno un campo {@param materiePrime} per la creazione di un prodotto trasformato
 */
@Entity
@DiscriminatorValue("trasformato")
public class ProdottoTrasformato extends Prodotto {


    private String materiePrime;

    /**
     * Costruttore per springboot con inizializzazione del campo {@param materiePrime}
     */
    public ProdottoTrasformato() {
        super();
        this.materiePrime = null;
    }

    /**
     * Metodo costruttore che viene richiamato dal builder per la creazione di un prodotto trasformato
     *
     * @param nome
     * @param descrizione
     * @param prezzo
     * @param quantita
     * @param categoria
     * @param azienda
     * @param materiePrime
     * @param certificazioni
     */
    protected ProdottoTrasformato(String nome, String descrizione, double prezzo, int quantita, Categoria categoria,
                                  Azienda azienda, String materiePrime, List<Certificazioni> certificazioni) {

        super(nome, descrizione, prezzo, quantita, categoria, azienda, certificazioni);
        this.materiePrime = materiePrime;
    }

    public String getMateriePrime() {
        return materiePrime;
    }
}
