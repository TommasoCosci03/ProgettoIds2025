package it.unicam.cs.ids25.model.Utenti;

import it.unicam.cs.ids25.model.Categoria;
import it.unicam.cs.ids25.model.Prodotti.Prodotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public abstract class Azienda {
    private static long contatoreID = 0;
    private long id;
    private String nome;
    private String sede;
    private ArrayList<Prodotto> prodottiCaricati;


    public Azienda(String nome, String sede) {
        this.id = ++contatoreID;
        this.nome = nome;
        this.sede = sede;
        this.prodottiCaricati = new ArrayList<>();
    }

    public long getId() {
        return id;
}

    public String getNome() {
        return nome;
    }

    public String getSede() {
        return sede;
    }

    public ArrayList<Prodotto> getProdottiCaricati() {
        return prodottiCaricati;
    }

    public void creaProdotto(){
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


        System.out.println(Arrays.toString(Categoria.values()));
        String categoria = scanner.nextLine();
        Categoria categoriaprodotto = creazioneCategoria(categoria);

        Prodotto prodotto = creaProdottoAzienda(nome, descrizione,prezzo, quantita, categoriaprodotto);
        System.out.println("Prodotto creato --> " + prodotto.getNome() + " con ID: " + prodotto.getId() +
                " e venduto dall'azienda " + prodotto.getAzienda().getNome());

        this.getProdottiCaricati().add(prodotto);
    }

    public abstract Prodotto creaProdottoAzienda(String nome, String descrizione, double prezzo, int quantita,
                                                 Categoria categoria);
    public void vediProdottiCaricati(){}

    public void eliminaProdotto(Prodotto prodotto){
        prodottiCaricati.remove(prodotto);
    }


    public Categoria creazioneCategoria(String categoria){

            switch (categoria.toLowerCase()) {
                case "carne":
                    return Categoria.Carne;
                case "pesce":
                    return Categoria.Pesce;
                case "frutta":
                    return Categoria.Frutta;
                case "verdura":
                    return Categoria.Verdura;
                case "cereali":
                    return Categoria.Cereali;
                case "legumi":
                    return Categoria.Legumi;
                    default:
                    throw new IllegalArgumentException("Categoria non valida");
            }

    }


}

