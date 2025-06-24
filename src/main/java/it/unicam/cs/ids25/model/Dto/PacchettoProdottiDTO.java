package it.unicam.cs.ids25.model.Dto;

import it.unicam.cs.ids25.model.Prodotti.Prodotto;

import java.util.List;

/**
 * La classe PacchettoProdottiDto è un Data Trasfer Object per la creazione di un oggetto pacchetto di  prodotti
 * che estende {@link ProdottoSingoloDTO}, contiente una lista di Long per tenere traccia degli id dei prodotti
 * all'interno del pacchetto
 */
public class PacchettoProdottiDTO extends ProdottoSingoloDTO{

    List<Long> pacchetto;

    public void setPacchetto(List<Long> pacchetto) {
        this.pacchetto = pacchetto;
    }

    public List<Long> getPacchetto() {
        return pacchetto;
    }


}
