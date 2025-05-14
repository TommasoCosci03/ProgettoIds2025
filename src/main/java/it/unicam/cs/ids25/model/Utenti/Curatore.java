package it.unicam.cs.ids25.model.Utenti;

import it.unicam.cs.ids25.model.Marketplace;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;

import java.util.ArrayList;

public class Curatore {
    private static Curatore curatore;
    private String nome;
    private ArrayList<Prodotto> richieste;
    private final Marketplace marketplace;

    private Curatore(){
        this.nome = "Curatore";
        this.richieste = new ArrayList<>();
        marketplace = Marketplace.getIstanzaMarketplace();
    }


    public void approvaProdotto(int idprodotto){
        if(!this.richieste.isEmpty()){
            for (Prodotto prodotto : richieste) {
            if (prodotto.getId() == idprodotto) {
                prodotto.setApprovato(true);
                richieste.remove(prodotto);
                marketplace.addProdottoToMarket(prodotto);
                break;
            }else {
                System.out.println("Prodotto non presente nelle richieste.");
                 }
             }
        }
    }



    public void dimensione(){
        System.out.println(this.richieste.size());
    }


    public static Curatore getInstanzaCuratore() {
        if (curatore == null) {
            curatore = new Curatore(); // oppure recupera da config
        }
        return curatore;
    }



    public ArrayList<Prodotto> getRichieste() {
        return richieste;
    }

    public void addRichiesta(Prodotto prodottorichiesta) {
        this.richieste.add(prodottorichiesta);
    }

    public void stampaRichieste() {
        if (richieste.isEmpty()) {
            System.out.println("Nessuna richiesta in attesa di approvazione.");
            return;
        }

        System.out.println("Prodotti in attesa di approvazione:");
        for (Prodotto p : richieste) {
            System.out.println("ID: " + p.getId()
                    + ", Nome: " + p.getNome()
                    + ", Descrizione: " + p.getDescrizione()
                    + ", Prezzo: " + p.getPrezzo()
                    + ", Quantità: " + p.getQuantita());
        }
    }


}
