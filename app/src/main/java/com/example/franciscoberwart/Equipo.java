package com.example.franciscoberwart;

import java.io.Serializable;

public class Equipo implements Serializable {

    private String serie, descripcion;
    private int valor;

    public Equipo(String serie, String descripcion, int valor) {
        this.serie = serie;
        this.descripcion = descripcion;
        this.valor = valor;
    }


    @Override
    public String toString() {
        return serie + " " + descripcion + " " + valor;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
