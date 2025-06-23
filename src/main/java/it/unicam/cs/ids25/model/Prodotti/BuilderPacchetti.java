package it.unicam.cs.ids25.model.Prodotti;

import it.unicam.cs.ids25.model.Prodotti.Enum.Categoria;
import it.unicam.cs.ids25.model.Prodotti.Enum.Certificazioni;
import it.unicam.cs.ids25.model.Utenti.Azienda;

import java.util.ArrayList;
import java.util.List;

public class BuilderPacchetti {
    private int quantita;
    private String descrizione;
    private String nome;
    private Azienda azienda;
    private List<Prodotto> prodotti = new ArrayList<>();
    private Categoria categoria;
    private List<Certificazioni> certificazioni;
    private double prezzo;


    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setAzienda(Azienda azienda) {
        this.azienda = azienda;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setProdotti(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setCertificazioni(List<Certificazioni> certificazioni) {
        this.certificazioni = certificazioni;
    }

    public PacchettoDiProdotti buildPacchetto() {
        if (nome == null || descrizione == null || categoria == null || prodotti.size()<2) {
            throw new IllegalStateException("Campi obbligatori mancanti");
        }
        return new PacchettoDiProdotti(nome, descrizione, prezzo, quantita, categoria, azienda,certificazioni,prodotti);

    }
}
