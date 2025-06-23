package it.unicam.cs.ids25.model.Autenticazione;


import jakarta.persistence.*;

/**
 * La classe Utente è un classe astratta che rappresenta un utente registrato nel sistema.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_utente")
public abstract class Utente{

    @Column(unique = true, nullable = false)
    private String username;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(nullable = false)
    private String password;


    /**
     * Costruttore che crea un utente con un username e una password.
     * @param username
     * @param password
     */
    public Utente(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Costruttore vuoto per Springboot.
     */
    public Utente() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
