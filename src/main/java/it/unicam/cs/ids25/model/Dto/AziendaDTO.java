package it.unicam.cs.ids25.model.Dto;

/**
 * La classe AziendaDto è un Data Trasfer Object per la creazione di un oggetto azienda
 */
public class AziendaDTO {
    String nome;
    String sede;
    String username;
    String password;
    String tipoAzienda;

    /*Double latitudine;
    Double longitudine;*/

    public String getNome() {
        return nome;
    }
    public String getSede() {
        return sede;
    }
    public String getTipoAzienda() {
        return tipoAzienda;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    /*public Double getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(Double longitudine) {
        this.longitudine = longitudine;
    }

    public Double getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(Double latitudine) {
        this.latitudine = latitudine;
    }*/
}