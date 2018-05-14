/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mc.datos;

/**
 *
 * @author luna
 */
public class User {
    private int idUser;
    private String nickname;
    private String password;
    private String nombreCompleto;

    public User(int idUser, String nickname,
            String password, String nombreCompleto) {
        this.idUser = idUser;
        this.nickname = nickname;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    @Override
    public String toString() {
        return "User{" + "idUser=" + idUser 
                + ", nickname=" + nickname 
                + ", password=" + password 
                + ", nombreCompleto=" + nombreCompleto + '}';
    }
    
    
}
