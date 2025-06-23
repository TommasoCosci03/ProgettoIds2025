package it.unicam.cs.ids25.model.Prodotti;

import it.unicam.cs.ids25.model.Prodotti.Enum.Categoria;
import it.unicam.cs.ids25.model.Prodotti.Enum.Certificazioni;
import it.unicam.cs.ids25.model.Utenti.Azienda;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

    public PacchettoDiProdotti() {
        super();
        this.pacchetto = new ArrayList<>();
    }

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