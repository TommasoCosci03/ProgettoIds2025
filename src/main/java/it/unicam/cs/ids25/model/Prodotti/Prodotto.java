package it.unicam.cs.ids25.model.Prodotti;
import it.unicam.cs.ids25.model.Utenti.Azienda;
import it.unicam.cs.ids25.model.Utenti.Curatore;

import java.util.ArrayList;

public abstract class  Prodotto {
    private static int contatoreId = 0;
    private int id;
    private String nome;
    private String descrizione;
    private double prezzo;
    private int quantita;
    private Categoria categoria;
    private ArrayList<Certificazioni> certificazioni;
    private boolean approvato;
    private Azienda azienda;
    private Curatore curatore= Curatore.getInstanzaCuratore();


    public Prodotto(String nome, String descrizione, double prezzo,
                    int quantita, Categoria categoria, Azienda azienda, ArrayList<Certificazioni> certificazioni) {
        this.id = ++contatoreId;
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.quantita = quantita;
        this.categoria = categoria;
        this.approvato = false;
        this.azienda = azienda;
        this.certificazioni = certificazioni;
        curatore.addRichiesta(this);
    }

    public void inviaRichiesta() {curatore.addRichiesta(this);}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public ArrayList<Certificazioni> getCertificazioni() {
        return certificazioni;
    }

    public void setCertificazioni(ArrayList<Certificazioni> certificazioni) {
        this.certificazioni = certificazioni;
    }

    public boolean isApprovato() {
        return approvato;
    }

    public void setApprovato(boolean approvato) {
        this.approvato = approvato;
    }

    public Azienda getAzienda() {
        return azienda;
    }

    public void setAzienda(Azienda azienda) {
        this.azienda = azienda;
    }
}
