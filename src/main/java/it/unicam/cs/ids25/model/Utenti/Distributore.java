package it.unicam.cs.ids25.model.Utenti;

import it.unicam.cs.ids25.model.Prodotti.Enum.Categoria;
import it.unicam.cs.ids25.model.Prodotti.Enum.Certificazioni;
import it.unicam.cs.ids25.model.Acquisto.Ordine;
import it.unicam.cs.ids25.model.Prodotti.BuilderPacchetti;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

import java.util.List;

@Entity
@DiscriminatorValue("distributore")
public class Distributore extends Azienda {
    @Transient
    List<Prodotto> prodotti;

    public Distributore(String nome, String sede) {super(nome, sede);
    }

    protected Distributore() {super();}

    @Override
    public Prodotto creaProdottoAzienda(String nome, String descrizione, double prezzo, int quantita, Categoria categoria,  List<Certificazioni> certificazioni) {
        if (prodotti.size() < 2) { throw new IllegalStateException("il pacchetto deve essere composto da almeno due prodotti");}
        BuilderPacchetti builderPacchetti = new BuilderPacchetti();
        builderPacchetti.setPrezzo(prezzo);
        builderPacchetti.setQuantita(quantita);
        builderPacchetti.setCategoria(categoria);
        builderPacchetti.setNome(nome);
        builderPacchetti.setDescrizione(descrizione);
        builderPacchetti.setAzienda(this);
        builderPacchetti.setCertificazioni(certificazioni);
        builderPacchetti.setProdotti(prodotti);
        prodotti = null;
        return builderPacchetti.buildPacchetto();

    }

    public void setProdotti(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    @Override
    public void update(Ordine ordine) {
        System.out.println("--- L'azienda " + this.getNome()+  " ha ricevuto l'ordine ricevuto da "
                + ordine.getAcquirente().getNome() + " ---");
    }
}
