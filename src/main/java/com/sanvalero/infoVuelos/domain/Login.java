package com.sanvalero.infoVuelos.domain;

/**
 * Creado por @author: Javier
 * el 03/12/2020
 */

public class Login {

    private String usuario;
    private String password;

    //Constructor
    public Login(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
