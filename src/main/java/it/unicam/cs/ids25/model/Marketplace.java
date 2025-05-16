package it.unicam.cs.ids25.model;

import it.unicam.cs.ids25.model.Prodotti.PacchettoDiProdotti;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import it.unicam.cs.ids25.model.Prodotti.ProdottoTrasformato;

import java.util.ArrayList;

public class Marketplace {

    private static Marketplace marketplace; // singleton
    private ArrayList<Prodotto> prodottiInVendita; // contenuto



    private Marketplace() {
        prodottiInVendita = new ArrayList<Prodotto>();
    }

    public static Marketplace getIstanzaMarketplace() {
        if (marketplace == null) {
            marketplace = new Marketplace();
        }
        return marketplace;
    }

    public void addProdottoToMarket(Prodotto prodotto) {
        prodottiInVendita.add(prodotto);
    }

    public Prodotto getProdotto(int id) {
        for (Prodotto prodotto : prodottiInVendita) {
            if (prodotto.getId() == id) {
               return prodotto;
            }
        }
        throw new IllegalStateException("Indice del prodotto da inserire nel pacchetto non presente nel marketplace");
    }


    public String getMateriePrime(int id) {
        ProdottoTrasformato prodottoTrasformato = null;
        for (Prodotto prodotto : prodottiInVendita) {
            if (prodotto.getId() == id) {
                if (prodotto instanceof ProdottoTrasformato) {
                    prodottoTrasformato = (ProdottoTrasformato) prodotto;
                    break;
                }
                else { throw new IllegalStateException("il prodotto non è un prodotto trasformato");}
            }
        }
        assert prodottoTrasformato != null;
        return prodottoTrasformato.getMateriaPrima();
    }




    public void getProdottiPacchetto(int id) {
        PacchettoDiProdotti pacchetto = null;
        for (Prodotto prodotto : prodottiInVendita) {
            if (prodotto.getId() == id) {
                if (prodotto instanceof PacchettoDiProdotti) {
                    pacchetto = (PacchettoDiProdotti) prodotto;
                    break;
                }
                else { throw new IllegalStateException("il prodotto non è un prodotto trasformato");}
            }
        }
        assert pacchetto != null;
        pacchetto.getProd();
    }

    public void stampaProdottiInVendita() {
        if (prodottiInVendita.isEmpty()) {
            System.out.println("Nessun prodotto attualmente in vendita.");
            return;
        }

        System.out.println("Prodotti attualmente in vendita:");
        for (Prodotto p : prodottiInVendita) {
            System.out.println("ID: " + p.getId()
                    + ", Nome: " + p.getNome()
                    + ", Descrizione: " + p.getDescrizione()
                    + ", Prezzo: " + p.getPrezzo()
                    + ", Quantità: " + p.getQuantita()
                    + ", Categoria: " + p.getCategoria());
        }
    }



    public ArrayList<Prodotto> getProdottiInVendita() {
        return prodottiInVendita;
    }



    public Prodotto get(int idProdotto) {
        Prodotto prodotto = null;
        for (Prodotto p : prodottiInVendita) {
            if (p.getId() == idProdotto) {
                prodotto= p;
            }
        }
        return prodotto;
    }

    public boolean controllaQuantita(Prodotto prodotto, int quantita) {
        if(prodotto.getQuantita() >= quantita) {
            return true;
        }
        else {
            return false;
        }
    }

    public void aggiornaQuantita(Carrello carrello) {
        for (Prodotto p : carrello.getProdottiDaAcquistare().keySet()) {
            marketplace.getProdotto(p.getId()).setQuantita(p.getQuantita() - carrello.getProdottiDaAcquistare().get(p));
        }
    }
}
