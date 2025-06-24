package it.unicam.cs.ids25.model.Dto;

/**
 * La classe ProdottoOrdineDto è un Data Trasfer Object per la creazione di un Ordine
 */
public class ProdottoOrdineDTO {

    private long idProdotto;
    private int quantita;

    public long getIdProdotto() {
        return idProdotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setIdProdotto(long idProdotto) {
        this.idProdotto = idProdotto;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}
