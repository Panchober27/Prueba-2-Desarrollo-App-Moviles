package com.example.franciscoberwart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class
MainActivity extends AppCompatActivity {

    // Listview -> Listado de Usuario.
    private ListView listView;
    // Adapter para el listview.
    private ArrayAdapter<Usuario> adapterUsuarios;

    // Creo variable string para almacenar y utilizar el atributo Usuario-> usuario.
    // esto para poder usarla durante varios metodos. // creo que no, esto esta mal?
    private String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inits();
        chargeListEvents();
    }


    // Para mostrar el listado de los usuarios usar un ListView.


    // Metodo para inicializar los componentes.
    private void inits() {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        listView = findViewById(R.id.listView);
        chargeSistem();
        adapterUsuarios = new ArrayAdapter<Usuario>(MainActivity.this, android.R.layout.simple_list_item_1, BaseDatos.listUsers());
        listView.setAdapter(adapterUsuarios);

    }

    // Metodos para click y longClick en la lista.
    private void chargeListEvents() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Usuario us = (Usuario) parent.getItemAtPosition(position);
                Intent i = new Intent(MainActivity.this, MantenedorEquipos.class);
                i.putExtra("usuario", us);
                startActivity(i);
            }
        });
        // Ahora el longClick, depues esto me abrira una ventana de confirmacion para eliminar al usuario seleccionado!
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Usuario us = (Usuario) parent.getItemAtPosition(position);
                userid = us.getUsuario();
                //Toast.makeText(MainActivity.this, "Eliminar usuario: " + us.getUsuario() + "?", Toast.LENGTH_LONG).show();
                /**
                 * Agregar un ConfirmAlertDialog para eliminar al usuario seleccionado de la lista.
                 * crear metodo para borrar un usuario en base a su atributo usuario:!!!
                 */
                shohDialog();
                return true;
            }
        });
    }


    // Metodo para mostrar el Dialog de confirmación para eliminara un usuario.
    private void shohDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Desea eliminar a");
        builder.setMessage(userid)
                .setIcon(R.drawable.ic_denied) // Agregar esto en devDesign
                .setCancelable(false)
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Borrar al usuario.
                        BaseDatos.deleteUser(userid);
                        // Recargar el adaptador del listView.
                        // Para cargar correctamente la lista de usuarios.
                        adapterUsuarios = new ArrayAdapter<Usuario>(MainActivity.this, android.R.layout.simple_list_item_1, BaseDatos.listUsers());
                        listView.setAdapter(adapterUsuarios);
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    // Metodo para cargar datos Iniciales a los Usuarios y sus Equipos.
    private void chargeSistem() {
        BaseDatos.insertUser(new Usuario("pancho", "Francisco", "Berwart", "Informatica"));
        BaseDatos.insertUser(new Usuario("mono", "Daniel", "Muñoz", "Medicina"));
        BaseDatos.insertUser(new Usuario("apu", "Benjamin", "Meneses", "Informatica"));
        BaseDatos.insertUser(new Usuario("alvaro", "Alvaro", "Guerrero", "Comunicaiones"));

        // BaseDatos. insertar Equipo a usuario.
        BaseDatos.addEquipToUser("pancho", new Equipo("linea 1", "Nada 1", 2000));
        BaseDatos.addEquipToUser("pancho", new Equipo("linea 2", "Nada 1", 2000));
        // 2 EQUIPOS AL USUARIO PANCHO.

        BaseDatos.addEquipToUser("mono", new Equipo("linea 3", "Mono 1", 12300));
        BaseDatos.addEquipToUser("mono", new Equipo("linea 4", "Mono 2", 45000));
        // 2 EQUIPOS AL USUARIO MONO.

        BaseDatos.addEquipToUser("apu", new Equipo("linea 5", "Japus 1", 15000));
        BaseDatos.addEquipToUser("apu", new Equipo("linea 6", "Japus 2", 34790));
        // 2 EQUIPOS AL USUARIO APU.

        BaseDatos.addEquipToUser("alvaro", new Equipo("linea 7", "Alvaro 1", 15000));
        BaseDatos.addEquipToUser("alvaro", new Equipo("linea 8", "Alvaro 2", 34790));
        // 2 EQUIPOS AL USUARIO ALVARO.

    }

}