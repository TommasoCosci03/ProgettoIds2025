package it.unicam.cs.ids25.model.Dto;

public class AziendaDTO {
    String nome;
    String sede;
    String username;
    String password;
    String tipoAzienda;

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
}