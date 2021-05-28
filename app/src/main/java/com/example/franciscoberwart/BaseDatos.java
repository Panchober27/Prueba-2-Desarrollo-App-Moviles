package com.example.franciscoberwart;

import java.util.ArrayList;

/**
 * ArrayList Usuario -> Con todos los usaurios del sistema
 * ArrayList Equipo -> Con todos los equipos disponibles o que estan a cargo de un usuario.
 */

public class BaseDatos {

    private static ArrayList<Usuario> tablaUsuarios = new ArrayList<>();
    private static ArrayList<Equipo> listaEquipos = new ArrayList<>();

    // Metodo para agregar un usuario a tabla/lista
    public static boolean insertUser(Usuario user) {
        tablaUsuarios.add(user);
        return true;
    }

    // Metodo para retornar los datos de los usuarios.
    public static ArrayList<Usuario> listUsers() {
        ArrayList<Usuario> listaUsers = new ArrayList<Usuario>();
        for (Usuario us : tablaUsuarios) {
            listaUsers.add(us);
        }
        return listaUsers;
    }

}
