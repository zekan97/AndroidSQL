package com.example.seguros_android.actividades;

import androidx.appcompat.app.AppCompatActivity;
import com.example.seguros_android.R;
import com.example.seguros_android.datos.MyOpenHelper;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FormularioCrearCliente extends AppCompatActivity {

    SQLiteDatabase db;
    EditText nombre_cliente;
    EditText apellido;
    EditText dni;
    EditText telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_crear_cliente);

        MyOpenHelper dbHelper = new MyOpenHelper(this);
        db = dbHelper.getWritableDatabase();
    }

    /**
     *  Metodo que inserta un cliente
     * @param view
     */

    public void onClick(View view) {
        nombre_cliente = findViewById(R.id.name_cli2);
        apellido = findViewById(R.id.ape_cli);
        dni = findViewById(R.id.dni_cli);
        telefono = findViewById(R.id.tel_cli);

        if(nombre_cliente.getText().length()!=0 || apellido.getText().length()!=0
                || telefono.getText().length()!=0  || dni.getText().length()!=0) {
                if(isNumeric(telefono.getText().toString())) {
                    ContentValues cv = new ContentValues();
                    cv.put("DNI", dni.getText().toString());
                    cv.put("nombre", nombre_cliente.getText().toString());
                    cv.put("apellidos", apellido.getText().toString());
                    cv.put("activo", 1);
                    cv.put("telefono", telefono.getText().toString());
                    db.insert("clientes", null, cv);
                    Toast.makeText(view.getContext(), "Cliente añadido", Toast.LENGTH_SHORT).show();
                    moverPantalla();

                } else {
                    Toast.makeText(view.getContext(), "El telefono no es un numero", Toast.LENGTH_SHORT).show();
                }
        } else {
            Toast.makeText(view.getContext(), "Faltan campos por rellenar", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     *  Método que lleva al empleado a la pantalla anterior una vez añadido el usuario nuevo
     *
     */
    private void moverPantalla() {
        Intent intent = new Intent(FormularioCrearCliente.this, AdministrarClientes.class);
        startActivity(intent);
    }

    private static boolean isNumeric(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }
}
