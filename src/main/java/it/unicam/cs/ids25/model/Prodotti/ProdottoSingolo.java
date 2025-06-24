package it.unicam.cs.ids25.model.Prodotti;

import it.unicam.cs.ids25.model.Prodotti.Enum.Categoria;
import it.unicam.cs.ids25.model.Prodotti.Enum.Certificazioni;
import it.unicam.cs.ids25.model.Utenti.Azienda;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.List;

/**
 * La classe ProdottoSingolo estende la classe {@link Prodotto} da cui prende i propri campi per la creazione di un ProdottoSngolo
 */
@Entity
@DiscriminatorValue("singolo")
public class ProdottoSingolo extends Prodotto {

    /**
     * Costruttore vuoto per springboot
     */
    public ProdottoSingolo() {
        super();
    }

    /**
     * Metodo costruttore pche viene richiamato dal builder per la creazione dei prodotti singoli
     *
     * @param nome
     * @param descrizione
     * @param prezzo
     * @param quantita
     * @param categoria
     * @param azienda
     * @param certificazione
     */
    protected ProdottoSingolo(String nome, String descrizione, double prezzo, int quantita, Categoria categoria, Azienda azienda, List<Certificazioni> certificazione) {
        super(nome, descrizione, prezzo, quantita, categoria, azienda, certificazione);
    }
}
