package com.example.franciscoberwart;

import android.util.Log;

import java.util.ArrayList;

/**
 * ArrayList Usuario -> Con todos los usaurios del sistema
 * ArrayList Equipo -> Con todos los equipos disponibles o que estan a cargo de un usuario.
 */

public class BaseDatos {

    private static ArrayList<Usuario> tablaUsuarios = new ArrayList<>();
    private static ArrayList<Equipo> tablaEquipos = new ArrayList<>();


    //Metodo constructor para que la clase no se pueda instanciar.
    private BaseDatos() {
    }


    // Metodo para agregar un usuario a tabla/lista
    public static boolean insertUser(Usuario user) {
        tablaUsuarios.add(user);
        return true;
    }

    // Metodo para eliminar un usuario, utilizando su atributo usuario como parametro
    // para encontrarlo en la tabla de la "base de datos".
    public static void deleteUser(String user) {
        ArrayList<Usuario> listUser = tablaUsuarios;
        Usuario us = null;
        for (Usuario u : listUser) {
            if (u.getUsuario().equals(user)) { // Se encontro un usuario.
                //tablaUsuarios.remove(u);
                us = u;
            }
        }
        if (us != null) {
            tablaUsuarios.remove(us);
        } else {
            Log.d("ERRORES_", "deleteUser: Fallo el metodo deleteUser-> " + us.getUsuario());
        }
    }


    // Metodo para asignar Equipos a un usuario.
    // Recibe como parametro un usuario y el equipo nuevo.
    public static boolean addEquipToUser(String user, Equipo equipo) { // user-> atributo usuario de un Usuario.xD
        for (Usuario us : tablaUsuarios) {
            if (us.getUsuario().equals(user)) {
                //us.addEquipToUser(equipo); // FALTA PARAMETRO: a que usuario se asigna el equipo?. / o esta resuelto eso?.
                //tablaEquipos.add(equipo);
                us.addEquipToUser(equipo);

                // Validar que el equipo no exista (segun serie.)


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


    // Metodo que retorna la lista de Equipos de un usuario.
    public static ArrayList<Equipo> getTablaEquipos(String user) {
        for (Usuario u : tablaUsuarios) {
            if (u.getUsuario().equals(user)) { // Existe un usuario con el atributo usuario(user).
                tablaEquipos = u.getListaEquipos();
                return tablaEquipos;
            }
        }
        return null;
    }


    // Metodo para obtener el valor total de equipos de un usaurio.
    public static int getValorTotal(String user) { // -> atributo: Usuario.usuario!
        // valorTotal => a la suma de todos los getValor()!!!
        int valorTotal = 0;
        for (Usuario u : tablaUsuarios) {
            if (u.getUsuario().equals(user)) {
                for (Equipo e : tablaEquipos) {
                    valorTotal += e.getValor();
                }
            }
        }
        return valorTotal;
    }


}
