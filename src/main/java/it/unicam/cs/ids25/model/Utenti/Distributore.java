package it.unicam.cs.ids25.model.Utenti;

import it.unicam.cs.ids25.model.Prodotti.Enum.Categoria;
import it.unicam.cs.ids25.model.Prodotti.Enum.Certificazioni;
import it.unicam.cs.ids25.model.Acquisto.Ordine;
import it.unicam.cs.ids25.model.Prodotti.BuilderPacchetti;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import it.unicam.cs.ids25.model.Observer.Observer;
import java.util.List;



/**
 * Rappresenta un'azienda di tipo distributore.
 * Un {@code Distributore} è un{@link Azienda} che può creare pacchetti di prodotti composti
 * da almeno due elementi.
 * i pacchetti possono essere composti solamente da prodotti presenti nel marketplace
 * Implementa anche il pattern Observer per ricevere notifiche sugli ordini nel momento in cui un proprio pacchetto viene acquistato.
 */
@Entity
@DiscriminatorValue("distributore")
public class Distributore extends Azienda {
    @Transient
    List<Prodotto> prodotti;

    /**
     * Costruttore pubblico per creare un distributore con i dati principali.
     *
     * @param nome     il nome dell'azienda distributrice
     * @param sede     la sede dell'azienda
     * @param email    l'email usata come username
     * @param password la password per l'autenticazione
     */
    public Distributore(String nome, String sede, String email, String password) {
        super(nome, sede, email, password);
    }

    protected Distributore() {
        super();
    }



    /**
     * Crea un pacchetto di prodotti per il distributore.
     * È necessario che siano stati impostati almeno due prodotti prima della chiamata.
     *
     * @param nome           il nome del pacchetto
     * @param descrizione    la descrizione del pacchetto
     * @param prezzo         il prezzo totale del pacchetto
     * @param quantita       la quantità disponibile del pacchetto
     * @param categoria      la categoria (deve essere {@code Categoria.Pacchetto})
     * @param certificazioni eventuali certificazioni associate al pacchetto
     * @return il pacchetto prodotto
     * @throws IllegalStateException se la lista dei prodotti contiene meno di due elementi
     */
    @Override
    public Prodotto creaProdottoAzienda(String nome, String descrizione, double prezzo, int quantita, Categoria categoria, List<Certificazioni> certificazioni) {
        if (prodotti.size() < 2) {
            throw new IllegalStateException("il pacchetto deve essere composto da almeno due prodotti");
        }
        BuilderPacchetti builderPacchetti = new BuilderPacchetti();
        builderPacchetti.setPrezzo(prezzo);
        builderPacchetti.setQuantita(quantita);
        builderPacchetti.setCategoria(Categoria.Pacchetto);
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


    /**
     * Metodo dell'interfaccia {@link Observer}
     * che permette di ricevere notifiche al momento di un nuovo ordine.
     *
     * @param ordine l'ordine notificato
     */
    @Override
    public void update(Ordine ordine) {
        System.out.println("--- L'azienda " + this.getNome() + " ha ricevuto l'ordine ricevuto da "
                + ordine.getAcquirente().getNome() + " ---");
    }
}
