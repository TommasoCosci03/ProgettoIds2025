package it.unicam.cs.ids25.model.Prodotti;

import it.unicam.cs.ids25.model.Categoria;
import it.unicam.cs.ids25.model.Certificazioni;
import it.unicam.cs.ids25.model.Utenti.Azienda;
import it.unicam.cs.ids25.model.Utenti.Curatore;

import java.util.ArrayList;





public class PacchettoDiProdotti extends Prodotto {
    private ArrayList<Prodotto> pacchetto;
    private double prezzo;



    protected PacchettoDiProdotti(String nome, String descrizione, double prezzo, int quantita,
                               Categoria categoria, Azienda azienda, ArrayList<Certificazioni> certificazioni, ArrayList<Prodotto> pacchetto) {
        super("pacchetto"+ nome, descrizione, prezzo, quantita, Categoria.Pacchetto, azienda, certificazioni);
        this.prezzo = prezzo;
        this.pacchetto = pacchetto;
    }

}