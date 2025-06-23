package it.unicam.cs.ids25.model.Prodotti;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.unicam.cs.ids25.model.Prodotti.Enum.Categoria;
import it.unicam.cs.ids25.model.Prodotti.Enum.Certificazioni;
import it.unicam.cs.ids25.model.Utenti.Azienda;
import it.unicam.cs.ids25.model.Utenti.Curatore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo_prodotto")
public abstract class  Prodotto  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String descrizione;
    private double prezzo;
    private int quantita;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    @ElementCollection(targetClass = Certificazioni.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "prodotto_certificazioni", joinColumns = @JoinColumn(name = "prodotto_id"))
    @Column(name = "certificazione")
    private List<Certificazioni> certificazioni;
    private boolean approvato;
    @ManyToOne
    @JoinColumn(name = "azienda_id")
    @JsonIgnoreProperties("prodotti")
    private Azienda azienda;

    public Prodotto() {}

    public Prodotto(String nome, String descrizione, double prezzo,
                    int quantita, Categoria categoria, Azienda azienda, List<Certificazioni> certificazioni) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.quantita = quantita;
        this.categoria = categoria;
        this.approvato = false;
        this.azienda = azienda;
        this.certificazioni = certificazioni;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Certificazioni> getCertificazioni() {
        return certificazioni;
    }

    public void setCertificazioni(ArrayList<Certificazioni> certificazioni) {
        this.certificazioni = certificazioni;
    }

    public boolean isApprovato() {
        return approvato;
    }

    public void setApprovato() {
        this.approvato = true;
    }

    public Azienda getAzienda() {
        return azienda;
    }

    public void setAzienda(Azienda azienda) {
        this.azienda = azienda;
    }
}
