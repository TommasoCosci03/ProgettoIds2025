package progettoIDS25.src.main.java.it.unicam.cs.ids25.model;
import java.util.ArrayList;

public class Prodotto {
    private static long contatoreId = 0;
    private long id;
    private String nome;
    private String descrizione;
    private double prezzo;
    private int quantita;
    private Categoria categoria;
    private ArrayList<Certificazioni> certificazioni;
    private boolean approvato;
    private Azienda azienda;

    public Prodotto(String nome, String descrizione, double prezzo,
                    int quantita, Categoria categoria, Azienda azienda) {
        this.id = ++contatoreId;
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.quantita = quantita;
        this.categoria = categoria;
        this.approvato = false;
        this.azienda = azienda;
    }

    public long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public int getQuantita() {
        return quantita;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public ArrayList<Certificazioni> getCertificazioni() {
        return certificazioni;
    }

    public boolean isApprovato() {
        return approvato;
    }

    public Azienda getAzienda() {
        return azienda;
    }

}
