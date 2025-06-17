package it.unicam.cs.ids25.model.Utenti;

import com.fasterxml.jackson.annotation.*;
import it.unicam.cs.ids25.model.Evento;
import it.unicam.cs.ids25.model.Prodotti.Enum.Categoria;
import it.unicam.cs.ids25.model.Prodotti.Enum.Certificazioni;
import it.unicam.cs.ids25.model.Observer.Observer;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo_azienda")

public abstract class Azienda implements Observer {
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
    protected Azienda() {};

    public Azienda(String nome, String sede) {
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

    public abstract Prodotto creaProdottoAzienda(String nome, String descrizione, double prezzo, int quantita,
                                                 Categoria categoria, List<Certificazioni> certificazioni);
    public void vediProdottiCaricati(){}

    public void eliminaProdotto(Prodotto prodotto){
        prodottiCaricati.remove(prodotto);
    }


    public Categoria creazioneCategoria(int categoria){

        return switch (categoria) {
            case 1 -> Categoria.Carne;
            case 2 -> Categoria.Pesce;
            case 3 -> Categoria.Frutta;
            case 4 -> Categoria.Verdura;
            case 5 -> Categoria.Cereali;
            case 6 -> Categoria.Legumi;
            case 7 -> Categoria.Pacchetto;
            default -> throw new IllegalArgumentException("Categoria non valida");
        };

    }


    public List<Evento> getEventi() {
        return eventi;
    }

    public void setEventi(List<Evento> eventi) {
        this.eventi = eventi;
    }
}

