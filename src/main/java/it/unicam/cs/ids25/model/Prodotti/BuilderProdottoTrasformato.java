package it.unicam.cs.ids25.model.Prodotti;

import it.unicam.cs.ids25.model.Prodotti.Enum.Categoria;
import it.unicam.cs.ids25.model.Prodotti.Enum.Certificazioni;
import it.unicam.cs.ids25.model.Utenti.Azienda;

import java.util.List;

public class BuilderProdottoTrasformato {
    private int quantita;
    private String descrizione;
    private String nome;
    private Azienda azienda;
    private Categoria categoria;
    private List<Certificazioni> certificazioni;
    private double prezzo;
    private String materiePrime;

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

    public void setMateriaPrima(String materiePrime) {
        this.materiePrime = materiePrime;
    }

    public ProdottoTrasformato buildProdottoTrasformato() {
        if (nome == null || descrizione == null || categoria == null || azienda == null) {
            throw new IllegalStateException("Campi obbligatori mancanti");
        }
        return new ProdottoTrasformato(nome, descrizione, prezzo, quantita, categoria, azienda, materiePrime, certificazioni);

    }
}
