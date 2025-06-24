package it.unicam.cs.ids25.model.Prodotti;

import it.unicam.cs.ids25.model.Prodotti.Enum.Categoria;
import it.unicam.cs.ids25.model.Prodotti.Enum.Certificazioni;
import it.unicam.cs.ids25.model.Utenti.Azienda;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe PacchettoDiProdotti estende la classe astratta {@link Prodotto}
 * in piu ha al suo interno una lista di prodotti per la creazione del pacchetto
 */
@Entity
@DiscriminatorValue("pacchetto")
public class PacchettoDiProdotti extends Prodotto {

    @ManyToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "pacchetto_prodotto",
            joinColumns = @JoinColumn(name = "pacchetto_id"),
            inverseJoinColumns = @JoinColumn(name = "prodotto_id")
    )
    private List<Prodotto> pacchetto;

    /**
     * Costruttore per springboot con inizializzazione della lista di parodotti
     */
    public PacchettoDiProdotti() {
        super();
        this.pacchetto = new ArrayList<>();
    }

    /**
     * Metodo costruttore che viene richiamato dal builder per la crazione di un pacchetto tramite i parametri passati
     *
     * @param nome
     * @param descrizione
     * @param prezzo
     * @param quantita
     * @param categoria
     * @param azienda
     * @param certificazioni
     * @param pacchetto
     */
    protected PacchettoDiProdotti(String nome, String descrizione, double prezzo, int quantita,
                                  Categoria categoria, Azienda azienda, List<Certificazioni> certificazioni, List<Prodotto> pacchetto) {
        super("pacchetto "+ nome, descrizione, prezzo, quantita, Categoria.Pacchetto, azienda, certificazioni);
        this.pacchetto = pacchetto;
    }

    public List<Prodotto> getPacchetto() {
        return pacchetto;
    }

    public void getProd() {
        for (Prodotto p : pacchetto) {
         System.out.println(p.getNome() + " " + p.getDescrizione());
        }
    }

}