package com.example.franciscoberwart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

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
            Equipo equipo = user.findEquipoBySerie("111");

            ArrayList<Equipo> listEquipos = user.getListaEquipos();

            Toast.makeText(MantenedorEquipos.this, "Lista Equipos: " + listEquipos, Toast.LENGTH_LONG).show();

            Log.d("TAG_", "Usuario user: " + user.getUsuario());
            Log.d("TAG_", "Usuario nombre: " + user.getNombre());
            Log.d("TAG_", "Usuario apellido: " + user.getApellido());
            Log.d("TAG_", "Usuario depto: " + user.getDepartamento());
            Log.d("TAG_", "EQUIPO 1 serie: " + equipo.getSerie());
            Log.d("TAG_", "EQUIPO 1 descripcion: " + equipo.getDescripcion());
            Log.d("TAG_", "EQUIPO 1 valor: " + equipo.getValor());

            Log.d("TAG_", "");

        }

    }


}