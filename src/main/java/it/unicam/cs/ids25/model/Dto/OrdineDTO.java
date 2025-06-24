package it.unicam.cs.ids25.model.Dto;

import it.unicam.cs.ids25.model.Utenti.Acquirente;

/**
 * La classe OrdineDto è un Data Trasfer Object per la creazione di un oggetto ordine
 */
public class OrdineDTO {

    private String indirizzo;

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
}
