package com.example.franciscoberwart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MantenedorEquipos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantenedor_equipos);

        chargeBundles();

    }


    // Metodo para cargar los datos(atributos y objetos del usuario.)
    private void chargeBundles() {
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) { // Viene algo....
            Usuario user = (Usuario) bundle.getSerializable("usuario");

            Log.d("TAG_", "Usuario user: " + user.getUsuario());
            Log.d("TAG_", "Usuario nombre: " + user.getNombre());
            Log.d("TAG_", "Usuario apellido: " + user.getApellido());
            Log.d("TAG_", "Usuario depto: " + user.getDepartamento());

        }

    }


}