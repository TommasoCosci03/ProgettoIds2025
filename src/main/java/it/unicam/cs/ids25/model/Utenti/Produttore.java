package it.unicam.cs.ids25.model.Utenti;


import it.unicam.cs.ids25.model.Prodotti.Enum.Categoria;
import it.unicam.cs.ids25.model.Prodotti.Enum.Certificazioni;
import it.unicam.cs.ids25.model.Acquisto.Ordine;
import it.unicam.cs.ids25.model.Prodotti.BuilderProdottoSingolo;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.List;

/**
 * Rappresenta un'azienda di tipo produttore.
 * Un {@code Produttore} è una {@link Azienda} che può creare prodotti singoli,
 * specificandone tutti gli attributi.
 * Implementa anche l'interfaccia {@code Observer} per ricevere notifiche sugli ordini
 * nel momento in cui un proprio prodotto viene acquistato
 */
@Entity
@DiscriminatorValue("produttore")
public class Produttore extends Azienda {

    /**
     * Crea un produttore con i dati specificati.
     *
     * @param nome     il nome dell'azienda produttrice
     * @param sede     la sede dell'azienda
     * @param email    l'email usata come username
     * @param password la password per l'autenticazione
     */
    public Produttore(String nome, String sede, String email, String password) {
        super(nome, sede, email, password);
    }

    protected Produttore() {
        super();
    }


    /**
     * Crea un prodotto singolo associato al produttore corrente.
     *
     * @param nome           il nome del prodotto
     * @param descrizione    la descrizione del prodotto
     * @param prezzo         il prezzo unitario del prodotto
     * @param quantita       la quantità disponibile
     * @param categoria      la categoria del prodotto
     * @param certificazioni eventuali certificazioni associate
     * @return il prodotto creato
     */
    @Override
    public Prodotto creaProdottoAzienda(String nome, String descrizione, double prezzo, int quantita,
                                        Categoria categoria, List<Certificazioni> certificazioni) {
        BuilderProdottoSingolo prodottobuilder = new BuilderProdottoSingolo();
        prodottobuilder.setNome(nome);
        prodottobuilder.setDescrizione(descrizione);
        prodottobuilder.setPrezzo(prezzo);
        prodottobuilder.setAzienda(this);
        prodottobuilder.setQuantita(quantita);
        prodottobuilder.setCategoria(categoria);
        prodottobuilder.setCertificazioni(certificazioni);
        return prodottobuilder.buildProdottoSingolo();
    }


    @Override
    public void vediProdottiCaricati() {
        System.out.println("--- Lista di prodotti caricati da " + this.getNome() + " ---\n" + this.getIdProdottiCaricati());
    }

    @Override
    public void update(Ordine ordine) {
        /*System.out.println("--- L'azienda " + this.getNome()+  " ha ricevuto l'ordine ricevuto da "
                + ordine.getAcquirente().getNome() + " ---");*/
    }
}
