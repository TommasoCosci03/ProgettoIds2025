package it.unicam.cs.ids25.model.Utenti;

import com.fasterxml.jackson.annotation.*;
import it.unicam.cs.ids25.model.Autenticazione.Utente;
import it.unicam.cs.ids25.model.Evento;
import it.unicam.cs.ids25.model.Prodotti.Enum.Categoria;
import it.unicam.cs.ids25.model.Prodotti.Enum.Certificazioni;
import it.unicam.cs.ids25.model.Observer.Observer;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Rappresenta un'entità aziendale all'interno del sistema.
 * Una {@code Azienda} è un {@link Utente} che può caricare prodotti, partecipare a eventi.
 * Questa classe è astratta e viene estesa da specifici tipi di azienda.
 * L'ereditarietà è gestita tramite una tabella unica con una colonna discriminatore {@code tipo_azienda}.
 * l'azienda può essere:
 * {@link Produttore } può inserire nel marketplace solamente prodotti singoli
 * {@link Trasformatore } può inserire nel marketplace solamente prodotti trasformati, specificando le materie prime,
 * le materie prime non devono essere necessariamente presenti nel marketplace; possono dunque essere reperite al di fuori di esso
 * {@link Distributore} può inserire nel marketplace solamente pacchetti di prodotti, specificando i prodotti
 * i prodotti del pacchetto devono essere necessariamente presenti nel marketplace
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo_azienda")

public abstract class Azienda extends Utente implements Observer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sede;
    @OneToMany(mappedBy = "azienda", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Prodotto> prodottiCaricati = new ArrayList<>();
    @ManyToMany(mappedBy = "invitati")
    private List<Evento> eventi;

    private Double latitudine;
    private Double longitudine;

    protected Azienda() {
        super();
    };

    /**
     * Costruttore per inizializzare una nuova azienda.
     *
     * @param nome     il nome dell'azienda
     * @param sede     la sede dell'azienda
     * @param username lo username dell'utente associato
     * @param password la password dell'utente associato
     */
    public Azienda(String nome, String sede,String username, String password) {
        super(username, password);
        this.nome = nome;
        this.sede = sede;
    }

    public Long getId() {
        return id;
}

    public String getNome() {
        return nome;
    }

    public String getSede() {
        return sede;
    }

    public List<Prodotto> getListaProdotti(){
        return prodottiCaricati;
    }

    @JsonProperty("prodottiCaricati")
    public List<Long> getIdProdottiCaricati(){
        return prodottiCaricati.stream().map(Prodotto::getId).toList();
    }


    /**
     * Metodo astratto che consente alle sottoclassi di creare un prodotto specifico per l'azienda.
     * la  tipologia del prodotto dipende dalla tipologia dell'azienda
     * @param nome           il nome del prodotto
     * @param descrizione    la descrizione del prodotto
     * @param prezzo         il prezzo unitario del prodotto
     * @param quantita       la quantità disponibile
     * @param categoria      la categoria del prodotto
     * @param certificazioni eventuali certificazioni del prodotto
     * @return il prodotto creato
     */
    public abstract Prodotto creaProdottoAzienda(String nome, String descrizione, double prezzo, int quantita,
                                                 Categoria categoria, List<Certificazioni> certificazioni);

    /**
     * Visualizza i prodotti caricati dall'azienda.
     * Metodo vuoto da implementare o sovrascrivere se necessario per estensioni future.
     */
    public void vediProdottiCaricati(){}


    /**
     * Rimuove un prodotto dalla lista dei prodotti caricati.
     *
     * @param prodotto il prodotto da eliminare
     */
    public void eliminaProdotto(Prodotto prodotto){
        prodottiCaricati.remove(prodotto);
    }


    /**
     * Restituisce la categoria corrispondente al codice numerico passato.
     *
     * @param categoria codice numerico della categoria
     * @return la categoria associata
     * @throws IllegalArgumentException se il codice non è valido
     */
    public Categoria creazioneCategoria(int categoria){

        return switch (categoria) {
            case 1 -> Categoria.Carne;
            case 2 -> Categoria.Pesce;
            case 3 -> Categoria.Frutta;
            case 4 -> Categoria.Verdura;
            case 5 -> Categoria.Cereali;
            case 6 -> Categoria.Legumi;
            case 7 -> Categoria.Pacchetto;
            case 8 -> Categoria.Latte;
            case 9 -> Categoria.Formaggi;
            case 10 -> Categoria.Uova;
            default -> throw new IllegalArgumentException("Categoria non valida");
        };

    }


    public List<Evento> getEventi() {
        return eventi;
    }

    public void setEventi(List<Evento> eventi) {
        this.eventi = eventi;
    }

    public Double getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(Double longitudine) {
        this.longitudine = longitudine;
    }

    public Double getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(Double latitudine) {
        this.latitudine = latitudine;
    }
}

