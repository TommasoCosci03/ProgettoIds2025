package it.unicam.cs.ids25.model.Utenti;


import it.unicam.cs.ids25.model.Prodotti.Enum.Categoria;
import it.unicam.cs.ids25.model.Prodotti.Enum.Certificazioni;
import it.unicam.cs.ids25.model.Acquisto.Ordine;
import it.unicam.cs.ids25.model.Prodotti.BuilderProdottoTrasformato;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

import java.util.List;


/**
 * Rappresenta un'azienda trasformatrice.
 * Un {@code Trasformatore} è una {@link Azienda} specializzata nella creazione
 * di prodotti trasformati, utilizzando materie prime dichiarate.
 * le materie prime non necessariamente devono derivare dal marketplace.
 ** Implementa anche l'interfaccia {@code Observer} per ricevere notifiche sugli ordini
 *  * nel momento in cui un proprio prodotto trasformato viene acquistato
 */
@Entity
@DiscriminatorValue("trasformatore")
public class Trasformatore extends Azienda {
    @Transient
    private String materiePrime;

    /**
     * Crea un nuovo trasformatore con i dati specificati.
     *
     * @param nome     il nome dell'azienda
     * @param sede     la sede dell'azienda
     * @param email    l'email usata come username
     * @param password la password di accesso
     */
    public Trasformatore(String nome, String sede, String email, String password) {
        super(nome, sede, email, password);
    }


    protected Trasformatore() {
        super();
    }



    /**
     * Crea un prodotto trasformato, assicurandosi che le materie prime siano state specificate.
     *
     * @param nome           il nome del prodotto
     * @param descrizione    la descrizione del prodotto
     * @param prezzo         il prezzo del prodotto
     * @param quantita       la quantità disponibile
     * @param categoria      la categoria del prodotto
     * @param certificazioni eventuali certificazioni associate
     * @return il prodotto trasformato creato
     * @throws IllegalStateException se le materie prime non sono state impostate
     */
    @Override
    public Prodotto creaProdottoAzienda(String nome, String descrizione, double prezzo, int quantita,
                                        Categoria categoria, List<Certificazioni> certificazioni) {
        if (materiePrime == null) {
            throw new IllegalStateException("materie prime mancanti");
        }
        BuilderProdottoTrasformato builderProdottoTrasformato = new BuilderProdottoTrasformato();
        builderProdottoTrasformato.setNome(nome);
        builderProdottoTrasformato.setDescrizione(descrizione);
        builderProdottoTrasformato.setPrezzo(prezzo);
        builderProdottoTrasformato.setAzienda(this);
        builderProdottoTrasformato.setQuantita(quantita);
        builderProdottoTrasformato.setMateriaPrima(materiePrime);
        builderProdottoTrasformato.setCategoria(categoria);
        builderProdottoTrasformato.setCertificazioni(certificazioni);
        materiePrime = null;
        return builderProdottoTrasformato.buildProdottoTrasformato();
    }

    public void setMateriePrime(String materiePrime) {
        this.materiePrime = materiePrime;
    }

    @Override
    public void vediProdottiCaricati() {
        System.out.println("--- Lista di prodotti caricati da " + this.getNome() + " ---\n" + this.getIdProdottiCaricati());
    }


    @Override
    public void update(Ordine ordine) {
        System.out.println("--- L'azienda " + this.getNome() + " ha ricevuto l'ordine ricevuto da "
                + ordine.getAcquirente().getNome() + " ---");
    }
}



