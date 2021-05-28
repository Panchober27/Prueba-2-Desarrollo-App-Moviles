package com.example.franciscoberwart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Listview -> Listado de Usuario.
    private ListView listView;
    // Adapter para el listview.
    private ArrayAdapter<Usuario> adapterUsuarios;


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
                Toast.makeText(MainActivity.this, "Eliminar usuario: " + us.getUsuario() + "?", Toast.LENGTH_LONG).show();
                /**
                 * Agregar un ConfirmAlertDialog para eliminar al usuario seleccionado de la lista.
                 * crear metodo para borrar un usuario en base a su atributo usuario:!!!
                 */
                return true;
            }
        });
    }


    // Metodo para cargar datos Iniciales a los Usuarios y sus Equipos.
    private void chargeSistem(){
        BaseDatos.insertUser(new Usuario("pancho", "Francisco", "Berwart", "Informatica"));
        BaseDatos.insertUser(new Usuario("mono", "Daniel", "Muñoz", "Medicina"));
        BaseDatos.insertUser(new Usuario("apu", "Benjamin", "Meneses", "Informatica"));
        BaseDatos.insertUser(new Usuario("alvaro","Alvaro","Guerrero","Comunicaiones"));

        // BaseDatos. insertar Equipo a usuario.
        BaseDatos.addEquipToUser("pancho",new Equipo("linea1","Nada 1",2000));
        BaseDatos.addEquipToUser("pancho",new Equipo("linea2","Nada 1",2000));
        // 2 EQUIPOS AL USUARIO PANCHO.

        BaseDatos.addEquipToUser("mono",new Equipo("linea3","Mono 1",12300));
        BaseDatos.addEquipToUser("mono",new Equipo("linea4","Mono 2",45000));
        // 2 EQUIPOS AL USUARIO MONO.

        BaseDatos.addEquipToUser("apu",new Equipo("linea5","Japus 1",15000));
        BaseDatos.addEquipToUser("apu",new Equipo("linea6","Japus 2",34790));
        // 2 EQUIPOS AL USUARIO APU.

        BaseDatos.addEquipToUser("alvaro",new Equipo("linea7","Alvaro 1",15000));
        BaseDatos.addEquipToUser("alvaro",new Equipo("linea8","Alvaro 2",34790));
        // 2 EQUIPOS AL USUARIO ALVARO.

    }

}