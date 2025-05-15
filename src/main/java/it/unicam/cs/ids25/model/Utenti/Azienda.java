package it.unicam.cs.ids25.model.Utenti;

import it.unicam.cs.ids25.model.Categoria;
import it.unicam.cs.ids25.model.Certificazioni;
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

   /* public void creaProdotto(){
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

        System.out.println("inserisci il numero della categoria: ");
        int i=1;
        for (Categoria cat : Categoria.values()) {
            System.out.println(i+": "+cat.name());
            i++;
        }
        int numerocategoria =  Integer.parseInt(scanner.nextLine());
        Categoria categoriaprodotto = creazioneCategoria(numerocategoria);

        Prodotto prodotto = creaProdottoAzienda(nome, descrizione,prezzo, quantita, categoriaprodotto);
        System.out.println("Prodotto creato --> " + prodotto.getNome() + " con ID: " + prodotto.getId() +
                " e venduto dall'azienda " + prodotto.getAzienda().getNome());

        this.getProdottiCaricati().add(prodotto);
    }*/

    public abstract Prodotto creaProdottoAzienda(String nome, String descrizione, double prezzo, int quantita,
                                                 Categoria categoria, ArrayList<Certificazioni> certificazioni);
    public void vediProdottiCaricati(){}

    public void eliminaProdotto(Prodotto prodotto){
        prodottiCaricati.remove(prodotto);
    }


    public Categoria creazioneCategoria(int categoria){

        return switch (categoria) {
            case 1 -> Categoria.Carne;
            case 2 -> Categoria.Pesce;
            case 3 -> Categoria.Frutta;
            case 4 -> Categoria.Verdura;
            case 5 -> Categoria.Cereali;
            case 6 -> Categoria.Legumi;
            case 7 -> Categoria.Pacchetto;
            default -> throw new IllegalArgumentException("Categoria non valida");
        };

    }


}

