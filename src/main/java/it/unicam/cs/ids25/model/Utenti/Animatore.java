package it.unicam.cs.ids25.model.Utenti;

import it.unicam.cs.ids25.model.Evento;

import java.util.ArrayList;
import java.util.Scanner;

public class Animatore {
    private long id;
    private String nome;
    private ArrayList<Evento> eventiCreati;

    public long getId(){return id;}

    public void setId(long id) {this.id = id;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public ArrayList<Evento> getEventiCreati() {return eventiCreati;}

    public void creaEvento(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Creazione di un nuovo evento:");
        System.out.print("Nome evento: ");
        String nome = scanner.nextLine();

        System.out.print("Descrizione: ");
        String descrizione = scanner.nextLine();

        System.out.print("luogo: ");
        String luogo = scanner.nextLine();

        System.out.print("Data: ");
        String dataEvento = scanner.nextLine();

        Evento evento = new Evento(nome, descrizione, luogo, dataEvento, this);

        eventiCreati.add(evento);

    }

    public void eliminaEvento(Evento evento){
        eventiCreati.remove(evento);
    }
}
