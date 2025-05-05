package progettoIDS25.src.main.java.it.unicam.cs.ids25.model;

import java.util.Scanner;

public class Produttore extends Azienda{

    public Produttore(String nome, String sede) {
        super(nome, sede);
    }

    @Override
    public void creaProdotto() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Creazione di un nuovo prodotto:");
        System.out.print("Nome prodotto: ");
        String nome = scanner.nextLine();

        System.out.print("Descrizione: ");
        String descrizione = scanner.nextLine();

        System.out.print("Prezzo: ");
        double prezzo = Double.parseDouble(scanner.nextLine());

        System.out.print("Quantita: ");
        int quantita = Integer.parseInt(scanner.nextLine());

        Prodotto prodotto = new Prodotto(nome, descrizione, prezzo, quantita, Categoria.Frutta,this);
        System.out.println("Prodotto creato --> " + prodotto.getNome() + " con ID: " + prodotto.getId() +
                " e venduto dall'azienda " + prodotto.getAzienda().getNome());

        this.getProdottiCaricati().add(prodotto);
    }

    @Override
    public void vediProdottiCaricati() {
        System.out.println("--- Lista di prodotti caricati da " + this.getNome() + " ---");

        for(Prodotto prodotto : this.getProdottiCaricati()) {
            System.out.println(prodotto.getNome() + " - Approvato : " + prodotto.isApprovato());
        }
    }
}
