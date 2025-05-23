package it.unicam.cs.ids25.model.Dto;

import it.unicam.cs.ids25.model.Prodotti.Prodotto;

import java.util.List;

public class PacchettoProdottiDTO extends ProdottoSingoloDTO{

    List<Long> pacchetto;

    public void setPacchetto(List<Long> pacchetto) {
        this.pacchetto = pacchetto;
    }

    public List<Long> getPacchetto() {
        return pacchetto;
    }


}
