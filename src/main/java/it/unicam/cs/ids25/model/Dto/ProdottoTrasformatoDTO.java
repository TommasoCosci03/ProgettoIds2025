package it.unicam.cs.ids25.model.Dto;

import it.unicam.cs.ids25.model.Prodotti.Categoria;
import it.unicam.cs.ids25.model.Prodotti.Certificazioni;

import java.util.ArrayList;
import java.util.List;

public class ProdottoTrasformatoDTO extends ProdottoSingoloDTO{

    String materiePrime;

    public String getMateriePrime() {
        return materiePrime;
    }

    public void setMateriePrime(String materiePrime) {
        this.materiePrime = materiePrime;
    }
}
