package it.unicam.cs.ids25.model.Prodotti;

import it.unicam.cs.ids25.model.Utenti.Azienda;

import java.util.ArrayList;

public class BuilderProdottoSingolo {
    private int quantita;
    private String descrizione;
    private String nome;
    private Azienda azienda;
    private Categoria categoria;
    private ArrayList<Certificazioni> certificazioni;
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

    public void setCertificazioni(ArrayList<Certificazioni> certificazioni) {
        this.certificazioni = certificazioni;
    }

    public ProdottoSingolo buildProdottoSingolo() {
        if (nome == null || descrizione == null || categoria == null || azienda == null) {
            throw new IllegalStateException("Campi obbligatori mancanti");
        }
        return new ProdottoSingolo(nome, descrizione, prezzo, quantita, categoria, azienda, certificazioni);
    }
}
