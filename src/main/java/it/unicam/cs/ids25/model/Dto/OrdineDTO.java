package it.unicam.cs.ids25.model.Dto;

import it.unicam.cs.ids25.model.Utenti.Acquirente;

public class OrdineDTO {
    private Long idAcquirente;
    private String indirizzo;

    public Long getAcquirente() {
        return idAcquirente;
    }

    public void setAcquirente(Long acquirente) {
        this.idAcquirente = acquirente;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
}
