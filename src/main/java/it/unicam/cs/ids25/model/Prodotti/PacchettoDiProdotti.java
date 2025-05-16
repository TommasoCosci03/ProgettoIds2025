package it.unicam.cs.ids25.model.Prodotti;

import it.unicam.cs.ids25.model.Utenti.Azienda;

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

    public ArrayList<Prodotto> getPacchetto() {
        return pacchetto;
    }

    public void getProd() {
        for (Prodotto p : pacchetto) {
         System.out.println(p.getNome() + " " + p.getDescrizione());
        }
    }

}