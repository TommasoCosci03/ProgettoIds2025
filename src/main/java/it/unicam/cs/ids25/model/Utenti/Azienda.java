package it.unicam.cs.ids25.model.Utenti;

import it.unicam.cs.ids25.model.Prodotti.Categoria;
import it.unicam.cs.ids25.model.Prodotti.Certificazioni;
import it.unicam.cs.ids25.model.Observer.Observer;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import jakarta.persistence.*;

import java.util.ArrayList;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo_azienda")

public abstract class Azienda implements Observer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String sede;
    @Transient
    private ArrayList<Prodotto> prodottiCaricati;

    protected Azienda() {}

    public Azienda(String nome, String sede) {
        this.nome = nome;
        this.sede = sede;
        this.prodottiCaricati = new ArrayList<>();
    }


    public long getId() {
        return id;
}

    public String getNome() {
        return nome;
    }

    public String getSede() {
        return sede;
    }

    public ArrayList<Prodotto> getProdottiCaricati() {
        return prodottiCaricati;
    }



    public abstract Prodotto creaProdottoAzienda(String nome, String descrizione, double prezzo, int quantita,
                                                 Categoria categoria, ArrayList<Certificazioni> certificazioni);
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


}

