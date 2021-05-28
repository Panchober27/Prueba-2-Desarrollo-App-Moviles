package com.example.franciscoberwart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MantenedorEquipos extends AppCompatActivity {

    private TextView tvNomUser, tvDeptUser, valorTotal;
    private ListView listaEquipos;
    private ArrayAdapter<Equipo> adapterEquipos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantenedor_equipos);

        inits();
        chargeBundles();
        chargeListEvents();

    }

    // Metodo de inicializacion de componentes.
    private void inits() {
        tvNomUser = findViewById(R.id.tvNomUser);
        tvDeptUser = findViewById(R.id.tvDeptUser);
        listaEquipos = findViewById(R.id.listEquipos);
        valorTotal = findViewById(R.id.tvValorTotal);
    }


    // Metodo para cargar los datos(atributos y objetos del usuario.)
    private void chargeBundles() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) { // Viene algo....
            Usuario user = (Usuario) bundle.getSerializable("usuario");
            //Equipo equipo = user.findEquipoBySerie("111");

            //ArrayList<Equipo> listEquipos = user.getListaEquipos(); // Reemplazar por BaseDatos.getListaDeEquipos(String usuario)
            // Buscar el usuario que corresponde(el que esta instanciado ahora mismo?)


            ArrayList<Equipo> listEquipos = BaseDatos.getTablaEquipos(user.getUsuario()); // Se obtiene la lista de equipos de un usuario.

            // Se cargan los datos(atributos del usuario) a los TextView :)
            tvNomUser.setText(tvNomUser.getText() + " " + user.getNombre() + " " + user.getApellido());
            tvDeptUser.setText(tvDeptUser.getText() + " " + user.getDepartamento());

            // Cargar al ListView de equipos, el arreglo con los equipos que le pertenecen al usuario.
            adapterEquipos = new ArrayAdapter<Equipo>(MantenedorEquipos.this, android.R.layout.simple_list_item_1,
                    listEquipos);
            listaEquipos.setAdapter(adapterEquipos);

            valorTotal.setText(valorTotal.getText() + String.valueOf(BaseDatos.getValorTotal(user.getUsuario())));
        }
    }

    private void chargeListEvents() {
        listaEquipos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Equipo eq = (Equipo) parent.getItemAtPosition(position);
                Toast.makeText(MantenedorEquipos.this, "Eliminar Equipo: " + eq.getSerie() + " " + eq.getDescripcion() + "?",
                        Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }


}