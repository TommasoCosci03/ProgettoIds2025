import it.unicam.cs.ids25.model.Prodotti.Categoria;
import it.unicam.cs.ids25.model.Prodotti.Certificazioni;
import it.unicam.cs.ids25.model.Marketplace;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;
import it.unicam.cs.ids25.model.Utenti.*;

import java.util.ArrayList;

public class Application {
    public static void main(String[] args) {
      /*  Marketplace marketplace = Marketplace.getIstanzaMarketplace();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Benvenuto, scrivi il nome dell'azienda: ");
        String nome = scanner.nextLine();
        System.out.println("Scrivi l'indirizzo della sede: ");
        String sede = scanner.nextLine();

        Produttore p = new Produttore(nome, sede);
        int input;

        do{
        System.out.println("\n\n1 - Crea Prodotto \n2 - Vedi prodotti caricati \n3 - Esci");

            input = scanner.nextInt();
            scanner.nextLine();

            switch (input) {
                case 1:
                    p.creaProdotto();
                    break;
                case 2:
                    p.vediProdottiCaricati();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Selezione non valida");
                    break;
            }
        }while(input != 3);


        Curatore curatore= Curatore.getInstanzaCuratore();
        curatore.stampaRichieste();
        marketplace.stampaProdottiInVendita();

        curatore.stampaRichieste();
        curatore.approvaProdotto(1);
        curatore.approvaProdotto(2);

        curatore.stampaRichieste();
        marketplace.stampaProdottiInVendita();*/

        //prova creazione prodotti e approvazione curatore e materiePrime
        Marketplace marketplace = Marketplace.getIstanzaMarketplace();
        Produttore produttore1 = new Produttore("produttore","milano");
        ArrayList<Certificazioni> certificazioni1 = new ArrayList<>();
        certificazioni1.add(Certificazioni.DOC);
        produttore1.creaProdottoAzienda("mele","buone",10,2,Categoria.Frutta,certificazioni1 );
        produttore1.creaProdottoAzienda("pere","buone",10,2,Categoria.Frutta,certificazioni1 );

        Curatore curatore= Curatore.getInstanzaCuratore();
        curatore.stampaRichieste();
        marketplace.stampaProdottiInVendita();

        curatore.stampaRichieste();
        curatore.approvaProdotto(1);
        curatore.approvaProdotto(2);

        curatore.stampaRichieste();
        marketplace.stampaProdottiInVendita();

        Trasformatore trasformatore = new Trasformatore("trasformatore","milano");
        trasformatore.setMateriePrime("mele");
        trasformatore.creaProdottoAzienda("marmellata","buone",10,2,Categoria.Frutta,certificazioni1);

        Distributore distributore = new Distributore("distributore","milano");
        Prodotto prodotto1 = distributore.getProdottoMarketplace(1);
        Prodotto prodotto2 = distributore.getProdottoMarketplace(2);
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        prodotti.add(prodotto1);
        prodotti.add(prodotto2);
        distributore.setProdotti(prodotti);
        System.out.println(prodotti.size());
        distributore.creaProdottoAzienda("pacchetto","buone",10,2,Categoria.Frutta, certificazioni1 );

        curatore.stampaRichieste();
        curatore.approvaProdotto(3);
        curatore.approvaProdotto(4);
        marketplace.getProdotto(3);
        System.out.println(marketplace.getMateriePrime(3));


        curatore.stampaRichieste();
        marketplace.stampaProdottiInVendita();

        //controllo creazione pacchetto
        produttore1.creaProdottoAzienda("banane","buone",10,2,Categoria.Frutta,certificazioni1 );
        produttore1.creaProdottoAzienda("arance","buone",10,2,Categoria.Frutta,certificazioni1 );

        curatore.approvaProdotto(5);
        curatore.approvaProdotto(6);
        marketplace.stampaProdottiInVendita();

        ArrayList<Prodotto> prodotti2 = new ArrayList<>();
        prodotti2.add(marketplace.getProdotto(5));
        prodotti2.add(marketplace.getProdotto(6));

        distributore.setProdotti(prodotti2);
        distributore.creaProdottoAzienda("pacchettoBanane","buone",10,2,Categoria.Pacchetto,certificazioni1 );

        curatore.approvaProdotto(7);

        marketplace.stampaProdottiInVendita();

        marketplace.getProdottiPacchetto(4);
        marketplace.getProdottiPacchetto(7);

        //test errore lista vuota creazione pacchetto
        /*ArrayList<Prodotto> prodotti3 = new ArrayList<>();
        prodotti3.add(marketplace.getProdotto(5));
        distributore.setProdotti(prodotti3);
        distributore.creaProdottoAzienda("pacchettoErrore","buone",10,2,Categoria.Pacchetto,certificazioni1 );

        //campo nulla pacchettoSingolo
        produttore1.creaProdottoAzienda("carne","buone",10,2,null,certificazioni1);*/

        //TODO SCENARIO ACQUISTO
        Acquirente acquirente = new Acquirente("Cosci");
        acquirente.aggiungiAlCarrello(prodotto2,2);
        acquirente.aggiungiAlCarrello(prodotto1,1);

        Acquirente acquirente1 = new Acquirente("Elia");
        acquirente1.aggiungiAlCarrello(prodotto2,1);

        System.out.println("Cosci = " + acquirente.getCarrello().prezzoTotale());
        System.out.println("Elia = " + acquirente1.getCarrello().prezzoTotale());

        /*//togli dal carrello
        acquirente1.togliDalCarrello(prodotto2);
        acquirente.togliDalCarrello(prodotto1);
        System.out.println("Cosci = " + acquirente.getCarrello().prezzoTotale());
        System.out.println("Elia = " + acquirente1.getCarrello().prezzoTotale());

        //cancella carrello
        acquirente.cancellaCarrello();
        acquirente1.cancellaCarrello();
        System.out.println("Cosci = " + acquirente.getCarrello().prezzoTotale());
        System.out.println("Elia = " + acquirente1.getCarrello().prezzoTotale());*/

        //prova acquisto FUNZIONA GODOOOOO
        acquirente.acquista();
        marketplace.stampaProdottiInVendita();

    }

}