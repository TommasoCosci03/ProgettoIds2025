package it.unicam.cs.ids25.model.Utenti;

import it.unicam.cs.ids25.model.Acquisto.Carrello;
import it.unicam.cs.ids25.model.Acquisto.CarrelloItem;
import it.unicam.cs.ids25.model.Autenticazione.Utente;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import jakarta.persistence.*;


/**
 * Rappresenta un acquirente nel sistema.
 * Un acquirente è un {@link Utente} che può visualizzare esclusivamente i prodotti validati dal curatore,
 * aggiungerli al proprio carrello ed effettuare un ordine.
 * Ogni acquirente dispone di un carrello personale, di un saldo e di un nome identificativo.
 */
@Entity
@DiscriminatorValue("acquirente")
public class Acquirente extends Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @OneToOne(cascade = CascadeType.ALL)
    private Carrello carrello;
    private double saldo;


    /**
     * Crea un nuovo acquirente con il nome specificato.
     * Inizializza un nuovo carrello e imposta il saldo a 0.
     *
     * @param nome il nome dell'acquirente
     */
    public Acquirente(String nome) {
        this.nome = nome;
        this.carrello = new Carrello();
        this.carrello.setAcquirente(this);
        this.saldo = 0.0;
    }

    public Acquirente() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Carrello getCarrello() {
        return carrello;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }



    /**
     * Aggiunge un prodotto e la relativa quantità al carrello.
     *Questo avviene se il prodotto e la quantità sono disponibili
     *
     * @param prodotto il prodotto da aggiungere
     * @param quantita la quantità del prodotto
     */
    public void aggiungiAlCarrello(Prodotto prodotto, int quantita) {
        CarrelloItem order = new CarrelloItem(prodotto, quantita);
        carrello.setProdottiDaAcquistare(order);
    }

    /**
     *elimina tutti i prodotti dal carrello dell'utente,
     * il carrello torna ad essere vuoto
     */
    public void cancellaCarrello() {
        carrello.getProdottiDaAcquistare().clear();

    }
}
