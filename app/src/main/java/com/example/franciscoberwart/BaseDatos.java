package com.example.franciscoberwart;

import java.util.ArrayList;

/**
 * ArrayList Usuario -> Con todos los usaurios del sistema
 * ArrayList Equipo -> Con todos los equipos disponibles o que estan a cargo de un usuario.
 */

public class BaseDatos {

    private static ArrayList<Usuario> tablaUsuarios = new ArrayList<>();
    private static ArrayList<Equipo> tablaEquipos = new ArrayList<>();


    //Metodo constructor para que la clase no se pueda instanciar.
    private BaseDatos() {}


    // Metodo para agregar un usuario a tabla/lista
    public static boolean insertUser(Usuario user) {
        tablaUsuarios.add(user);
        return true;
    }

    // Metodo para asignar Equipos a un usuario.
    // Recibe como parametro un usuario y el equipo nuevo.
    public static boolean addEquipToUser(String user, Equipo equipo) { // user-> atributo usuario de un Usuario.xD
        for (Usuario us : tablaUsuarios) {
            if (us.getUsuario().equals(user)) {
                us.addEquipToUser(equipo); // FALTA PARAMETRO: a que usuario se asigna el equipo?. / o esta resuelto eso?.
            } // CREO QUE ESTA LISTO EL TEMA DE VER A QUE USUARIO :)
        }

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
