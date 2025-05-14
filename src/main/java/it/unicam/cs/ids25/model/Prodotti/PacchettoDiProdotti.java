package it.unicam.cs.ids25.model.Prodotti;

import it.unicam.cs.ids25.model.Categoria;
import it.unicam.cs.ids25.model.Utenti.Azienda;
import it.unicam.cs.ids25.model.Utenti.Curatore;

import java.util.ArrayList;





public class PacchettoDiProdotti extends Prodotto {
    private ArrayList<Prodotto> pacchetto;
    private static int idPacchetto = 0;
    private Curatore curatore = Curatore.getInstanzaCuratore();



    public PacchettoDiProdotti(String nome, String descrizione, double prezzo, int quantita,
                               Categoria categoria, Azienda azienda, ArrayList<Prodotto> pacchetto) {
        super("pacchetto"+ nome, descrizione, prezzo, quantita, categoria, azienda);

        if (pacchetto == null || pacchetto.size() < 2) {
            throw new IllegalArgumentException("Un pacchetto deve contenere almeno due prodotti.");
        }
        this.pacchetto = pacchetto;
    }


    public void add(Prodotto prodotto) {
        pacchetto.add(prodotto);
    }

    public void nuovoPacchetto() {
        idPacchetto++;
        pacchetto = new ArrayList<>();
    }

    public int getIdPacchetto() {
        return idPacchetto;
    }

    public ArrayList<Prodotto> getPacchetto() {
        return pacchetto;
    }

    public void setPacchetto(ArrayList<Prodotto> pacchetto) {
        this.pacchetto = pacchetto;
    }

    public static void setIdPacchetto(int idPacchetto) {
        PacchettoDiProdotti.idPacchetto = idPacchetto;
    }

    public Curatore getCuratore() {
        return curatore;
    }

    public void setCuratore(Curatore curatore) {
        this.curatore = curatore;
    }
}