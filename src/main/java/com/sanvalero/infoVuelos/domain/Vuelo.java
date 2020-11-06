package com.sanvalero.infoVuelos.domain;

import java.util.Random;

/**
 * Creado por @author: Javier
 * el 02/11/2020
 */
public class Vuelo {

    private String codigo;
    private String origen;
    private String destino;
    private String  operadora;
    private String fecha;
    private String clase;

    private final int N = 6;

    public Vuelo(){

    }

    public Vuelo(String codigo){
        this.codigo = codigo;
    }

    public Vuelo(String codigo, String origen, String destino, String operadora, String fecha, String clase) {
        this.codigo = codigo;
        this.origen = origen;
        this.destino = destino;
        this.operadora = operadora;
        this.fecha = fecha;
        this.clase = clase;
    }

    public void setCodigo(){
        Random rd= new Random();
        String identificador = "";
        for (int i = 0; i < N; i++) {
            int letraNum = rd.nextInt(2);
            if (letraNum == 1){
                char letra = (char) (rd.nextInt(26) + 65);
                identificador += letra;
            } else{
                char num = (char) (rd.nextInt(10) + 48);
                identificador += num;
            }
        }
        this.codigo = identificador;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setOperadora(String operadora) {
        this.operadora = operadora;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public String getOperadora() {
        return operadora;
    }

    public String getFecha() {
        return fecha;
    }

    public String getClase() {
        return clase;
    }

    public String getCodigo(){
        return codigo;
    }

    public String toString(){
        return codigo + "-" + origen + "-" + destino + "-" + operadora + "-" + fecha + "-" + clase;
    }

}
