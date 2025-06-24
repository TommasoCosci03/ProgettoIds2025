package it.unicam.cs.ids25.model.Prodotti;

import it.unicam.cs.ids25.model.Prodotti.Enum.Categoria;
import it.unicam.cs.ids25.model.Prodotti.Enum.Certificazioni;
import it.unicam.cs.ids25.model.Utenti.Azienda;

import java.util.List;

/**
 * La classe BuilderPacchetti è responsabile della crazione dei {@link ProdottoSingolo} tramite il design pattern Builder
 */
public class BuilderProdottoSingolo {
    private int quantita;
    private String descrizione;
    private String nome;
    private Azienda azienda;
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

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setCertificazioni(List<Certificazioni> certificazioni) {
        this.certificazioni = certificazioni;
    }

    /**
     * Metodo per la creazione di un Prodotto singolo tramite il costruttore di {@link ProdottoSingolo}
     * @return new {@link ProdottoSingolo}
     */
    public ProdottoSingolo buildProdottoSingolo() {
        return new ProdottoSingolo(nome, descrizione, prezzo, quantita, categoria, azienda, certificazioni);
    }
}
