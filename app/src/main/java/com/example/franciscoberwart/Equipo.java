package com.example.franciscoberwart;

public class Equipo {

    private String serie, descripcion;
    private int valor;

    public Equipo(String serie, String descripcion, int valor) {
        this.serie = serie;
        this.descripcion = descripcion;
        this.valor = valor;
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
