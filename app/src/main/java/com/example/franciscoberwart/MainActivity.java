package com.example.franciscoberwart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    // Listview -> Listado de Usuarios.
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inits();
    }


    // Para mostrar el listado de los usuarios usar un ListView.


    // Metodo para inicializar los componentes.
    private void inits(){
        listView = findViewById(R.id.listView);


    }
}