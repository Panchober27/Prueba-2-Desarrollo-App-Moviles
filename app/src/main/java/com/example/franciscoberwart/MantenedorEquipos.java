package com.example.franciscoberwart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MantenedorEquipos extends AppCompatActivity {

    private TextView tvNomUser, tvDeptUser, tvValorTotal, tvTipoEquipo, tvValorEquipo;
    private Button btnAddEquip, btnDeleteEquip, btnVolver;
    private AutoCompleteTextView autoSerieEquipo;
    private ListView listaEquipos;
    private ArrayAdapter<Equipo> adapterEquipos;

    private String[] listSerie = new String[12];// para colocar en el autoCompleteTextView
    private ArrayAdapter<String> adapterSeries;

    // Variable para almacenar y utilizar el atributo Equipo-> serie.
    // para poder usarla en distintos metodos.
    private String idequipo;

    // Variable del usaurio. que se cargara con el bundle para poder utilizarla en mas de una funcion
    private Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantenedor_equipos);

        inits();
        chargeBundles();
        chargeListEvents();
        events();

    }

    // Metodo de inicializacion de componentes.
    private void inits() {
        tvNomUser = findViewById(R.id.tvNomUser);
        tvDeptUser = findViewById(R.id.tvDeptUser);
        listaEquipos = findViewById(R.id.listEquipos);
        tvValorTotal = findViewById(R.id.tvValorTotal);
        btnAddEquip = findViewById(R.id.btnAddEquipo);
        btnDeleteEquip = findViewById(R.id.btnDeleteEquipo);
        btnVolver = findViewById(R.id.btnVolver);
        autoSerieEquipo = findViewById(R.id.autoSeries);
        tvTipoEquipo = findViewById(R.id.tvTipoEquipo);
        tvValorEquipo = findViewById(R.id.tvTipoEquipo);
        // Carga del arreglo con las series(id) de los equipos.
        listSerie = new String[]{
                "linea 1", "linea 2", "linea 3", "linea 4", "linea 5", "linea 6", "linea 7", "linea 8",
                "linea 9", "linea 10", "linea 11", "linea 12"};
        adapterSeries = new ArrayAdapter<String>(MantenedorEquipos.this, android.R.layout.simple_list_item_1, listSerie);
        autoSerieEquipo.setAdapter(adapterSeries);

    }


    // Metodo para ejecutar funciones segun boton apretado.
    private void clickButtons(View btn) {
        if (btn.getId() == R.id.btnAddEquipo) { // Boton para añadir un equipo.
            Toast.makeText(MantenedorEquipos.this, "Añadir un Equipo", Toast.LENGTH_LONG).show();
        }
        if (btn.getId() == R.id.btnDeleteEquipo) { // Boton para
            Toast.makeText(MantenedorEquipos.this, "Borrar un Equipo", Toast.LENGTH_LONG).show();
        }
        if (btn.getId() == R.id.btnVolver) { // Boton para volver a la primera activity.
            finish();
        }


    }

    // Metodo para asignar eventos  a los botones.
    private void events() {
        btnAddEquip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButtons(v);
            }
        });
        btnDeleteEquip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButtons(v);
            }
        });
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButtons(v);
            }
        });
    }


    // Metodo para cargar los datos(atributos y objetos del usuario.)
    private void chargeBundles() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) { // Viene algo....
            user = (Usuario) bundle.getSerializable("usuario");
            //Equipo equipo = user.findEquipoBySerie("111");

            //ArrayList<Equipo> listEquipos = user.getListaEquipos(); // Reemplazar por BaseDatos.getListaDeEquipos(String usuario)
            // Buscar el usuario que corresponde(el que esta instanciado ahora mismo?)


            // Se cargan los datos(atributos del usuario) a los TextView :)
            tvNomUser.setText(tvNomUser.getText() + " " + user.getNombre() + " " + user.getApellido());
            tvDeptUser.setText(tvDeptUser.getText() + " " + user.getDepartamento());

            // Cargar al ListView de equipos, el arreglo con los equipos que le pertenecen al usuario.
            adapterEquipos = new ArrayAdapter<Equipo>(MantenedorEquipos.this, android.R.layout.simple_list_item_1,
                    BaseDatos.getTablaEquipos(user.getUsuario()));
            listaEquipos.setAdapter(adapterEquipos);

            tvValorTotal.setText(tvValorTotal.getText() + String.valueOf(BaseDatos.getValorTotal(user.getUsuario())));
        }
    }

    private void chargeListEvents() {
        listaEquipos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Equipo eq = (Equipo) parent.getItemAtPosition(position);
                idequipo = eq.getSerie();
                showDialog();
                return true;
            }
        });
    }


    // Metodo para mostrar el Dialog de confirmación para eliminar un equipo.
    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MantenedorEquipos.this);
        builder.setTitle("Eliminar ?");
        builder.setMessage("equipo?")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Borrar el equipo.
                        BaseDatos.deleteEquipo(idequipo);
                        // Recargar el adapter de los equipos.
                        // Recargar el valor total de equipos a cargo del usuario.

                        // Cargar al ListView de equipos, el arreglo con los equipos que le pertenecen al usuario.
                        adapterEquipos = new ArrayAdapter<Equipo>(MantenedorEquipos.this, android.R.layout.simple_list_item_1,
                                BaseDatos.getTablaEquipos(user.getUsuario()));
                        listaEquipos.setAdapter(adapterEquipos);

                        tvValorTotal.setText(String.valueOf(BaseDatos.getValorTotal(user.getUsuario())));

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


}