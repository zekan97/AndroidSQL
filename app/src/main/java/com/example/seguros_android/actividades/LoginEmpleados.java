package com.example.seguros_android.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.seguros_android.R;
import com.example.seguros_android.datos.MyOpenHelper;

public class LoginEmpleados extends AppCompatActivity {
    EditText usuario, pass;
    SQLiteDatabase db;
    String passStr;
    public String DNIUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = findViewById(R.id.dniUser);
        pass = (EditText) findViewById(R.id.passUser);

        MyOpenHelper dbHelper = new MyOpenHelper(this);
        db = dbHelper.getWritableDatabase();
        if (db != null) {
            insertar();
        }

    }

    public void onClick(View view) {
        DNIUsuario = usuario.getText().toString();
        passStr = pass.getText().toString();
        if (estaActivo(DNIUsuario, passStr)) {
            if (Exists(DNIUsuario, passStr)) {
                if (es_administrador(DNIUsuario, passStr)) {
                    Toast.makeText(view.getContext(), "Bienvenido " + DNIUsuario, Toast.LENGTH_SHORT).show();
                    loginOkAdmin();
                } else {
                    Toast.makeText(view.getContext(), "Bienvenido " + DNIUsuario, Toast.LENGTH_SHORT).show();

                    SharedPreferences prefs = getSharedPreferences("CuentaUsuario", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("dni_empleado", DNIUsuario);
                    editor.commit();


                    loginOkTrabajador();
                }
            } else
                Toast.makeText(view.getContext(), "El usuario no existe: " + DNIUsuario, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(view.getContext(), "Este usuario no esta activo", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * Metodo que abre la nueva actividad
     * de un trabajador que no es admin
     */

    private void loginOkTrabajador() {
        Intent intent = new Intent(LoginEmpleados.this, MenuEmpleadoNoAdmin.class);
        intent.putExtra("dni_vendedor", DNIUsuario);
        startActivity(intent);
    }

    /**
     * Metodo que abre la nueva actividad
     * de un trabajador que es admin
     */

    private void loginOkAdmin() {
        Intent intent = new Intent(LoginEmpleados.this, MenuEmpleadoAdmin.class);
        startActivity(intent);
    }

    /**
     * Metodo que comprueba si un usuario
     * con una contraseña X existe
     * @param _id
     * @param _pass
     * @return
     */

    public boolean Exists(String _id, String _pass) {
        String[] args = new String[] {_id, _pass};
        Cursor c = db.rawQuery(" SELECT DNI, password FROM vendedores WHERE DNI=? and password=?", args);
        boolean exists = (c.getCount() > 0);
        c.close();
        return exists;
    }

    /**
     * Metodo que comprueba si el usuario
     * es admin o no
     * @param _id
     * @param _pass
     * @return
     */

    public boolean es_administrador (String _id, String _pass) {
        String[] args = new String[] {_id, _pass};
        Cursor c = db.rawQuery(" SELECT es_admin FROM vendedores WHERE DNI=? and password=?", args);
        c.moveToFirst();
        int valor =  c.getInt(c.getColumnIndex("es_admin"));
        c.close();
        if( valor == 1) {
            return true;
        }
        return false;
    }

    /**
     * Metodo que comprueba si un empleado
     * esta en activo o no
     * @param _id
     * @param _pass
     * @return
     */
    public boolean estaActivo(String _id, String _pass) {
        String[] args = new String[] {_id, _pass};
        Cursor c = db.rawQuery(" SELECT activo FROM vendedores WHERE DNI=? and password=?", args);
        c.moveToFirst();
        int valor =  c.getInt(c.getColumnIndex("activo"));
        c.close();
        if( valor == 1) {
            return true;
        }
        return false;
    }

    /**
     * Metodo que inserta los primeros datos
     * en la base de datos
     */

    public void insertar() {
        ContentValues cv1 = new ContentValues();
        cv1.put("DNI", "33333333M");
        cv1.put("nombre", "Kiko");
        cv1.put("apellidos", "Jimenez");
        cv1.put("telefono", "674434565");
        cv1.put("es_admin", 1);
        cv1.put("activo", 1);
        cv1.put("password", "1234");
        db.insert("vendedores", null, cv1);

        ContentValues cv2 = new ContentValues();
        cv2.put("DNI", "22222222P");
        cv2.put("nombre", "Juan");
        cv2.put("apellidos", "Jimenez");
        cv2.put("telefono", "674434465");
        cv2.put("es_admin", 0);
        cv2.put("activo", 1);
        cv2.put("password", "1234");
        db.insert("vendedores", null, cv2);

        ContentValues cv3 = new ContentValues();
        cv3.put("id_seguro", 1);
        cv3.put("nombre", "Coche Terceros");
        cv3.put("descripcion", "Este es un seguro a terceros, que cubre los daños ocasionados a otros vehiculos durante un accidente");
        cv3.put("precio", 250.0);
        db.insert("seguros", null, cv3);

        ContentValues cv4 = new ContentValues();
        cv4.put("id_seguro", 2);
        cv4.put("nombre", "Coche Todo riesgo");
        cv4.put("descripcion", "Este es un seguro de cochde a todo riesgo que cubre los daños ocasionados a otros vehiculos y reparacion de desperfectos al propio coche");
        cv4.put("precio", 650.0);
        db.insert("seguros", null, cv4);

        ContentValues cv5 = new ContentValues();
        cv5.put("id_seguro", 3);
        cv5.put("nombre", "Casa + Coche Todo riesgo");
        cv5.put("descripcion", "Este es un seguro de casa que incluye un seguro todo riesgo para coches con todas sus ventajas añadidas");
        cv5.put("precio", 1250.0);
        db.insert("seguros", null, cv5);

        ContentValues cv6 = new ContentValues();
        cv6.put("DNI", "X9197650L");
        cv6.put("nombre", "Robert");
        cv6.put("apellidos", "Marian");
        cv6.put("activo", 1);
        cv6.put("telefono", "642216897");
        db.insert("clientes", null, cv6);

    }
}
