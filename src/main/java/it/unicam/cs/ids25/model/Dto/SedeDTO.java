package it.unicam.cs.ids25.model.Dto;

public class SedeDTO {
    private String nome;
    private String indirizzo;
    private Double latitudine;
    private Double longitudine;

    public SedeDTO(String nome, String indirizzo, Double latitudine, Double longitudine) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public Double getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(Double latitudine) {
        this.latitudine = latitudine;
    }

    public Double getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(Double longitudine) {
        this.longitudine = longitudine;
    }

    // Getter e Setter
}
