package com.example.seguros_android.actividades;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.seguros_android.R;
import com.example.seguros_android.datos.MyOpenHelper;

public class FormularioEliminarEmpleados extends AppCompatActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_eliminar_empleado);
        MyOpenHelper dbHelper = new MyOpenHelper(this);
        db = dbHelper.getWritableDatabase();

    }

    /**
     * Metodo que elimina un empleado
     * @param v
     */

    public void eliminar(View v) {
        EditText cajaDniUser = findViewById(R.id.cajaDniUser);
        if(exists(cajaDniUser.getText().toString())) {
            String[] args = new String[]{cajaDniUser.getText().toString()};
            db.execSQL("UPDATE vendedores SET activo=0 WHERE DNI=?", args);
            Toast.makeText(v.getContext(), "Este empleado ya no tiene acceso a la app", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(v.getContext(), "No existe un usuario con ese DNI", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Metodo que comprueba si un dni
     * esta en la base
     * @param dni
     * @return
     */

    public boolean exists(String dni) {
        String[] args = new String[] {dni};
        Cursor c = db.rawQuery(" SELECT DNI FROM vendedores WHERE DNI=?", args);
        boolean exists = (c.getCount() > 0);
        c.close();
        return exists;
    }

}
