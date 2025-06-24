package it.unicam.cs.ids25.model.Dto;

/**
 *  La classe AnimatoreDto è un Data Trasfer Object per la creazione di un oggetto animatore
 */
public class AnimatoreDTO {
     String nome;
     String username;
     String password;

     public String getNome() {
        return nome;
     }
     public void setNome(String nome) {
         this.nome = nome;
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
