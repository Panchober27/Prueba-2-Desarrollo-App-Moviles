package com.example.franciscoberwart;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable {

    private String usuario, nombre, apellido, departamento;
    private ArrayList<Equipo> listaEquipos = new ArrayList<>();

    public Usuario(String usuario, String nombre, String apellido, String departamento) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.departamento = departamento;

        // Cuando agrego esto al objeto falla lo que llevo construido :(
        //listaEquipos.add(new Equipo("111", "Nose 1", 2000));
        //listaEquipos.add(new Equipo("222", "Nose 2", 2500));

    }

    // Metodo que retorna el listado de Equipos de un Usuario.
    public ArrayList<Equipo> getListaEquipos() {
        ArrayList<Equipo> equipos = listaEquipos;
        return equipos;
    }

    // Metodo para retornar un equipo por su serie.
    // ESTE METODO DEBE SER PROGRAMADO CORRECTAMENTE!!!!
    public Equipo findEquipoBySerie(String serie) {
        // Recibo la serie y busco en la lista de equipos...
        for (Equipo e : listaEquipos) {
            if (e.getSerie().equals(serie)) { // Equivalencia de series en un Equipo.
                return e;
            }
        }
        return null;
    }

    // Metodo para agregar equipos al usuario.
    public void addEquipToUser(Equipo equipo) {
        listaEquipos.add(equipo);
    } // FALTA "MARCAR" AL USUARIO // Que reciba parametro de usuario:


    @Override
    public String toString() {
        return nombre + " " + apellido + "  /dpto: " + departamento;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
