package it.unicam.cs.ids25.model.Dto;

/**
 * La classe ProdottoTrasformatoDto è un Data Trasfer Object per la creazione di un oggetto prodotto prodotto trasformato,
 * che estende {@link ProdottoSingoloDTO}, contiente un campo {@param materiePrime} per la creazione di un prodotto trasformato
 */
public class ProdottoTrasformatoDTO extends ProdottoSingoloDTO{

    String materiePrime;

    public String getMateriePrime() {
        return materiePrime;
    }

    public void setMateriePrime(String materiePrime) {
        this.materiePrime = materiePrime;
    }
}
