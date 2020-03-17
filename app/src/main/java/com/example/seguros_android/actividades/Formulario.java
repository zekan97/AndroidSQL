package com.example.seguros_android.actividades;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.seguros_android.R;
import com.example.seguros_android.datos.MyOpenHelper;

public class Formulario extends AppCompatActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario);

        MyOpenHelper dbHelper = new MyOpenHelper(this);
        db = dbHelper.getWritableDatabase();
    }

    /**
     *  Metodo que inserta un empleado que no es admin
     *  FALTA AÑADIR APELLIDO TAMBIEN QUE NO ESTA EN EL DISEÑO
     * @param view
     */

    public void onClick(View view) {
        EditText nombre_empleado = findViewById(R.id.name_user);
        EditText pass1 = findViewById(R.id.pass1);
        EditText pass2 = findViewById(R.id.pass2);
        EditText dni = findViewById(R.id.dni_valor);
        EditText telefono = findViewById(R.id.telefono_valor);

        String contrasena = String.valueOf(pass1.getText());
        String contrasena2 = String.valueOf(pass2.getText());

        if(nombre_empleado.getText().length()!=0 || pass1.getText().length()!=0 || pass2.getText().length()!=0
                || telefono.getText().length()!=0  || dni.getText().length()!=0) {
            if(contrasena.equals(contrasena2)){
                if(isNumeric(telefono.getText().toString())) {
                    ContentValues cv = new ContentValues();
                    cv.put("DNI", dni.getText().toString());
                    cv.put("nombre", nombre_empleado.getText().toString());
                    cv.put("apellidos", "Jimenez");
                    cv.put("telefono", telefono.getText().toString());
                    cv.put("es_admin", 0);
                    cv.put("activo", 1);
                    cv.put("password", pass1.getText().toString());
                    db.insert("vendedores", null, cv);
                    Toast.makeText(view.getContext(), "Empleado añadido", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(view.getContext(), "El telefono no es un numero", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(view.getContext(), "Las contraseñas no son iguales", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(view.getContext(), "Faltan campos por rellenar", Toast.LENGTH_SHORT).show();
        }
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
