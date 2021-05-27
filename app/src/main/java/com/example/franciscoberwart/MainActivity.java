package com.example.franciscoberwart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

        BaseDatos.insertUser(new Usuario("pancho", "Francisco", "Berwart", "Informatica"));
        BaseDatos.insertUser(new Usuario("mono", "Daniel", "Muñoz", "Medicina"));
        BaseDatos.insertUser(new Usuario("apu", "Benjamin", "Meneses", "Informatica"));


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
    }
}