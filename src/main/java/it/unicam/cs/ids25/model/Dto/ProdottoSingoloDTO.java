package it.unicam.cs.ids25.model.Dto;

import it.unicam.cs.ids25.model.Prodotti.Categoria;
import it.unicam.cs.ids25.model.Prodotti.Certificazioni;

import java.util.ArrayList;
import java.util.List;

public class ProdottoSingoloDTO {
    String nome;
    String descrizione;
    double prezzo;
    int quantita;
    Categoria categoria;
    long idAzienda;
    List<Certificazioni> certificazioni;

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

    public long getIdAzienda() {
        return idAzienda;
    }

    public void setIdAzienda(long idAzienda) {
        this.idAzienda = idAzienda;
    }

    public List<Certificazioni> getCertificazioni() {
        return certificazioni;
    }

    public void setCertificazioni(ArrayList<Certificazioni> certificazioni) {
        this.certificazioni = certificazioni;
    }
}
