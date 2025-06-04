package it.unicam.cs.ids25.model.Dto;

public class ProdottoOrdineDTO {
    private long idAcquirente;
    private long idProdotto;
    private int quantita;

    public long getIdAcquirente() {
        return idAcquirente;
    }

    public long getIdProdotto() {
        return idProdotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setIdAcquirente(long idAcquirente) {
        this.idAcquirente = idAcquirente;
    }

    public void setIdProdotto(long idProdotto) {
        this.idProdotto = idProdotto;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}
