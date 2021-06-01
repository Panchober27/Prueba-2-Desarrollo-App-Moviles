package com.example.franciscoberwart;

import android.util.Log;

import java.lang.reflect.Array;
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

    // Constructor para tener los precios de todos los equipos.

    private void constructorTablaEquipos() {
        tablaEquipos.add(new Equipo("se10001", "Desc 1", 2000));
        tablaEquipos.add(new Equipo("se10002", "Desc 1", 2000));
        tablaEquipos.add(new Equipo("se10003", "Desc 1", 12300));
        tablaEquipos.add(new Equipo("se10004", "Desc 1", 45000));
        tablaEquipos.add(new Equipo("se10005", "Desc 1", 15000));
        tablaEquipos.add(new Equipo("se10006", "Desc 1", 34790));
        tablaEquipos.add(new Equipo("se10007", "Desc 1", 15000));
        tablaEquipos.add(new Equipo("se10008", "Desc 1", 34790));
        tablaEquipos.add(new Equipo("se10009", "Desc 1", 28340)); // NO Ingresados.
        tablaEquipos.add(new Equipo("se10010", "Desc 1", 35450));
        tablaEquipos.add(new Equipo("se10011", "Desc 1", 23420));
        tablaEquipos.add(new Equipo("se10012", "Desc 1", 12310));
    }


    public static Equipo findEquipBySerie(String serie) {
        for (Equipo e : tablaEquipos) {
            if (e.getSerie().equals(serie)) {
                return e;
            }
        }
        return null;
    }


    // Metodo para agregar un usuario a tabla/lista
    public static boolean insertUser(Usuario user) {
        return tablaUsuarios.add(user);
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


    // Carga automatica de equipos.

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


    // Metodo para eliminar un equipo de un usaurio.
    public static void deleteEquipo(String serie) {
        ArrayList<Equipo> list = tablaEquipos;
        Equipo equipo = null;
        for (Equipo e : list) {
            if (e.getSerie().equals(serie)) { // Se encontro el equipo a borrar.
                equipo = e;
            }
        }
        if (equipo != null) {
            tablaEquipos.remove(equipo);
        } else {
            Log.d("ERRORES_", "deleteEquipo: Fallo el metodo deleteEquipo()  " + equipo.getSerie());
        }
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


    // Metodo para cargar los datos iniciales de la aplicación. Usuarios y Equipos :)
    public static void chargeSistem() {
        BaseDatos.insertUser(new Usuario("pancho", "Francisco", "Berwart", "Informatica"));
        BaseDatos.insertUser(new Usuario("mono", "Daniel", "Muñoz", "Medicina"));
        BaseDatos.insertUser(new Usuario("apu", "Benjamin", "Meneses", "Informatica"));
        BaseDatos.insertUser(new Usuario("alvaro", "Alvaro", "Guerrero", "Comunicaiones"));

        // BaseDatos. insertar Equipo a usuario.
        BaseDatos.addEquipToUser("pancho", new Equipo("se10001", "Desc 1", 2000));
        BaseDatos.addEquipToUser("pancho", new Equipo("se10002", "Desc 2", 2000));
        // 2 EQUIPOS AL USUARIO PANCHO.

        BaseDatos.addEquipToUser("mono", new Equipo("se10003", "Desc 3", 12300));
        BaseDatos.addEquipToUser("mono", new Equipo("se10004", "Desc 4", 45000));
        // 2 EQUIPOS AL USUARIO MONO.

        BaseDatos.addEquipToUser("apu", new Equipo("se10005", "Desc x", 15000));
        BaseDatos.addEquipToUser("apu", new Equipo("se10006", "Desc x", 34790));
        // 2 EQUIPOS AL USUARIO APU.

        BaseDatos.addEquipToUser("alvaro", new Equipo("se10007", "Desc x", 15000));
        BaseDatos.addEquipToUser("alvaro", new Equipo("se10008", "Desc x", 34790));
        // 2 EQUIPOS AL USUARIO ALVARO.
    }


}
