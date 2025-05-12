package it.unicam.cs.ids25.model;

import it.unicam.cs.ids25.model.Utenti.Animatore;
import it.unicam.cs.ids25.model.Utenti.Azienda;
import it.unicam.cs.ids25.model.Utenti.Utente;

import java.util.ArrayList;

public class Evento {
    private int id;
    private String nome;
    private String descrizione;
    private String luogo;
    private String dataEvento;
    private ArrayList<Azienda> invitati;
    private ArrayList<Utente> partecipanti;
    private Animatore animatore;

    public Evento(String nome, String descrizione, String luogo, String dataEvento, Animatore animatore) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.luogo = luogo;
        this.dataEvento = dataEvento;
        this.invitati = new ArrayList<>();
        this.partecipanti = new ArrayList<>();
        this.animatore = animatore;
    }

//metodi getter
    public int getId() {return id;}

    public String getNome() {return nome;}

    public String getDescrizione() {return descrizione;}

    public String getLuogo() {return luogo;}

    public String getDataEvento() {return dataEvento;}

    public ArrayList<Azienda> getInvitati() {return invitati;}

    public ArrayList<Utente> getPartecipanti() {return partecipanti;}

//metodi setter
    public void setNome(String nome) {this.nome = nome;}

    public void setDescrizione(String descrizione) {this.descrizione = descrizione;}

    public void setLuogo(String luogo) {this.luogo = luogo;}

    public void setDataEvento(String dataEvento) {this.dataEvento = dataEvento;}
}
