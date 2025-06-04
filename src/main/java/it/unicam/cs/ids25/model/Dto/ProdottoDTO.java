package it.unicam.cs.ids25.model.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;

public class ProdottoDTO {

    private Long id;
    private String nome;
    private String descrizione;
    private double prezzo;
    private int quantita;
    private String categoria;
    private Long aziendaId;
    private String aziendaNome;


    public ProdottoDTO() {}

    public ProdottoDTO(Long id, String nome, String descrizione,
                       double prezzo, int quantita, String categoria,
                       Long aziendaId, String aziendaNome) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.quantita = quantita;
        this.categoria = categoria;
        this.aziendaId = aziendaId;
        this.aziendaNome = aziendaNome;
    }


    public static ProdottoDTO fromEntity(Prodotto p) {
        return new ProdottoDTO(
                p.getId(),
                p.getNome(),
                p.getDescrizione(),
                p.getPrezzo(),
                p.getQuantita(),
                p.getCategoria().name(),
                p.getAzienda() != null ? p.getAzienda().getId() : null,
                p.getAzienda() != null ? p.getAzienda().getNome() : null
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Long getAziendaId() {
        return aziendaId;
    }

    public void setAziendaId(Long aziendaId) {
        this.aziendaId = aziendaId;
    }

    public String getAziendaNome() {
        return aziendaNome;
    }

    public void setAziendaNome(String aziendaNome) {
        this.aziendaNome = aziendaNome;
    }
}
