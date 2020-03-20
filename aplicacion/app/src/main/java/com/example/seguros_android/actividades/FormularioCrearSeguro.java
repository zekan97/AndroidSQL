package com.example.seguros_android.actividades;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.seguros_android.R;
import com.example.seguros_android.datos.MyOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class FormularioCrearSeguro extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    Button btnAdd;
    EditText inputLabel;
    SQLiteDatabase db;
    int seleccionadoValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_crear_seguro);

        spinner = findViewById(R.id.spinnerTipoSeg);
        btnAdd = findViewById(R.id.btn_add);
        inputLabel = findViewById(R.id.dni_cli_add_seguro);

        MyOpenHelper dbHelper = new MyOpenHelper(this);
        db = dbHelper.getWritableDatabase();

        List<String> labels = cargarDatos();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                SharedPreferences prefs =
                        getSharedPreferences("CuentaUsuario", Context.MODE_PRIVATE);

                String DNI_vendedor = prefs.getString("dni_empleado", "error. No se ha podido recuperar");

                if(inputLabel.getText().length()!=0) {
                        ContentValues cv = new ContentValues();
                        cv.put("DNI_cliente", inputLabel.getText().toString());
                        cv.put("DNI_vendedor", DNI_vendedor);
                        cv.put("id_poliza", seleccionadoValor+1);
                        db.insert("union_seguro_vendedor", null, cv);
                        Toast.makeText(view.getContext(), "Poliza añadida: Vendedor: " + DNI_vendedor + " ID: " + seleccionadoValor, Toast.LENGTH_SHORT).show();

                        moverPantalla();
                } else {
                    Toast.makeText(view.getContext(), "Faltan campos por rellenar", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     *  Método que lleva al empleado a la pantalla anterior una vez añadido el usuario nuevo
     *
     */
    private void moverPantalla() {
        Intent intent = new Intent(FormularioCrearSeguro.this, AdministrarSeguro.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String label = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Has seleccionado: " + label,
                Toast.LENGTH_LONG).show();
        seleccionadoValor = parent.getSelectedItemPosition();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private List<String> cargarDatos() {
        List<String> list = new ArrayList<String>();

        String[] args = new String[] {};
        Cursor c = db.rawQuery(" SELECT * FROM seguros", args);
        // Bucle que recorre todas las filas y crea una lista de Strings

        if (c.moveToFirst()) {
            do {
                list.add(c.getString(1));//Añade a la lista el valor de la segunda columna
            } while (c.moveToNext());
        }

        c.close();
        return list;

    }
}